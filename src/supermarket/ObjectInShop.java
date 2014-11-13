package supermarket;

import com.jme3.math.Vector2f;

/**
 *
 * @author SDJM
 */
public class ObjectInShop {

    protected String name;
    protected Vector2f location;
    protected ObjectInShop curLocObject;

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

    public void setLocation(Vector2f location) {
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Vector2f getLocation() {
        return new Vector2f(location);
    }
}
