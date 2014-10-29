package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item.Category;
import supermarket.StaffTypes.Staff;
import supermarket.StaffTypes.Unloader;

/**
 *
 * @author Hulsman
 */
public class Supermarket {

    private ArrayList<Aisle> aisles;
    private ArrayList<Checkout> checkouts;
    private ArrayList<Department> departments;
    private ArrayList<Item> items;
    private ArrayList<PartOfShop> allLocations;
    private Storage storage;
    private Truck truck;
    private Staff staff;

    public Supermarket() {
        final int MAX_AISLES = 4;
        final int MAX_ITEMS_PER_AISLE = 2;
        final int MAX_CHECKOUTS = 4;
        final int MAX_DEPARTMENTS = 2;
        final int MAX_ITEMS_PER_DEPARTMENT = 1;
        final int MAX_STAFF_MEMBERS = 6;
        final int MAX_UNIQUE_ITEMS = (MAX_AISLES * MAX_ITEMS_PER_AISLE) + (MAX_DEPARTMENTS * MAX_ITEMS_PER_DEPARTMENT);

        aisles = new ArrayList<>();
        for (int i = 0; i < MAX_AISLES; i++) {
            aisles.add(new Aisle("Liquor", new Vector2f(40, 40), Item.Category.BEER, Item.Category.LIQUOR));
        }

        checkouts = new ArrayList<>();
        for (int i = 0; i < MAX_CHECKOUTS; i++) {
            checkouts.add(new Checkout(i + 1, new Vector2f(90 - 10 * i, 80)));
        }

        departments = new ArrayList<>();
        for (int i = 0; i < MAX_DEPARTMENTS; i++) {
            departments.add(new Department("Drinks Department", new Vector2f(50, 50)));

        }
        allLocations = new ArrayList<>();
        allLocations.addAll(departments);
        allLocations.addAll(aisles);
        allLocations.addAll(checkouts);

        storage = new Storage("Storage", new Vector2f(0, 50));
        truck = new Truck("Truck", new Vector2f(0, 0));


        allLocations.add(storage);
        allLocations.add(truck);

        //Add staff members

        Unloader unloader = new Unloader("Jannes", storage);
        //

        items = new ArrayList<>();
        // Add items
        for (int i = 0; i < 50; i++) {
            items.add(new Item("BudWeiser", Category.BEER, 3, true));
        }
        //
        truck.order(items);
        unloader.getItemsFromTruck(allLocations);
    }

    public static void main(String[] args) {
        Supermarket simulation = new Supermarket();
    }

    private boolean chanceOf(int percent) {
        return (int) (Math.random() * 101) <= percent;
    }
}
