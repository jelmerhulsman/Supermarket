package supermarket.StaffTypes;

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

    public Stocker(String name, Storage storage) {
        super(name);
        workplace = storage;

        action = Action.WAITING;
        this.storage = storage;
    }

    public void getItemsFromStorage() {
        System.out.println("STAFF MEMBER " + name + " is picking up items from storage...");
        for (Category category : aisle.getCategories()) {
            if (storage.getItems(storage.getItems().size(), category).size() > 0) {
                items.addAll(storage.getItems(maxItems, category));
                sleep(items.size() * ITEM_INTERACTION_TIME);
            }
        }
    }

    public boolean GetIsWorking() {
        return isWorking;
    }

    public void setAisle(Aisle aisle) {
        this.aisle = aisle;
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
