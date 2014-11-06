package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Checkout;
import supermarket.Checkout.Status;
import supermarket.Customer;
import supermarket.ObjectInShop;

/**
 *
 * @author SDJM
 */
public class Cashier extends Staff {

    private enum Action {

        GO_TO_CHECKOUT, WORKING, WAITING
    }
    private Action action;
    private Checkout checkout;
    private Cashier me;

    /**
     * Constructor for the Cashier staff member
     *
     * @param name Specify the name of this person
     * @param workplace Specify the workspace of this person
     */
    public Cashier(String name, Vector2f spawnLocation, Checkout checkout) {
        super(name, spawnLocation);

        action = Action.GO_TO_CHECKOUT;
        this.checkout = checkout;
        me = this;
    }

    /**
     * Help the customer.
     */
    public void processCustsomer() {
        Customer c = checkout.getFirstCustomer();
        if (c != null) {
            float toPay = checkout.printReceipt(c.getShoppingCart());
            c.setSaldo(c.getSaldo() - toPay);
            checkout.removeFirstCustomer();
        } else {
            if (checkout.getStatus() == Status.CLOSING) {
                checkout.close();
            }
        }
    }

    /**
     * Gets the CLASS of the current location. For example "Aisle" or "Storage"
     *
     * @return the class of the current location
     */
    public Checkout getCheckOut() {
        return checkout;
    }

    @Override
    public void update(final ArrayList<ObjectInShop> staticLocations) {
        operation = new Thread(new Runnable() {
            @Override
            public void run() {
                switch (action) {
                    case GO_TO_CHECKOUT:
                        gotoLocation(checkout.getName(), staticLocations);
                        checkout.open(me);
                        action = Action.WORKING;
                    case WORKING:
                        processCustsomer();
                        if (checkout.getStatus() == Status.CLOSED) {
                            action = Action.WAITING;
                        }
                        break;
                    case WAITING:
                        gotoLocation("Storage", staticLocations);
                        break;
                }
            }
        });
        operation.start();
    }
}
