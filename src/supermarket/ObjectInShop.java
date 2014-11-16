package supermarket;

import com.jme3.math.Vector2f;

/**
 *
 * @author SDJM
 */
public abstract class ObjectInShop {

    protected String name;
    protected Vector2f location;

    /**
     * Creates an objectinshop
     *
     * @param name the name of this object
     * @param location the (starting)location of this object
     */
    public ObjectInShop(String name, Vector2f location) {
        this.name = name;
        this.location = location;
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
