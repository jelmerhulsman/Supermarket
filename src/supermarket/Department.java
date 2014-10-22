package supermarket;

import java.util.ArrayList;

public class Department extends Object {

    private String name;
    private ArrayList<Aisle> aisles;

    public Department(String name) {
        super(name);
        this.name = name;
    }
/**
 * Gets the name of this department
 * @return the name of the department
 */
    public String getName() {
        return name;
    }
/**
 * Gets all the containing Aisles in this department
 * @return an ArrayList of aisles
 */
    public ArrayList<Aisle> getAisles() {
        return aisles;
    }

    /**
     * Sets the aisles in this department
     * @param aisles The aisles you are willing to set to this department
     */
    public void setAisles(ArrayList<Aisle> aisles) {
        this.aisles = aisles;
    }
}
