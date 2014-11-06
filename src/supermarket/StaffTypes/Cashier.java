package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import supermarket.Checkout;
import supermarket.Customer;

public class Cashier extends Staff {

    private Checkout checkout;

    /**
     * Constructor for the Cashier staff member
     *
     * @param name Specify the name of this person
     * @param workplace Specify the workspace of this person
     */
    public Cashier(String name, Vector2f spawnLocation, Checkout checkout) {
        super(name, spawnLocation);
        this.checkout = checkout;
    }

    /**
     * Let the cashier walk to her/his checkout, when she/he arrives open the
     * checkout.
     */
    public void goToCheckout() {
        if (location == checkout.getLocation()) {
            checkout.open(this);
        }
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
            if (checkout.getStatus() == Checkout.Status.CLOSING) {
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
}
