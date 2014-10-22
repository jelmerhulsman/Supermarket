package supermarket;

import com.jme3.math.Vector2f;

/**
 *
 * @author Moreno
 */
public class Object {
    protected String locationName;
    protected Vector2f location;
    
    public Object(String locationName, Vector2f location)
    {
        this.locationName = locationName;
        this.location = location;
    }

    /**
     * Gets the name of the current location
     * @return the NAME current location. For example: "The wine Aisle" or "The dairy aisle"
     */
    public String getLocationName() {
        return locationName;
    }

    public Vector2f getLocation() {
        return location;
    }
    
    
}
