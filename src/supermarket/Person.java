package supermarket;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author SDJM
 */
public class Person extends ObjectInShop {

    final protected int ITEM_INTERACTION_TIME = 250;
    final private int STEP_TIME = 100;
    protected Thread operation;
    protected float speed;

    public Person() {
    }

    public Person(String name, Vector2f spawnLocation) {
        super(name, spawnLocation);
        operation = new Thread();
    }

    /**
     * Move this person to location x in an arraylist of locations
     *
     * @param targetName The string of the location we are looking for
     * @param objects The collection of locations where we will be searching in
     */
    public void gotoLocation(String targetName, ArrayList<ObjectInShop> objects) {
        ObjectInShop targetObject = null;
        for (ObjectInShop o : objects) {
            if (o.getName().equals(targetName)) {
                targetObject = o;
            }
        }

        if (targetObject == null) {
            System.out.println("ERROR: TARGET / " + targetName + " / " + this.getClass().toString() + " / " + this.name + " NOT FOUND! CHECK YOUR CODE!");
        }

        Vector2f targetLocation = new Vector2f(targetObject.getLocation());

        float moveX = FastMath.floor(targetLocation.x) - FastMath.floor(getLocation().x);
        float moveY = FastMath.floor(targetLocation.y) - FastMath.floor(getLocation().y);
        float moveTotal = FastMath.abs(moveX) + FastMath.abs(moveY);

        moveX = (moveX / moveTotal) * speed;
        moveY = (moveY / moveTotal) * speed;

        System.out.println(name + " is going to " + targetName);
        float distanceToTarget = location.distance(targetLocation);
        if (distanceToTarget > speed) {
            while (distanceToTarget > speed) {
                location.addLocal(moveX, moveY);
                distanceToTarget = location.distance(targetLocation);

                sleep(STEP_TIME);
            }
        } else {
            sleep(STEP_TIME);
        }

        location = targetLocation;
        curLocObject = targetObject;
        System.out.println(name + " has reached " + targetName);

    }

    /**
     * Move this person to the specified coordinates and give that coordinate a
     * name
     *
     * @param targetLocation the coordinates where this person will be going to
     * @param targetName the name of the targetlocation
     */
    public void gotoCoords(Vector2f targetLocation, String targetName) {

        float moveX = FastMath.floor(targetLocation.x) - FastMath.floor(location.x);
        float moveY = FastMath.floor(targetLocation.y) - FastMath.floor(location.y);
        float moveTotal = FastMath.abs(moveX) + FastMath.abs(moveY);

        moveX = (moveX / moveTotal) * speed;
        moveY = (moveY / moveTotal) * speed;

        System.out.println(name + " is going to " + targetName);
        float distanceToTarget = location.distance(targetLocation);
        if (distanceToTarget > speed) {
            while (distanceToTarget > speed) {
                location.addLocal(moveX, moveY);
                distanceToTarget = location.distance(targetLocation);

                sleep(STEP_TIME);
            }
        } else {
            sleep(STEP_TIME);
        }

        location = targetLocation;
        System.out.println(name + " has reached " + targetName);
    }

    /**
     * Sleep this thread we are working with for x milliseconds
     *
     * @param milliseconds How long are we waiting in milliseconds?
     */
    public void sleep(int milliseconds) {
        try {
            operation.currentThread().sleep(milliseconds); //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            operation.currentThread().interrupt();
        }
    }

    /**
     * This function will be called over and over again
     *
     * @param staticLocations Collecion of all the locations
     */
    public void update(final ArrayList<ObjectInShop> staticLocations) {
    }
}
