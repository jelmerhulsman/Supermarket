package supermarket.StaffTypes;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item;
import supermarket.ObjectInShop;

public class Staff extends ObjectInShop {

    public Staff(String name) {
        super(name, new Vector2f(0, 50), 2f);
    }
    protected String name;
    protected float speed;
    protected ObjectInShop workplace;
    protected ObjectInShop curLocObject;
    protected Vector2f location;
    protected final int maxItems = 10;
    protected ArrayList<Item> items;

    /**
     * Gets the name of this Staff member
     *
     * @return the name of this staff member
     */
    public String getName() {
        return name;
    }

    @Override
    public Vector2f getLocation() {
        return location;
    }

    /**
     * Gets the CLASS of the current location. For example "Aisle" or "Storage"
     *
     * @return the class of the current location
     */
    public ObjectInShop getCurLocObject() {
        return curLocObject;
    }

    /**
     * gets the workplace object of this staff member
     *
     * @return the workplace
     */
    public ObjectInShop getWorkplace() {
        return workplace;
    }

    /**
     * Moves this staff member to the specified location name
     *
     * @param target For example: "The liquor Aisle"
     * @param objects This method needs all the possible location objects in an
     * arraylist
     */

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds); //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
