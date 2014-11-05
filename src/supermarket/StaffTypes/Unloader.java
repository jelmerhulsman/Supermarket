package supermarket.StaffTypes;

import java.util.ArrayList;
import supermarket.Item;
import supermarket.Item.Status;
import supermarket.ObjectInShop;
import supermarket.Storage;
import supermarket.Truck;

public class Unloader extends Staff {

    private enum Action {

        UNLOAD_TRUCK, STORE_ITEMS, WAITING
    }
    private Action action;
    private Storage storage;
    private Truck truck;

    /**
     * Constructor for the Unloader staff member
     *
     * @param name Specify the name of this person
     * @param storage Specify the workplace of this person
     */
    public Unloader(String name, Storage storage, Truck truck) {
        super(name);
        this.workplace = storage;

        action = Action.WAITING;
        this.storage = storage;
        this.truck = truck;
    }

    /**
     * Gets the maximum # of items this person can carry from the truck and puts
     * it in the Storage
     *
     * @param staticLocations This method needs all the possible location
     * objects in an arraylist
     */
    public void getItemsFromTruck() {
        if (truck.getCurUnloader() == null && !truck.getItems().isEmpty()) {
            System.out.println("STAFF MEMBER " + name + " is picking up items from truck...");
            truck.setCurUnloader(this);
            items.addAll(truck.unload(maxItems));
            sleep(maxItems * ITEM_INTERACTION_TIME);
        } else if (truck.getCurUnloader() == this && !truck.getItems().isEmpty()) {
            System.out.println("STAFF MEMBER " + name + " is picking up items from truck...");
            items.addAll(truck.unload(maxItems));
            sleep(items.size() * ITEM_INTERACTION_TIME);
        }
    }

    public void storeItemsInStorage() {
        for (Item i : items) {
            System.out.println(this.getClass().toString() + " / " + name + " is adding item " + i.getName() + " to the storage.");
            i.setStatus(Status.IN_STORAGE);
            storage.addItem(i);
            sleep(ITEM_INTERACTION_TIME);
        }
    }

    public void update(final ArrayList<ObjectInShop> staticLocations) {
        operation = new Thread(new Runnable() {
            @Override
            public void run() {
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
                        }
                        break;
                }
            }
        });
        operation.start();
    }
}