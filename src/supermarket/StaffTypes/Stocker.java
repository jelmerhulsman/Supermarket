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

    public Stocker(String name, Vector2f spawnLocation, Storage storage, Aisle aisle) {
        super(name, spawnLocation);
        
        action = Action.WAITING;
        this.storage = storage;
        this.aisle = aisle;
    }

    public void getItemsFromStorage(Category category) {
        if (!storage.getItems().isEmpty()) {
            System.out.println("STAFF MEMBER " + name + " is picking up items from storage...");
            items.addAll(storage.getItems(MAX_ITEMS, category));
        }
    }

    public void setAisle(Aisle aisle) {
        this.aisle = aisle;
    }

    public void putItemsInAisle() {
 
            System.out.println("STOCKER " + name + " is picking up items from storage...");
            //items.addAll(storage.getItems(MAX_ITEMS));
            sleep(items.size() * ITEM_INTERACTION_TIME);
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
                        //getItemsFromStorage(Catego);
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
