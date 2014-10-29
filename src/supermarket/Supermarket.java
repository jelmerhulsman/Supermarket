package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.StaffTypes.Cashier;
import supermarket.Customer.Stereotype;
import supermarket.StaffTypes.Staff;
import supermarket.StaffTypes.Unloader;

/**
 *
 * @author Hulsman
 */
public class Supermarket {

    private final float SIMULATION_SECOND = 60;
    private final int MAX_CUSTOMERS = 20;
    private ArrayList<Aisle> aisles;
    private ArrayList<Checkout> checkouts;
    private ArrayList<Department> departments;
    private ArrayList<Item> availableItems;
    private Storage storage;
    private Truck truck;
    private Staff unloader;
    private ArrayList<ObjectInShop> allLocations;
    private ArrayList<Customer> customers;

    public Supermarket() {
        final int MAX_AISLES = 4;
        final int MAX_ITEMS_PER_AISLE = 2;
        final int MAX_CHECKOUTS = 4;
        final int MAX_DEPARTMENTS = 2;
        final int MAX_ITEMS_PER_DEPARTMENT = 1;
        final int MAX_STAFF_MEMBERS = 6;
        final int MAX_UNIQUE_ITEMS = (MAX_AISLES * MAX_ITEMS_PER_AISLE) + (MAX_DEPARTMENTS * MAX_ITEMS_PER_DEPARTMENT);

        //Create aisles
        aisles = new ArrayList<>();
        for (int i = 0; i < MAX_AISLES; i++) {
            aisles.add(new Aisle("Liquor", new Vector2f(40, 40), Item.Category.BEER, Item.Category.LIQUOR));
        }

        //Create checkouts
        checkouts = new ArrayList<>();
        for (int i = 0; i < MAX_CHECKOUTS; i++) {
            checkouts.add(new Checkout(i + 1, new Vector2f(90 - 10 * i, 80)));
        }

        //Create departments
        departments = new ArrayList<>();
        for (int i = 0; i < MAX_DEPARTMENTS; i++) {
            departments.add(new Department("Drinks Department", new Vector2f(50, 50)));

        }

        //Create storage and truck
        storage = new Storage("Storage", new Vector2f(0, 50));
        truck = new Truck("Truck", new Vector2f(0, 0));

        //Assign locations in the shop
        allLocations = new ArrayList<>();
        allLocations.addAll(departments);
        allLocations.addAll(aisles);
        allLocations.addAll(checkouts);
        allLocations.add(storage);
        allLocations.add(truck);

        //Add staff members
        unloader = new Unloader("Jannes", storage);
        System.out.println(unloader.getName() + " : " + unloader.getLocation());
        System.out.println("Now going to the truck...");
        unloader.gotoLocation("Truck", allLocations);
        System.out.println(unloader.getName() + " : " + unloader.getLocation());
        System.out.println("Now going to the storage...");
        unloader.gotoLocation("Storage", allLocations);
        System.out.println(unloader.getName() + " : " + unloader.getLocation());

        //Add all unique items to a list
        availableItems = new ArrayList<>();
        for (int i = 0; i < MAX_UNIQUE_ITEMS; i++) {
            availableItems.add(new Item());
        }

        //List of customers
        customers = new ArrayList<>();
    }

    public static void main(String[] args) {
        Supermarket simulation = new Supermarket();
        System.out.println("Supermarket initialized...");

        while (true) { //Update loop
            simulation.newCustomer();

            //Sleep at the end of the loop
            simulation.sleep(1000);
        }
    }

    private void newCustomer() {
        if (customers.size() < MAX_CUSTOMERS) {
            int percent = 0;
            if (customers.size() < MAX_CUSTOMERS * 0.3f) {
                percent = 35;
            } else if (customers.size() < MAX_CUSTOMERS * 0.8f) {
                percent = 25;
            } else {
                percent = 15;
            }

            if (chanceOf(percent)) {
                ArrayList<Stereotype> stereotype;
                do {
                    stereotype = new ArrayList<>();
                    for (Stereotype s : Stereotype.values()) {
                        if (chanceOf(25)) {
                            stereotype.add(s);
                        }
                    }
                } while (stereotype.size() != 1);
                
                customers.add(new Customer("", stereotype.get(0), availableItems));
            }
        }
    }

    private boolean chanceOf(int percent) {
        return (int) (Math.random() * 101) <= percent;
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds); //1000 milliseconds is one second.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
