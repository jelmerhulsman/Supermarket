package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item;
import supermarket.Person;

/**
 *
 * @author SDJM
 */
public class Staff extends Person {
    
    protected final int MAX_ITEMS = 10;
    protected ArrayList<Item> items;

    public Staff(String name, Vector2f location) {
        super(name, location);
        items = new ArrayList<>();
    }
        
    public ArrayList<Item> getItems() {
        return items;
    }
}
