package supermarket;

import java.util.ArrayList;
import java.util.List;

public class Truck {

    public enum Status {
        WAITING, UNLOADING, AWAY, EMPTY
    };
    private List<Item> items = new ArrayList<Item>();
    private Status status;

    /**
     * creates a truck
     * @param status WAITING, UNLOADING, AWAY, EMPTY
     */
    public Truck(Status status) {
        this.status = status;
    }

    /**
     * returns the status of the truck
     * @return WAITING, UNLOADING, AWAY, EMPTY
     */
    public Status getStatus() {
        return status;
    }

    /**
     * sets the status of the truck
     * @param status WAITING, UNLOADING, AWAY, EMPTY
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * unloads the truck (returns all the items in the truck in an array
     *                    and removes them from the truck)
     * @return items
     */
    public List<Item> unload() {
        List<Item> bufferItems = items;
        items.clear();
        return bufferItems;
    }

    /**
     * orders items (add's items to the truck)
     * @param items 
     */
    public void order(List<Item> items) {
     for(Item i : items)
        this.items.add(i);
    }
}
