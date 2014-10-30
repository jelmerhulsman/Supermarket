package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import java.util.Scanner;
import supermarket.Item.Category;
import supermarket.Customer.Stereotype;
import supermarket.Item.Status;
import supermarket.StaffTypes.Cashier;
import supermarket.StaffTypes.Staff;
import supermarket.StaffTypes.Unloader;

/**
 *
 * @author Hulsman
 */
public class Supermarket {

    private final int MAX_CUSTOMERS = 20;
    private ArrayList<Aisle> aisles;
    private ArrayList<Checkout> checkouts;
    private ArrayList<Department> departments;
    private ArrayList<Item> items;
    private Storage storage;
    private Truck truck;
    private ArrayList<ObjectInShop> staticLocations;
    private Unloader unloader;
    private Staff staff;
    private ArrayList<Cashier> cashier;
    private ArrayList<Item> shopItems;
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
        staticLocations = new ArrayList<>();
        staticLocations.addAll(departments);
        staticLocations.addAll(aisles);
        staticLocations.addAll(checkouts);
        staticLocations.add(storage);
        staticLocations.add(truck);

        //Add all unique items to a list
        shopItems = new ArrayList<>();
        shopItems.add(new Item("Heimstel-Jan", Category.BEER, 0.80f, Status.VIRTUAL));
        shopItems.add(new Item("Ricewaffle", Category.BREAKFAST, 1.20f, Status.VIRTUAL));
        shopItems.add(new Item("Slurpys", Category.SODA, 2.00f, Status.VIRTUAL));
        shopItems.add(new Item("Ready 2 Eat Lasagne", Category.READY_TO_EAT, 2.50f, Status.VIRTUAL));
        shopItems.add(new Item("Nazi-kraut", Category.VEGTABLES, 2.80f, Status.VIRTUAL));
        shopItems.add(new Item("Tomahawkto", Category.FRUIT, 0.50f, Status.VIRTUAL));
        shopItems.add(new Item("Moo-Moo Milk", Category.DAIRY, 1.25f, Status.VIRTUAL));
        shopItems.add(new Item("Lice", Category.FOREIGN, 1.00f, Status.VIRTUAL));
        shopItems.add(new Item("Ass-Whipe Deluxe", Category.NONFOOD, 1.40f, Status.VIRTUAL));

        //List of customers
        customers = new ArrayList<>();

        //Choose debugger
        chooseDebugger();
    }

    public static void main(String[] args) {
        Supermarket simulation = new Supermarket();
        System.out.println("Supermarket initialized...");

        while (true) { //Update loop
            simulation.customersLoop();

            //Sleep at the end of the loop
            simulation.sleep(1000);
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

    private void staffLoop() {
        if (storage.getItems().size() < 10) {
            truck.order(items);
        }
        if (!truck.getItems().isEmpty()) {
            unloader.getItemsFromTruck(staticLocations);
        }
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
        unloader = new Unloader("Jannes", storage);

        items = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            for (Item shopItem : shopItems)
            {
                if (shopItem.getName() == "Heimstel-Jan")
                {
                    Item orderItem = new Item(shopItem.getName(), shopItem.getCategory(), shopItem.getPrice());
                    items.add(orderItem);
                }
            }
        }

        truck.order(items);
        unloader.getItemsFromTruck(staticLocations);
    }

    private void Dylan() { //Dylan's testing area
        cashier = new ArrayList<>();
        cashier.add(new Cashier("Johanna", checkouts.get(0)));

        //cashier.get(0).gotoLocation(cashier.get(0).getWorkplace().getLocationName(), staticLocations);
        //cashier.get(0).openCheckout();
    }

    private void Sander() { //Sander's testing area
    }

    private void Jelmer() { //Jelmer's testing area
    }
}
