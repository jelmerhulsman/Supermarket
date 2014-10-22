package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.StaffTypes.Staff;
import supermarket.StaffTypes.Supervisor;
import supermarket.StaffTypes.Unloader;


/**
 *
 * @author Hulsman
 */
public class Supermarket {

    private ArrayList<Aisle> aisles = new ArrayList<>();
    private ArrayList<Checkout> checkouts = new ArrayList<>();
    private ArrayList<Department> departments = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private Storage storage;
    private Truck truck;
    private Staff unloader;

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
        unloader = new Unloader("Jannes");
        unloader.calcDistanceToTarget("Storage");
        //

        // Add items
        //
        //
        
    }

    public static void main(String[] args) {
        Supermarket simulation = new Supermarket();
        System.out.println("test");
    }
}
