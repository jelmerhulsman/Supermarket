package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author SDJM
 */
public class Department extends ObjectInShop {

    private ArrayList<Aisle> aisles;
    private Vector2f topLeft;
    private float width;
    private float height;

    public Department(String name, ArrayList<String> aisleNames, ArrayList<Aisle> aisles) {
        super(name, new Vector2f(-100f, -100f));

        this.aisles = new ArrayList<>();
        for (Aisle aisle : aisles) {
            if (aisleNames.contains(aisle.getName())) {
                this.aisles.add(aisle);
            }
        }

        setTopLeft();
        setWidth();
        setHeight();

        this.setLocation(centralPoint());
    }

    /**
     * Gets all the containing Aisles in this department
     *
     * @return an ArrayList of aisles
     */
    public ArrayList<Aisle> getAisles() {
        return aisles;
    }

    public Vector2f getTopLeft() {
        return new Vector2f(topLeft);
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    private void setTopLeft() {
        topLeft = new Vector2f(500f, 500f);

        for (Aisle aisle : aisles) {
            if (aisle.getLocation().x < topLeft.x) {
                topLeft.x = aisle.getLocation().x;
            }

            if (aisle.getLocation().y < topLeft.y) {
                topLeft.y = aisle.getLocation().y;
            }
        }
    }

    private void setWidth() {
        float minX = 500f;
        float maxX = -100f;

        for (Aisle aisle : aisles) {
            if (aisle.getLocation().x < minX) {
                minX = aisle.getLocation().x;
            }

            if (aisle.getLocation().x > maxX) {
                maxX = aisle.getLocation().x;
            }
        }

        width = maxX - minX;
    }

    private void setHeight() {
        float minY = 500f;
        float maxY = -100f;

        for (Aisle aisle : aisles) {
            if (aisle.getLocation().y < minY) {
                minY = aisle.getLocation().y;
            }

            if (aisle.getLocation().y > maxY) {
                maxY = aisle.getLocation().y;
            }
        }

        height = maxY - minY;
    }

    private Vector2f centralPoint() {
        Vector2f centralPoint = new Vector2f();

        for (Aisle aisle : aisles) {
            centralPoint.addLocal(aisle.getLocation());
        }

        return centralPoint.divideLocal(aisles.size());
    }
}
