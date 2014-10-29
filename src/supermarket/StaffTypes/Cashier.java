package supermarket.StaffTypes;

import supermarket.Checkout;
import supermarket.Customer;

public class Cashier extends Staff {

    Checkout workplace;

    /**
     * Constructor for the Cashier staff member
     *
     * @param name Specify the name of this person
     * @param workplace Specify the workspace of this person
     */
    public Cashier(String name, Checkout checkout) {
        super(name);
        workplace = checkout;
    }

    /**
     * Let the cashier walk to her/his checkout, when she/he arrives open the
     * checkout.
     */
    public void goToCheckout() {
        if (this.location == this.workplace.getLocation()) {
            this.workplace.open(this);
        }
    }

    /**
     * Help the customer.
     */
    public void processCustsomer() {
        Customer c = this.workplace.getFirstCustomer();
        if (c != null) {
            float toPay = this.workplace.printReceipt(c.getShoppingCart());
            c.setSaldo(c.getSaldo() - toPay);
            this.workplace.removeFirstCustomer();
        } else {
            if (this.workplace.getStatus() == Checkout.Status.CLOSING) {
                this.workplace.close();
            }
        }
    }

    /**
     * Gets the CLASS of the current location. For example "Aisle" or "Storage"
     *
     * @return the class of the current location
     */
    public Checkout getWorkplace() {
        return workplace;
    }
}
