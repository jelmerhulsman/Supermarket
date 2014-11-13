package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Checkout;
import supermarket.Checkout.Status;
import supermarket.Customer;
import supermarket.Item;
import supermarket.ObjectInShop;

public class Cashier extends Staff {

    private final int PAYMENT_TIME = 3000;

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
    public Cashier(String name, Vector2f location, Checkout checkout) {
        super(name, location);

        action = Action.GO_TO_CHECKOUT;
        this.checkout = checkout;
    }

    /**
     * Help the customer.
     */
    public void processCustomer() {
        Customer c = checkout.getFirstCustomer();
        if (c != null) {
            for (Item i : c.getShoppingBasket()) {
                System.out.println("Cashier " + this.getName() + " -> " + i.getName() + " costs " + i.getPrice() + " and has been added to receipt.");
                sleep(ITEM_INTERACTION_TIME);
                c.sleep(ITEM_INTERACTION_TIME);
            }
            
            float toPay = checkout.printReceipt(c.getShoppingBasket());
            c.setSaldo(c.getSaldo() - toPay);
            System.out.println("Customer " + c.getName() + " -> Pays " + toPay + " and has " + c.getSaldo() + " left.");

            sleep(PAYMENT_TIME);
            c.sleep(PAYMENT_TIME);

            checkout.removeFirstCustomer();
        } else {
            if (checkout.getStatus() == Status.CLOSING) {
                checkout.close();
            }
        }
    }

    public void setCheckOut(Checkout checkout) {
        this.checkout = checkout;
    }

    public boolean isWaiting() {
        return (action == Action.WAITING);
    }

    /**
     * This method will be looped over and over
     *
     * @param staticLocations the collection of all the locations
     */
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
                            processCustomer();
                            if (checkout.getStatus() == Status.CLOSED) {
                                checkout = null;
                                gotoLocation("Storage", staticLocations);
                                action = Action.WAITING;
                            }
                            break;
                        case WAITING:
                            if (checkout != null) {
                                action = Action.GO_TO_CHECKOUT;
                            }

                            break;
                    }
                }
            }
        });
        operation.start();
        operation.setName("Cashier " + name);
    }
}
