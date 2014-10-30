package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import java.util.Scanner;
import supermarket.Item.Category;
import supermarket.Customer.Stereotype;
import supermarket.StaffTypes.Cashier;
import supermarket.StaffTypes.Staff;
import supermarket.StaffTypes.Unloader;

/**
 *
 * @author Hulsman
 */
public class Supermarket {

    private final int MAX_CUSTOMERS = 20;
    private final int MIN_STASH = 5;
    private final int MAX_STASH = 25;
    private ArrayList<Aisle> aisles;
    private ArrayList<Checkout> checkouts;
    private ArrayList<Department> departments;
    private ArrayList<Item> order;
    private Storage storage;
    private Truck truck;
    private ArrayList<ObjectInShop> staticLocations;
    private Unloader unloader;
    private Staff staff;
    private ArrayList<Item> availableItems;
    private ArrayList<Cashier> cashier;
    private ArrayList<Item> shopItems;
    private ArrayList<Customer> customers;

    @SuppressWarnings("empty-statement")
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
        aisles.add(new Aisle("Liquor", new Vector2f(40, 40), Item.Category.BEER, Item.Category.LIQUOR, Item.Category.WINE));
        aisles.add(new Aisle("Lunch & Breakfast", new Vector2f(80, 80), Item.Category.BREAD, Item.Category.SPREAD, Item.Category.BREAKFAST));
        aisles.add(new Aisle("Cooling", new Vector2f(20, 20), Item.Category.FROZEN, Item.Category.READY_TO_EAT, Item.Category.DAIRY));
        aisles.add(new Aisle("Luxury", new Vector2f(60, 60), Item.Category.SNACK, Item.Category.SODA, Item.Category.CAFFEINE));
        aisles.add(new Aisle("Durable", new Vector2f(20, 20), Item.Category.SPICES, Item.Category.FOREIGN, Item.Category.PRESERVATION));
        aisles.add(new Aisle("Vegtables & Fruit", new Vector2f(160, 160), Item.Category.VEGTABLES, Item.Category.FRUIT));
        aisles.add(new Aisle("Nonfood", new Vector2f(60, 60), Item.Category.NONFOOD));
        
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

        //Create Staff members
        unloader = new Unloader("Jannes", storage);

        //Assign locations in the shop
        staticLocations = new ArrayList<>();
        staticLocations.addAll(departments);
        staticLocations.addAll(aisles);
        staticLocations.addAll(checkouts);
        staticLocations.add(storage);
        staticLocations.add(truck);

        //Add all unique items to a list
        shopItems = new ArrayList<>();
        shopItems.add(new Item("Heimstel-Jan", 0.80f, Category.BEER));
        shopItems.add(new Item("Ricewaffle", 1.20f, Category.BREAKFAST));
        shopItems.add(new Item("Slurpys", 2.00f, Category.SODA));
        shopItems.add(new Item("Ready 2 Eat Lasagne", 2.50f, Category.READY_TO_EAT));
        shopItems.add(new Item("Nazi-kraut", 2.80f, Category.VEGTABLES));
        shopItems.add(new Item("Tomahawkto", 0.50f, Category.FRUIT));
        shopItems.add(new Item("Moo-Moo Milk", 1.25f, Category.DAIRY));
        shopItems.add(new Item("Lice", 1.00f, Category.FOREIGN));
        shopItems.add(new Item("Ass-Whipe Deluxe", 1.40f, Category.NONFOOD));

        //List of customers
        customers = new ArrayList<>();

        //Choose debugger
        chooseDebugger();
    }

    public static void main(String[] args) {
        Supermarket simulation = new Supermarket();
        System.out.println("Supermarket initialized...");

        while (true) { //Update loop
            simulation.orderLoop();
            simulation.customersLoop();

            //simulation.customersLoop();
            //Sleep at the end of the loop
            simulation.sleep(1000);
        }
    }

    private void orderLoop() {
        ArrayList<Item> orderItems = new ArrayList<>();
        for (Item shopItem : shopItems) {
            int currentStash = storage.getItemCount(shopItem.getName());
            if (currentStash < MIN_STASH) {
                do {
                    orderItems.add(new Item(shopItem));
                    currentStash++;
                } while (currentStash < MAX_STASH);
            }
        }

        if (!orderItems.isEmpty()) {
            truck.order(orderItems);

            unloader = new Unloader("Jannes", storage);
            unloader.getItemsFromTruck(staticLocations);
        }
    }

    private void customersLoop() {
        addEnteringCustomers();

        ArrayList<Customer> leavingCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            boolean stopUpdating = customer.update(staticLocations, checkouts);
            if (stopUpdating) {
                leavingCustomers.add(customer);
            }
        }

        customers.removeAll(leavingCustomers);
    }

    private ArrayList<Customer> addEnteringCustomers() {
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

                customers.add(new Customer("", stereotype.get(0), shopItems));
            }
        }

        return customers;
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

    private void chooseDebugger() {
        boolean loop = true;
        do {
            System.out.print("DEBUGGER: ");
            String debugger = new Scanner(System.in).next();
            debugger = debugger.trim();
            debugger = debugger.toUpperCase();

            switch (debugger) {
                case "MORENO":
                    Moreno();
                    loop = false;
                    break;
                case "DYLAN":
                    Dylan();
                    loop = false;
                    break;
                case "SANDER":
                    Sander();
                    loop = false;
                    break;
                case "JELMER":
                    Jelmer();
                    loop = false;
                    break;
                case "BREAK":
                    loop = false;
                    break;
            }
        } while (loop);
    }

    private void Moreno() { //Moreno's testing area
    }

    private void Dylan() { //Dylan's testing area
        cashier = new ArrayList<>();
        Staff johanna = new Staff("Johanna", checkouts.get(0)); 
        cashier.add(johanna.getCashier());        
        
        cashier.get(0).gotoLocation(cashier.get(0).getWorkplace().getName(), staticLocations);
        cashier.get(0).getCashier().goToCheckout();
    }

    private void Sander() { //Sander's testing area
    }

    private void Jelmer() { //Jelmer's testing area
    }
}
