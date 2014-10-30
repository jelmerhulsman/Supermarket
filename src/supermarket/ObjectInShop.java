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

    public ObjectInShop(){}
    
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
        if (this.getName() == name) {
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
    public void gotoLocation(String targetName, ArrayList<ObjectInShop> objects) {
        Vector2f targetLocation = null;
        ObjectInShop targetObject = null;
        float distanceToTarget;
        for (ObjectInShop o : objects) {
            if (o.seekByName(targetName)) {
                targetObject = o;
            }
        }
        
        if (targetObject == null) {
            System.out.println("ERROR: TARGET / " + targetName + " / " + this.getClass().toString() + " / " + this.name + " NOT FOUND! CHECK YOUR CODE!");
        }
        
        targetLocation = targetObject.getLocation();
        distanceToTarget = location.distance(targetLocation);

        float moveX = FastMath.floor(targetLocation.x) - FastMath.floor(location.x);
        float moveY = FastMath.floor(targetLocation.y) - FastMath.floor(location.y);
        float moveTotal = FastMath.abs(moveX) + FastMath.abs(moveY);

        moveX = (moveX / moveTotal) * speed;
        moveY = (moveY / moveTotal) * speed;
        
        String className = this.getClass().getName().substring(8);
        if (distanceToTarget > speed) {
            System.out.println(className + name + " is going to " + targetName);
            location.addLocal(moveX, moveY);
        } else {
            location = targetLocation;
            curLocObject = targetObject;
            System.out.println(className + name + " has reached " + targetName);
        }
    }
}
