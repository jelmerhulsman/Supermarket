package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Customer;
import supermarket.Department;
import supermarket.Item;
import supermarket.ObjectInShop;

/**
 *
 * @author SDJM
 */
public class Artisan extends Staff {

    private final int ITEM_CRAFT_TIME = 5000;

    private enum Action {

        CRAFT_ITEM, WAITING
    }
    private Action action;
    private Department department;

    /**
     * Constructor for the Artisan staff member
     *
     * @param name Specify the name of this person
     * @param department Specify the workplace of this person
     * @param location Specify the starting location of this person
     */
    public Artisan(String name, Vector2f location, Department department) {
        super(name, location);

        action = Action.WAITING;
        this.department = department;
    }

    /**
     * Used by the artisian to make items in front of the customer
     *
     * @param customer The customer this artisian is working for
     * @return A fresh new item which corresponds with the customer's needs
     */
    public Item craftItem(Customer customer) {
        for (Item item : department.getItems()) {
            if (customer.getShoppingList().contains(item)) {
                System.out.println("Artisan " + name + " is now crafting " + item.getName() + "...");
                sleep(ITEM_CRAFT_TIME);
                customer.sleep(ITEM_CRAFT_TIME);
                return new Item(item);
            }
        }

        return null;
    }

    /**
     * this function will be looped over and over
     *
     * @param staticLocations the collection of locations
     */
    @Override
    public void update(final ArrayList<ObjectInShop> staticLocations) {
        operation = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    switch (action) {
                        case CRAFT_ITEM:
                            Customer customer = department.getFirstCustomer();
                            Item item;
                            do {
                                item = craftItem(customer);
                                if (item != null) {
                                    sleep(ITEM_INTERACTION_TIME);
                                    customer.sleep(ITEM_INTERACTION_TIME);
                                    customer.addItemToBasket(item);
                                    System.out.println("Artisan " + name + " -> Gave Customer " + customer.getName() + " newly crafted " + item.getName() + ".");
                                }
                            } while (item != null);
                            department.removeFirstCustomer();

                            if (department.noCustomersLeft()) {
                                action = Action.WAITING;
                            }
                            break;
                        case WAITING:
                            if (locationObject != department) {
                                gotoLocation(department.getName(), staticLocations);
                            } else if (!department.noCustomersLeft()) {
                                action = Action.CRAFT_ITEM;
                            }
                            break;
                    }
                    sleep(500);
                }
            }
        });
        operation.start();
        operation.setName("Artisan " + name);
    }
}
