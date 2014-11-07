package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.StaffTypes.Unloader;

/**
 *
 * @author SDJM
 */
public class Truck extends ObjectInShop {

    private ArrayList<Item> items;

    /**
     * creates a truck
     *
     * @param status WAITING, UNLOADING, AWAY, EMPTY
     */
    public Truck(String name, Vector2f location) {
        super(name, location);
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
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
            for (int a = items.size() - 1; a >= 0; a--) {
                i.add(items.get(a));
                items.remove(a);
            }
        } else {
            for (int a = items.size() - 1; a >= 0; a--) {
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
        this.items.addAll(items);
        items.clear();
    }
}
