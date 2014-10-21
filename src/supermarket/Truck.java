package supermarket;

import java.util.List;

public class Truck {

    public enum Status {

        AWAY, WAITING, UNLOADING, EMPTY
    };
    private List<Item> items;
    private Status status;

    /**
     * creates a truck
     *
     * @param status WAITING, UNLOADING, AWAY, EMPTY
     */
    public Truck() {
        items = null;
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
    public List<Item> unload() {
        List<Item> i = items;
        items.clear();
        return i;
    }

    /**
     * orders items (add's items to the truck)
     *
     * @param items
     */
    public void order(List<Item> items) {
        for (Item item : items) {
            this.items.add(item);
        }
    }
}
