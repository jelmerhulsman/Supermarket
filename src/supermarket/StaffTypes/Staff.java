package supermarket.StaffTypes;
import com.jme3.math.FastMath;
import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.PartOfShop;
public class Staff {

    protected String name;
    protected float speed;
    protected PartOfShop objectType;
    protected Vector2f location;

    public Staff(String name){
        this.name = name;
        this.speed = 2.0f;
        this.location = new Vector2f(100,100);
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
     * @return the class of the current location
     */
    public PartOfShop getObjectType()
    {
        return objectType;
    }
    
    /**
     * Moves this staff member to the specified location name
     * @param target For example: "The liquor Aisle"
     * @param objects This method needs all the possible location objects in an arraylist
     */
    public void gotoLocation(String target,ArrayList<PartOfShop> objects)
    {
        Vector2f tarLoc,curLoc;
        PartOfShop targetObject = null;
        float distance;
        for(PartOfShop o : objects)
        {
            if(o.seekByName(target))
                targetObject = o;
                
        }
        curLoc = this.getLocation();
        tarLoc = targetObject.getLocation();
        distance = curLoc.distance(tarLoc);
        
        float moveX = FastMath.floor(tarLoc.x) - FastMath.floor(curLoc.x);
        float moveY = FastMath.floor(tarLoc.y) - FastMath.floor(curLoc.y);
        float moveTotal = FastMath.abs(moveX) + FastMath.abs(moveY);

        moveX = (moveX / moveTotal) * speed;
        moveY = (moveY / moveTotal) * speed;
        
        while (distance > 0)
        {
            curLoc.addLocal(moveX,moveY);
            distance = curLoc.distance(tarLoc);
        }
    }
}
