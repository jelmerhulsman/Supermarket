package supermarket.StaffTypes;

import java.util.ArrayList;
import supermarket.Aisle;
import supermarket.Item;
import supermarket.ObjectInShop;
import supermarket.Storage;

public class Stocker extends Staff {
    
    private enum Action {

        GET_ITEMS, STORE_ITEMS, WAITING
    }
    
    private Action action;
    private Storage storage;
    private Aisle aisle;

    public Stocker(String name, Storage storage) {
        super(name);
        workplace = storage;
        
        action = Action.WAITING;
        this.storage = storage;
    }

    public void getItemsFromStorage() {
        if (!storage.getItems().isEmpty()) {
            System.out.println("STOCKER " + name + " is picking up items from storage...");
            items.addAll(storage.getItems(maxItems));
            sleep(items.size() * ITEM_INTERACTION_TIME);
        }
    }
    
    public Aisle getAisleOfFirstItem(ArrayList<ObjectInShop> staticLocations)
    {
        for (ObjectInShop o : staticLocations) {
            if (o instanceof Aisle) {
                Aisle tempAisle = (Aisle) o;
                if (tempAisle.getCategories().contains(items.get(0).getCategory())) {
                    return tempAisle;
                }
            }
        }
        
        return null;
    }

    public void storeItemsInAisle() {
        for (Item i : items) {
            System.out.println("Stocker " + name + " is storing item" + i.getName() + " in aisle " + aisle.getName());
            aisle.loadAisle(i);
            sleep(ITEM_INTERACTION_TIME);
        }
    }
    
    public void update(final ArrayList<ObjectInShop> staticLocations) {
        operation = new Thread(new Runnable() {
            @Override
            public void run() {
                switch (action) {
                    case GET_ITEMS:
                        gotoLocation("Storage", staticLocations);
                        getItemsFromStorage();
                        action = Action.STORE_ITEMS;
                        break;
                    case STORE_ITEMS:
                        aisle = getAisleOfFirstItem(staticLocations);
                        gotoLocation(aisle.getName(), staticLocations);
                        storeItemsInAisle();
                        break;
                    case WAITING:
                        
                        break;
                }
            }
        });
        operation.start();
    }
}
