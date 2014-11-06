package supermarket;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import java.util.ArrayList;

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

    public void gotoLocation(String targetName, ArrayList<ObjectInShop> objects) {
        ObjectInShop targetObject = null;
        for (ObjectInShop o : objects) {
            if (o.seekByName(targetName)) {
                targetObject = o;
            }
        }

        if (targetObject == null) {
            System.out.println("ERROR: TARGET / " + targetName + " / " + this.getClass().toString() + " / " + this.name + " NOT FOUND! CHECK YOUR CODE!");
        }

        Vector2f targetLocation = new Vector2f(targetObject.getLocation());
        
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
        curLocObject = targetObject;
        System.out.println(name + " has reached " + targetName);

    }

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

    public void sleep(int milliseconds) {
        try {
            operation.currentThread().sleep(milliseconds); //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            operation.currentThread().interrupt();
        }
    }

    public void update(final ArrayList<ObjectInShop> staticLocations) {
    }
}
