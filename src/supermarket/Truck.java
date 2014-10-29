package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;

public class Truck extends ObjectInShop{

    public enum Status {

        AWAY, WAITING, UNLOADING, EMPTY
    };
    private ArrayList<Item> items;
    private Status status;

    /**
     * creates a truck
     *
     * @param status WAITING, UNLOADING, AWAY, EMPTY
     */
    public Truck(String name, Vector2f location) {
        super(name,location);
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

    /**
     * unloads the truck (returns all the items in the truck in an array and
     * removes them from the truck)
     *
     * @return items
     */
    public ArrayList<Item> unload() {
        ArrayList<Item> i = items;
        items = new ArrayList<>();
        return i;
    }

    /**
     * orders items (add's items to the truck)
     *
     * @param items
     */
    public void order(ArrayList<Item> items) {
     for(Item i : items)
        this.items.add(i);
    }
}
