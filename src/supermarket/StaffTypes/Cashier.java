package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Checkout;
import supermarket.Customer;
import supermarket.Item;
import supermarket.ObjectInShop;

/**
 *
 * @author SDJM
 */
public class Cashier extends Staff {

    private final int PAYMENT_TIME = 3000;

    private enum Action {

        GO_TO_CHECKOUT, WORKING, HELP_STOCKERS
    }
    private Action action;
    private Checkout checkout;

    /**
     * Constructor for the Cashier staff member
     *
     * @param name Specify the name of this person
     * @param workplace Specify the workspace of this person
     */
    public Cashier(String name, Vector2f location, Checkout checkout) {
        super(name, location);

        action = Action.GO_TO_CHECKOUT;
        this.checkout = checkout;
    }

    /**
     * Help the customer.
     */
    public void processCustomer() {
        if (!checkout.noCustomersLeft()) {
            Customer customer = checkout.getFirstCustomer();
            for (Item i : customer.getShoppingBasket()) {
                System.out.println("Cashier " + this.getName() + " -> " + i.getName() + " costs " + i.getPrice() + " and has been added to receipt.");
                sleep(ITEM_INTERACTION_TIME);
                customer.sleep(ITEM_INTERACTION_TIME);
            }

            float toPay = checkout.printReceipt(customer.getShoppingBasket());
            customer.setSaldo(customer.getSaldo() - toPay);
            System.out.println("Customer " + customer.getName() + " -> Pays " + toPay + " and has " + customer.getSaldo() + " left.");

            sleep(PAYMENT_TIME);
            customer.sleep(PAYMENT_TIME);

            checkout.removeFirstCustomer();
        } else if (checkout.isClosing()) {
            checkout.close();
        }
    }

    public void setCheckOut(Checkout checkout) {
        this.checkout = checkout;
    }

    public Checkout getCheckout() {
        return checkout;
    }

    public boolean isWaiting() {
        return (action == Action.HELP_STOCKERS);
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
                        case GO_TO_CHECKOUT:
                            checkout.open();
                            gotoLocation(checkout.getName(), staticLocations);
                            action = Action.WORKING;
                        case WORKING:
                            processCustomer();
                            if (checkout.isClosed()) {
                                checkout.setManned(false);
                                checkout = null;
                                gotoLocation("Storage", staticLocations);
                                action = Action.HELP_STOCKERS;
                            }
                            break;
                    }
                    sleep(500);
                }
            }
        });
        operation.start();
        operation.setName("Cashier " + name);
    }
}
