package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Aisle;
import supermarket.Item;
import supermarket.Item.Category;
import supermarket.ObjectInShop;
import supermarket.Storage;

public class Stocker extends Staff {

    private enum Action {

        GET_ITEMS, STORE_ITEMS, WAITING
    }
    private Action action;
    private Storage storage;
    private Aisle aisle;
    private boolean isWorking;
    
    public Stocker(String name, Vector2f spawnLocation, Storage storage, Aisle aisle) {
        super(name, spawnLocation);
        action = Action.WAITING;
        this.storage = storage;
        this.aisle = aisle;
    }


    public void getItemsFromStorage() {
        System.out.println(name + " is picking up items from storage...");
        for (Category category : aisle.getCategories()) {
            if (storage.getItems(storage.getItems().size(), category).size() > 0) {
                items.addAll(storage.getItems(MAX_ITEMS, category));
                sleep(items.size() * ITEM_INTERACTION_TIME);
            }
        }
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setAisle(Aisle aisle) {
        this.aisle = aisle;
    }
    
    public Aisle getAisleOfFirstItem(ArrayList<ObjectInShop> staticLocations)
    {
        for (ObjectInShop o : staticLocations) {
            if (o instanceof Aisle) {
                Aisle temp = (Aisle) o;
                if (temp.getCategories().contains(items.get(0).getCategory())) {
                    return temp;
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
    
    @Override
    public void update(final ArrayList<ObjectInShop> staticLocations) {
        operation = new Thread(new Runnable() {
            @Override
            public void run() {
                switch (action) {
                    case GET_ITEMS:
                        gotoLocation("Storage", staticLocations);
                        if (storage.getItems().isEmpty()) {
                            action = action.WAITING;
                        } else {
                            getItemsFromStorage();
                            isWorking = true;
                            action = Action.STORE_ITEMS;
                        }
                        break;
                    case STORE_ITEMS:
                        gotoLocation(aisle.getName(), staticLocations);
                        storeItemsInAisle();
                        isWorking = true;
                        action = action.GET_ITEMS;

                        break;
                    case WAITING:
                        isWorking = false;
                        if (!storage.getItems().isEmpty()) {
                            action = action.GET_ITEMS;
                        } else {
                            action = action.WAITING;
                        }
                        break;
                }
            }
        });
        operation.setName(this.getName() + " Thread");
        operation.start();
    }
}
