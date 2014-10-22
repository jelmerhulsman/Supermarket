package supermarket.StaffTypes;
import com.jme3.math.Vector2f;
import supermarket.Object;
public class Staff {

    protected String name;
    protected float speed;
    protected Object objectType;

    public Staff(String name){
        this.name = name;
        this.speed = 1.0f;
    }

    /**
     * Gets the name of this Staff member
     *
     * @return the name of this staff member
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the CLASS of the current location. For example "Aisle" or "Storage"
     * @return the class of the current location
     */
    public Object getObjectType()
    {
        return objectType;
    }
    
    /**
     * Moves this staff member to the specified location name
     * @param target For example: "The liquor Aisle"
     */
    public void gotoLocation(String target)
    {
        
    }
    
    public void calcDistanceToTarget(String target)
    {
        Object targetObject = new Object("SEEKOBJECT",new Vector2f(0,0));
        targetObject = targetObject.seekByName(target);
        Vector2f curLoc = this.getObjectType().getLocation();
    }
}
