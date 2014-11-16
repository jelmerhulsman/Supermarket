package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Aisle;
import supermarket.Item;
import supermarket.Item.Category;
import supermarket.ObjectInShop;
import supermarket.Sales;
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
    private int currentRun;

    /**
     * Make a new stocker, this person will slowly fill up all the aisles
     *
     * @param name the name of this stocker
     * @param location the starting location of this stocker
     * @param storage the starting workplace of this stocker
     */
    public Stocker(String name, Vector2f location, Storage storage) {
        super(name, location);

        action = Action.WAITING;
        this.storage = storage;
        aisle = null;
        currentRun = 0;
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

    /**
     * Used by this stocker to decide which aisle he will be filling next
     *
     * @param staticLocations a collection of all the possible locations in the
     * simulation
     */
    private void chooseAisle(ArrayList<ObjectInShop> staticLocations) {
        for (ObjectInShop o : staticLocations) {
            Sales tempSales = null;
            if (o instanceof Aisle) {
                Aisle tempAisle = (Aisle) o;
                if (tempAisle.getItems().isEmpty() && !tempAisle.isManned()) {
                    setAisle(tempAisle);
                    tempAisle.setManned(true);
                    break;
                }

                if (tempAisle == this.getAisle() && currentRun == 0) {
                    tempAisle.setManned(false);
                }
            }
            if (o instanceof Sales) {
                tempSales = (Sales) o;
                if (tempSales.getItems().isEmpty() && !tempSales.isManned()) {
                    setAisle(tempSales);
                    tempSales.setManned(true);
                    break;
                }

                if (tempSales == this.getAisle() && currentRun == 0) {
                    tempSales.setManned(false);
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
                            if (currentRun == NUMBER_OF_RUNS_PER_AISLE) {
                                currentRun = 0;
                                aisle = null;
                                action = Action.WAITING;
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
                            currentRun++;
                            action = Action.GET_ITEMS;
                            break;
                        case WAITING:
                            if (aisle == null) {
                                chooseAisle(staticLocations);
                                action = Action.WAITING;
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
