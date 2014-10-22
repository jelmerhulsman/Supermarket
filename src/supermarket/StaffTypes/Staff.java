package supermarket.StaffTypes;
import supermarket.Object;
public class Staff {

    protected String name;
    protected float speed;
    protected Object location;

    
    
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
    
    public String getLocation()
    {
        return location.getLocation();
    }
}
