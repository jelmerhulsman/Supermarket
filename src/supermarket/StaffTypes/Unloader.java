package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item;
import supermarket.ObjectInShop;
import supermarket.Storage;
import supermarket.Truck;

/**
 *
 * @author SDJM
 */
public class Unloader extends Staff {

    private final int MIN_STASH = 5;
    private final int MAX_STASH = 25;
    private final int ORDER_TIME_PER_ITEM = 100;

    private enum Action {

        UNLOAD_TRUCK, STORE_ITEMS, WAITING
    }
    private Action action;
    private Storage storage;
    private Truck truck;
    private ArrayList<Item> shopItems;

    /**
     * Constructor for the Unloader staff member
     *
     * @param name Specify the name of this person
     * @param storage Specify the workplace of this person
     */
    public Unloader(String name, Vector2f location, Storage storage, Truck truck, ArrayList<Item> shopItems) {
        super(name, location);

        action = Action.WAITING;
        this.storage = storage;
        this.truck = truck;
        this.shopItems = shopItems;
    }

    /**
     * Gets the maximum # of items this person can carry from the truck and puts
     * it in the Storage
     *
     * @param staticLocations This method needs all the possible location
     * objects in an arraylist
     */
    public void getItemsFromTruck() {
        if (!truck.getItems().isEmpty()) {
            System.out.println("Unloader " + name + " is picking up items from truck...");
            items.addAll(truck.unload(MAX_ITEMS));
            sleep(MAX_ITEMS * ITEM_INTERACTION_TIME);
        }
    }

    /**
     * Stores the items from the inventory of this unloader in the Storage
     */
    public void storeItemsInStorage() {
        System.out.println("Unloader " + name + " is storing the ordered items in the storage...");
        for (Item i : items) {
            storage.addItem(i);
            sleep(ITEM_INTERACTION_TIME);
        }

        System.out.println("Unloader " + name + " has stored the ordered items in the storage.");
        items = new ArrayList<>();
    }

    /**
     * Order new items for the Supermarket which will be put in the truck
     */
    public void orderItems() {
        ArrayList<Item> orderItems = new ArrayList<>();
        for (Item shopItem : shopItems) {
            int currentStash = storage.getItemCount(shopItem.getName());
            if (currentStash < MIN_STASH) {
                if (orderItems.isEmpty()) {
                    System.out.println("Unloader " + name + " is ordering items...");
                }

                do {
                    orderItems.add(new Item(shopItem));
                    currentStash++;
                    sleep(ORDER_TIME_PER_ITEM);
                } while (currentStash < MAX_STASH);
            }
        }

        if (!orderItems.isEmpty()) {
            truck.putItemsInTruck(orderItems);
            System.out.println("Unloader " + name + " has ordered the items.");
        }
    }

    /**
     * This function will be called over and over again
     *
     * @param staticLocations The collection of all the locations
     */
    @Override
    public void update(final ArrayList<ObjectInShop> staticLocations) {
        operation = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    switch (action) {
                        case UNLOAD_TRUCK:
                            gotoLocation("Truck", staticLocations);
                            getItemsFromTruck();
                            action = Action.STORE_ITEMS;
                            break;
                        case STORE_ITEMS:
                            gotoLocation("Storage", staticLocations);
                            storeItemsInStorage();

                            if (truck.getItems().isEmpty()) {
                                action = Action.WAITING;
                            } else {
                                action = Action.UNLOAD_TRUCK;
                            }
                            break;
                        case WAITING:
                            if (!truck.getItems().isEmpty()) {
                                action = Action.UNLOAD_TRUCK;
                            } else {
                                if (locationObject != storage) {
                                    gotoLocation("Storage", staticLocations);
                                } else {
                                    orderItems();
                                }
                            }
                            break;
                    }
                }
            }
        });
        operation.start();
        operation.setName("Unloader " + name);
    }
}