package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author SDJM
 */
public class Sales extends Aisle {

    public Sales(String name, Vector2f location, ArrayList<Item.Category> categories, ArrayList<Item> storeItems) {
        super(name, location, categories, storeItems);
    }
}
