package supermarket;

import com.jme3.math.Vector2f;

/**
 *
 * @author Moreno
 */
public class ObjectInShop {

    protected String name;
    protected Vector2f location;
    protected float speed;
    protected ObjectInShop curLocObject;

    public ObjectInShop() {
    }

    public ObjectInShop(String name, Vector2f location) {
        this.name = name;
        this.location = location;
        this.speed = 0;
    }

    public ObjectInShop(String name, Vector2f location, float speed) {
        this.name = name;
        this.location = location;
        this.speed = speed;
    }

    public ObjectInShop getCurLocObject() {
        return curLocObject;
    }

    public String getName() {
        return name;
    }

    public Vector2f getLocation() {
        return location;
    }

    public boolean seekByName(String name) {
        if (this.getName().equals(name)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Moves this object to the specified location name
     *
     * @param targetName For example: "The liquor Aisle"
     * @param objects This method needs all the possible location objects in an
     * arraylist
     */
}
