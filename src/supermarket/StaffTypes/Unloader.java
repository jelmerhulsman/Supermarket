package supermarket.StaffTypes;

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
    public Unloader(Storage storage, Truck truck, ArrayList<Item> shopItems) {
        action = Action.WAITING;
        this.storage = storage;
        this.truck = truck;
        this.shopItems = shopItems;
    }

    public ArrayList<Item> getShopItems() {
        return shopItems;
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
            System.out.println(name + " is picking up items from truck...");
            items.addAll(truck.unload(MAX_ITEMS));
            sleep(MAX_ITEMS * ITEM_INTERACTION_TIME);
        }
    }

    public void storeItemsInStorage() {
        for (Item i : items) {
            System.out.println(name + " is storing item " + i.getName() + " in the storage.");
            storage.addItem(i);
            sleep(ITEM_INTERACTION_TIME);
        }
    }

    public void orderItems() {
        ArrayList<Item> orderItems = new ArrayList<>();
        for (Item shopItem : shopItems) {
            int currentStash = storage.getItemCount(shopItem.getName());
            if (currentStash < MIN_STASH) {
                do {
                    orderItems.add(new Item(shopItem));
                    currentStash++;
                } while (currentStash < MAX_STASH);
            }
        }

        if (!orderItems.isEmpty()) {
            System.out.println(name + " is ordering items.");
            sleep(15000);
            truck.order(orderItems);
            System.out.println(name + " has ordered the items.");
        }
    }

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
                                orderItems();
                                sleep(5000);
                            }
                            break;
                    }
                }
            }
        });
        operation.start();
    }
}