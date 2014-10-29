package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.StaffTypes.Unloader;

public class Truck extends ObjectInShop {

    public enum Status {

        AWAY, WAITING, UNLOADING, EMPTY
    };
    private ArrayList<Item> items;
    private Status status;
    private Unloader curUnloader;

    /**
     * creates a truck
     *     
* @param status WAITING, UNLOADING, AWAY, EMPTY
     */
    public Truck(String name, Vector2f location) {
        super(name, location);
        items = new ArrayList<>();
        status = Status.AWAY;
    }

    /**
     * returns the status of the truck
     *     
* @return WAITING, UNLOADING, AWAY, EMPTY
     */
    public Status getStatus() {
        return status;
    }

    /**
     * sets the status of the truck
     *     
* @param status WAITING, UNLOADING, AWAY, EMPTY
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * gets the current unloader which is unloading stuff from the truck
     *     
* @return The current unloader class
     */
    public Unloader getCurUnloader() {
        return curUnloader;
    }

    /**
     * Sets the current unloader which is unloading stuff from the truck
     *     
* @param curUnloader the current unloader class
     */
    public void setCurUnloader(Unloader curUnloader) {
        this.curUnloader = curUnloader;
    }

    /**
     * unloads x amount of items from the truck (returns x items in the truck in
     * an array and removes them from the truck)
     *     
* @param numberOfItems The total number of items you want to unload
     * @return items
     */
    public ArrayList<Item> unload(int numberOfItems) {
        ArrayList<Item> i = new ArrayList<>();
        if (items.size() < numberOfItems) {
            for (int a = 0; a < items.size(); a++) {
                i.add(items.get(a));
                items.remove(a);
            }
        } else {
            for (int a = 0; a < numberOfItems; a++) {
                i.add(items.get(a));
                items.remove(a);
            }
        }

        return i;
    }

    /**
     * orders items (add's items to the truck)
     *     
* @param items
     */
    public void order(ArrayList<Item> items) {
        for (Item i : items) {
            this.items.add(i);
        }
    }
}
