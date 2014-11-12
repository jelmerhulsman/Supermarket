package supermarket.StaffTypes;

import java.util.ArrayList;
import supermarket.Aisle;
import supermarket.Item;
import supermarket.Item.Category;
import supermarket.ObjectInShop;
import supermarket.Storage;

/**
 * @author SDJM
 */
public class Stocker extends Staff {

    private enum Action {

        GET_ITEMS, STORE_ITEMS, WAITING
    }
    private Action action;
    private Storage storage;
    private Aisle aisle;
    private boolean isWorking;

    public Stocker(Storage storage) {
        super();
        action = Action.WAITING;
        this.storage = storage;
        aisle = null;
        speed = 1;
    }

    /**
     * Retrieves items from the storage and puts it in the Stocker' inventory
     */
    public void getItemsFromStorage() {
        System.out.println(name + " is picking up items from storage...");
        for (Category category : aisle.getCategories()) {
            
                items.addAll(storage.getItems(MAX_ITEMS, category));
                sleep(items.size() * ITEM_INTERACTION_TIME);
            
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public boolean getIsWorking() {
        return isWorking;
    }

    public void setAisle(Aisle aisle) {
        this.aisle = aisle;
    }

    /**
     * Stores all the items from the inventory to their own aisles according to the category
     */
    public void storeItemsInAisle() {
        ArrayList<Item> storedItems = new ArrayList<>();

        for (Item i : items) {
            if (aisle.getItemNames().contains(i.getName())) {
                System.out.println("Stocker " + name + " is storing item" + i.getName() + " in aisle " + aisle.getName());
                aisle.loadAisle(i);
                storedItems.add(i);
                sleep(ITEM_INTERACTION_TIME);
            }
        }

        items.removeAll(storedItems);
    }

    public Aisle getAisle() {
        return aisle;
    }

    /**
     * this function will be looped over and over
     * @param staticLocations the collection of locations
     */
    @Override
    public void update(final ArrayList<ObjectInShop> staticLocations) {
        operation = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    switch (action) {
                        case GET_ITEMS:
                            if (storage.getAllItems().isEmpty() || aisle == null) {
                                action = Action.WAITING;
                            } else {
                                isWorking = true;
                                gotoLocation("Storage", staticLocations);
                                getItemsFromStorage();
                                action = Action.STORE_ITEMS;
                            }
                            break;
                        case STORE_ITEMS:
                            gotoLocation(aisle.getName(), staticLocations);
                            storeItemsInAisle();
                            isWorking = true;
                            action = Action.GET_ITEMS;
                            break;
                        case WAITING:
                            isWorking = false;
                            if (!storage.getAllItems().isEmpty()) {
                                action = Action.GET_ITEMS;
                            } else {
                                sleep(1000);
                            }

                            break;
                    }
                }
            }
        });
        operation.start();
    }
}
