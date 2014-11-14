package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
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

    private final int NUMBER_OF_RUNS_PER_AISLE = 3;

    private enum Action {

        GET_ITEMS, STORE_ITEMS, WAITING
    }
    private Action action;
    private Storage storage;
    private Aisle aisle;
    private int runs;

    public Stocker(String name, Vector2f location, Storage storage) {
        super(name, location);

        action = Action.WAITING;
        this.storage = storage;
        aisle = null;
        runs = 0;
    }

    public Aisle getAisle() {
        return aisle;
    }

    public void setAisle(Aisle aisle) {
        this.aisle = aisle;
    }

    public boolean isWaiting() {
        return (action == Action.WAITING);
    }

    /**
     * Retrieves items from the storage and puts it in the Stocker' inventory
     */
    public void getItemsFromStorage() {
        System.out.println("Stocker " + name + " is picking up items from storage...");
        for (Category category : aisle.getCategories()) {
            items.addAll(storage.getItems(MAX_ITEMS, category));
            sleep(items.size() * ITEM_INTERACTION_TIME);
        }
    }

    /**
     * Stores the items from the inventory of this unloader in the Storage
     */
    public void storeItemsInStorage() {
        System.out.println("Stocker " + name + " is storing items back into the storage...");
        for (Item i : items) {
            storage.addItem(i);
            sleep(ITEM_INTERACTION_TIME);
        }

        items = new ArrayList<>();
    }

    private void chooseAisle(ArrayList<ObjectInShop> staticLocations) {
        for (ObjectInShop o : staticLocations) {
            if (o instanceof Aisle) {
                Aisle tempAisle = (Aisle) o;

                if (tempAisle.getItems().isEmpty() && !tempAisle.isManned()) {
                    setAisle(tempAisle);
                    tempAisle.setManned(true);
                    break;
                }
                
                if (tempAisle == this.getAisle() && runs == 0) {
                    tempAisle.setManned(false);
                }
            }
        }
    }

    /**
     * Stores all the items from the inventory to their own aisles according to
     * the category
     */
    public void storeItemsInAisle() {
        ArrayList<Item> storedItems = new ArrayList<>();

        System.out.println("Stocker " + name + " is storing items in aisle " + aisle.getName() + "...");
        for (Item i : items) {

            if (aisle.getItemNames().contains(i.getName())) {
                i.setAvailable(false);
                aisle.loadAisle(i);
                storedItems.add(i);
                sleep(ITEM_INTERACTION_TIME);
            }
        }

        items.removeAll(storedItems);
    }

    /**
     * this function will be looped over and over
     *
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
                            if (runs == NUMBER_OF_RUNS_PER_AISLE) {
                                runs = 0;
                                aisle = null;
                            }
                            if (storage.getAllItems().isEmpty() || aisle == null) {
                                action = Action.WAITING;
                            } else {
                                gotoLocation("Storage", staticLocations);
                                storeItemsInStorage();
                                getItemsFromStorage();
                                action = Action.STORE_ITEMS;
                            }
                            break;
                        case STORE_ITEMS:
                            gotoLocation(aisle.getName(), staticLocations);
                            storeItemsInAisle();
                            runs++;
                            action = Action.GET_ITEMS;
                            break;
                        case WAITING:
                            if (aisle == null) {
                                chooseAisle(staticLocations);
                            } else {
                                if (!storage.getAllItems().isEmpty()) {
                                    action = Action.GET_ITEMS;
                                } else {
                                    sleep(1000);
                                }
                            }
                            break;
                    }
                }
            }
        });
        operation.start();
        operation.setName("Stocker " + name);
    }
}
