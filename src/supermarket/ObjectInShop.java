package supermarket;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author Moreno
 */
public class ObjectInShop {

    protected String name;
    protected Vector2f location;
    protected float speed;
    protected ObjectInShop curLocObject;

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

    
    /**
     * Gets the name of the current location
     *
     * @return the NAME current location. For example: "The wine Aisle" or "The
     * dairy aisle"
     */
    public String getLocationName() {
        return name;
    }

    public Vector2f getLocation() {
        return location;
    }

    public boolean seekByName(String name) {
        if (this.getLocationName() == name) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Moves this object to the specified location name
     *
     * @param target For example: "The liquor Aisle"
     * @param objects This method needs all the possible location objects in an
     * arraylist
     */
    public void gotoLocation(String target, ArrayList<ObjectInShop> objects) {
        Vector2f tarLoc, curLoc;
        ObjectInShop targetObject = null;
        float distance;
        for (ObjectInShop o : objects) {
            if (o.seekByName(target)) {
                targetObject = o;
            }

        }
        if (targetObject == null) {
            System.out.println("ERROR: TARGET '" + target + "' AT STAFF MEMBER '" + this.name + "' NOT FOUND! CHECK YOUR CODE!");
        }
        curLoc = this.getLocation();
        tarLoc = targetObject.getLocation();
        distance = curLoc.distance(tarLoc);

        float moveX = FastMath.floor(tarLoc.x) - FastMath.floor(curLoc.x);
        float moveY = FastMath.floor(tarLoc.y) - FastMath.floor(curLoc.y);
        float moveTotal = FastMath.abs(moveX) + FastMath.abs(moveY);

        moveX = (moveX / moveTotal) * speed;
        moveY = (moveY / moveTotal) * speed;

        System.out.println("STAFF MEMBER " + this.name + " is going to " + target);

        while (distance > 0.1) {
            curLoc.addLocal(moveX, moveY);
            distance = curLoc.distance(tarLoc);
        }
        System.out.println("STAFF MEMBER " + this.name + " has reached " + target);
        curLocObject = targetObject;
    }
    }
