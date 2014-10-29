package supermarket.StaffTypes;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.ObjectInShop;

public class Staff extends ObjectInShop {

    public Staff(String name) {
        super(name, new Vector2f(0, 50), 2f);
    }

    /**
     * Gets the name of this Staff member
     *
     * @return the name of this staff member
     */
    public String getName() {
        return name;
    }

    public Vector2f getLocation() {
        return location;
    }
}
