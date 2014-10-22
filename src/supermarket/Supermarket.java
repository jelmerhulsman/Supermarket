package supermarket;

import com.jme3.math.Vector2f;
import java.util.List;

/**
 *
 * @author Hulsman
 */
public class Supermarket {

    private List<Aisle> aisles;
    private List<Checkout> checkouts;
    private List<Department> departments;
    private List<Item> items;
    private Storage storage;
    private Truck truck;

    public Supermarket() {
        final int MAX_AISLES = 4;
        final int MAX_ITEMS_PER_AISLE = 2;
        final int MAX_CHECKOUTS = 4;
        final int MAX_DEPARTMENTS = 2;
        final int MAX_ITEMS_PER_DEPARTMENT = 1;
        final int MAX_STAFF_MEMBERS = 6;
        final int MAX_UNIQUE_ITEMS = (MAX_AISLES * MAX_ITEMS_PER_AISLE) + (MAX_DEPARTMENTS * MAX_ITEMS_PER_DEPARTMENT);

        for (int i = 0; i < MAX_AISLES; i++) {
            aisles.add(new Aisle("Liquor",new Vector2f(40,40), Item.Category.BEER, Item.Category.LIQUOR));
        }

        for (int i = 0; i < MAX_CHECKOUTS; i++) {
            checkouts.add(new Checkout(i + 1, new Vector2f(90 - 10 * i, 80)));
        }

        for (int i = 0; i < MAX_DEPARTMENTS; i++) {
            departments.add(new Department("Drinks Department",new Vector2f(50,50)));
        }

        storage = new Storage("Storage",new Vector2f(0,50));
        truck = new Truck("Truck",new Vector2f(0,0));

        //Add staff members
        //
        //

        // Add items
        //
        //
    }

    public static void main(String[] args) {
        // TODO code application logic here
    }
}
