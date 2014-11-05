package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;

public class Department extends ObjectInShop {

    private ArrayList<Aisle> aisles;

    public Department(String name, Vector2f location) {
        super(name, location);
        this.name = name;
    }

    /**
     * Gets all the containing Aisles in this department
     *
     * @return an ArrayList of aisles
     */
    public ArrayList<Aisle> getAisles() {
        return aisles;
    }

    /**
     * Sets the aisles in this department
     *
     * @param aisles The aisles you are willing to set to this department
     */
    public void setAisles(ArrayList<Aisle> aisles) {
        this.aisles = aisles;
    }
}
