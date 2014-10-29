package supermarket.StaffTypes;

import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item;
import supermarket.PartOfShop;

public class Staff {

    protected String name;
    protected float speed;
    protected PartOfShop workplace;
    protected PartOfShop curLocObject;
    protected Vector2f location;
    protected final int maxItems = 10;
    protected ArrayList<Item> items;

    public Staff(String name) {
        this.name = name;
        this.speed = 2f;
        this.location = new Vector2f(0, 50);
        items = new ArrayList<>();
    }

    /**
     * Gets the name of this Staff member
     *
     * @return the name of this staff member
     */
    public String getName() {
        return name;
    }

    public Vector2f getLocation() {
        return location;
    }

    /**
     * Gets the CLASS of the current location. For example "Aisle" or "Storage"
     *
     * @return the class of the current location
     */
    public PartOfShop getCurLocObject() {
        return curLocObject;
    }

    /**
     * gets the workplace object of this staff member
     *
     * @return the workplace
     */
    public PartOfShop getWorkplace() {
        return workplace;
    }

    /**
     * Moves this staff member to the specified location name
     *
     * @param target For example: "The liquor Aisle"
     * @param objects This method needs all the possible location objects in an
     * arraylist
     */
    public void gotoLocation(String target, ArrayList<PartOfShop> objects) {
        Vector2f tarLoc, curLoc;
        PartOfShop targetObject = null;
        float distance;
        for (PartOfShop o : objects) {
            if (o.seekByName(target)) {
                targetObject = o;
            }

        }
        if (targetObject == null) {
            System.out.println("ERROR: TARGET '" + target + "' AT STAFF MEMBER '" + this.getName() + "' NOT FOUND! CHECK YOUR CODE!");
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
            sleep(100);
        }
        System.out.println("STAFF MEMBER " + this.name + " has reached " + target);
        curLocObject = targetObject;
    }

    public void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds); //1000 milliseconds is one second.
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
