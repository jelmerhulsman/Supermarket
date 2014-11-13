package supermarket;

import com.jme3.math.Vector2f;

/**
 *
 * @author SDJM
 */
public class ObjectInShop {

    protected String name;
    protected Vector2f location;
    protected ObjectInShop locationObject;

    public ObjectInShop(String name, Vector2f location) {
        this.name = name;
        this.location = location;
    }

    public ObjectInShop getLocationObject() {
        return locationObject;
    }

    public String getName() {
        return name;
    }

    public void setLocation(Vector2f location) {
        this.location = location;
    }

    public Vector2f getLocation() {
        return new Vector2f(location);
    }
}
