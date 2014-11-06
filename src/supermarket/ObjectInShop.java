package supermarket;

import com.jme3.math.Vector2f;

/**
 *
 * @author Moreno
 */
public class ObjectInShop {

    protected String name;
    protected Vector2f location;
    protected ObjectInShop curLocObject;

    public ObjectInShop() {
    }

    public ObjectInShop(String name, Vector2f location) {
        this.name = name;
        this.location = location;
    }

    public ObjectInShop getCurLocObject() {
        return curLocObject;
    }

    public String getName() {
        return name;
    }

    public Vector2f getLocation() {
        return new Vector2f(location);
    }

    public boolean seekByName(String name) {
        if (this.getName().equals(name)) {
            return true;
        } else {
            return false;
        }
    }
}
