package supermarket;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import java.util.ArrayList;

public class Person extends ObjectInShop {

    final protected int ITEM_INTERACTION_TIME = 250;
    final private int STEP_TIME = 100;
    protected Thread operation;

    public Person() {
    }

    public Person(String name, Vector2f location) {
        super(name, location);
    }

    public Person(String name, Vector2f location, float speed) {
        super(name, location, speed);
    }

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

        String className = this.getClass().getName().substring(12);
        System.out.println(className + " " + name + " is going to " + targetName);
        while (distanceToTarget > speed) {
            location.addLocal(moveX, moveY);
            distanceToTarget = location.distance(targetLocation);

            sleep(STEP_TIME);
        }
        location = targetLocation;
        curLocObject = targetObject;
        System.out.println(className + " " + name + " has reached " + targetName);

    }

    public void sleep(int milliseconds) {
        try {
            operation.currentThread().sleep(milliseconds); //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            operation.currentThread().interrupt();
        }
    }
}
