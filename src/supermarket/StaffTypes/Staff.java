package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item;
import supermarket.Person;

/**
 *
 * @author SDJM
 */
public abstract class Staff extends Person {

    protected final int MAX_ITEMS = 10;
    protected ArrayList<Item> items;

    /**
     * Abstract class
     *
     * @param name The name of this staff member
     * @param location the starting location of this start member
     */
    public Staff(String name, Vector2f location) {
        super(name, location);
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
