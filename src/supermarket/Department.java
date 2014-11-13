package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author SDJM
 */
public class Department extends ObjectInShop {

    private ArrayList<Aisle> aisles;

    public Department(String name, ArrayList<String> aisleNames, ArrayList<Aisle> aisles) {
        super(name, new Vector2f(-100f, -100f));
        
        this.aisles = new ArrayList<>();
        for (Aisle aisle : aisles) {
            if (aisleNames.contains(aisle.getName())) {
                this.aisles.add(aisle);
            }
        }

        this.setLocation(getCentralPoint());
    }

    /**
     * Gets all the containing Aisles in this department
     *
     * @return an ArrayList of aisles
     */
    public ArrayList<Aisle> getAisles() {
        return aisles;
    }

    private Vector2f getCentralPoint() {
        Vector2f centralPoint = new Vector2f();
        
        for (Aisle aisle:aisles) {
            centralPoint.addLocal(aisle.getLocation());
        }
        
        return centralPoint.divideLocal(aisles.size());
    }
}
