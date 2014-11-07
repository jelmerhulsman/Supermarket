package supermarket.StaffTypes;

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

    /**
     * Constructor for the Cashier staff member
     *
     * @param name Specify the name of this person
     * @param workplace Specify the workspace of this person
     */
    public Cashier(Checkout checkout) {
        action = Action.GO_TO_CHECKOUT;
        this.checkout = checkout;
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
                while (true) {
                    switch (action) {
                        case GO_TO_CHECKOUT:
                            gotoLocation(checkout.getName(), staticLocations);
                            checkout.open();
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
            }
        });
        operation.start();
    }
}
