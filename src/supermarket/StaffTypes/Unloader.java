package supermarket.StaffTypes;

import java.util.ArrayList;
import supermarket.Item;
import supermarket.Item.Status;
import supermarket.ObjectInShop;
import supermarket.Storage;
import supermarket.Truck;

public class Unloader extends Staff {

    Storage storage;
    Truck truck;

    /**
     * Constructor for the Unloader staff member
     *
     * @param name Specify the name of this person
     * @param storage Specify the workplace of this person
     */
    public Unloader(String name, Storage storage) {
        super(name);
        this.workplace = storage;
        storage = null;
        truck = null;
    }

    /**
     * Gets the maximum # of items this person can carry from the truck and puts
     * it in the Storage
     *
     * @param locations This method needs all the possible location objects in
     * an arraylist
     */
    public void getItemsFromTruck(ArrayList<ObjectInShop> locations) {

        for (ObjectInShop o : locations) {
            if (o instanceof Truck) {
                truck = (Truck) o;
            }
            if (o instanceof Storage) {
                storage = (Storage) o;
            }
        }
        if (location != truck.getLocation()) {
            gotoLocation("Truck", locations);
        } else {
            if (truck.getCurUnloader() == null && !truck.getItems().isEmpty()) {
                System.out.println("STAFF MEMBER " + name + " is picking up items from truck...");
                truck.setCurUnloader(this);
                items.addAll(truck.unload(maxItems));
            } else if (truck.getCurUnloader() == this && !truck.getItems().isEmpty()) {
                System.out.println("STAFF MEMBER " + name + " is picking up items from truck...");
                items.addAll(truck.unload(maxItems));
            } else if (truck.getItems().isEmpty()) {
                doNothing();
            } else {
                getItemsFromTruck(locations);
            }
            truck.setCurUnloader(null);
            putItemsInStorage(locations);
        }
    }

    public void putItemsInStorage(ArrayList<ObjectInShop> locations) {
        if (location != storage.getLocation()) {
            gotoLocation("Storage", locations);
        } else {
            for (Item i : items) {
                System.out.println(this.getClass().toString() + " / " + name + " is adding item " + i.getName() + " to the storage.");
                i.setStatus(Status.IN_STORAGE);
                storage.addItem(i);
            }
        }
    }
}