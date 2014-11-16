package supermarket;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author SDJM
 */
public abstract class Person extends ObjectInShop {

    final protected int ITEM_INTERACTION_TIME = 250;
    final private int STEP_TIME = 100;
    final private int WALKING_SPEED = 5;
    protected Thread operation;
    protected ObjectInShop locationObject;
    protected float speed;

    /**
     * Abstract class
     *
     * @param name the name of this person
     * @param location the starting location of this person
     */
    public Person(String name, Vector2f location) {
        super(name, location);
        operation = new Thread();
        speed = WALKING_SPEED;
    }

    /**
     * Used to stop the thread of this person and thus stopping the update loop
     */
    public void kill() {
        operation.stop();
    }

    /**
     * returns the object of the current location
     *
     * @return
     */
    public ObjectInShop getLocationObject() {
        return locationObject;
    }

    /**
     * multiplies the speed
     *
     * @param multiplier multiplier
     */
    public void multiplySpeed(float multiplier) {
        this.speed *= multiplier;
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

        Vector2f targetLocation = new Vector2f(targetObject.getLocation());
        locationObject = null;

        float moveX = FastMath.floor(targetLocation.x) - FastMath.floor(getLocation().x);
        float moveY = FastMath.floor(targetLocation.y) - FastMath.floor(getLocation().y);
        float moveTotal = FastMath.abs(moveX) + FastMath.abs(moveY);

        moveX = (moveX / moveTotal) * speed;
        moveY = (moveY / moveTotal) * speed;

        String[] absoluteClassName = this.getClass().toString().split("\\.");
        String className = absoluteClassName[absoluteClassName.length - 1] + " ";

        System.out.println(className + name + " is now going to " + targetName + "...");
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
        locationObject = targetObject;
    }

    /**
     * Move this person to the specified coordinates and give that coordinate a
     * name
     *
     * @param targetLocation the coordinates where this person will be going to
     * @param targetName the name of the targetlocation
     */
    public void gotoCoords(Vector2f targetLocation) {
        locationObject = null;

        float moveX = FastMath.floor(targetLocation.x) - FastMath.floor(location.x);
        float moveY = FastMath.floor(targetLocation.y) - FastMath.floor(location.y);
        float moveTotal = FastMath.abs(moveX) + FastMath.abs(moveY);

        moveX = (moveX / moveTotal) * speed;
        moveY = (moveY / moveTotal) * speed;

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
