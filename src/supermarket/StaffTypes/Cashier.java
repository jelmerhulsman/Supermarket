package supermarket.StaffTypes;

import supermarket.Checkout;
import supermarket.Customer;

public class Cashier extends Staff {

    private Checkout checkout;

    /**
     * Constructor for the Unloader staff member
     *
     * @param name Specify the name of this person
     * @param checkout Specify the workspace of this person
     */
    public Cashier(String name, Checkout checkout) {
        super(name);
        this.workplace = checkout;
        this.checkout = checkout;
    }

    /**
     * Let the cashier walk to her/his checkout, when she/he arrives open the
     * checkout.
     */
    public void goToCheckout() {
        if (this.location == this.workplace.getLocation()) {
            this.checkout.open(this);
        }
    }

    /**
     * Help the customer.
     */
    public void processCustsomer() {
        Customer c = this.checkout.getFirstCustomer();
        if (c != null) {
            float toPay = this.checkout.printReceipt(c.getShoppingCart());
            c.setSaldo(c.getSaldo() - toPay);
            this.checkout.removeFirstCustomer();
        } else {
            if (this.checkout.getStatus() == Checkout.Status.CLOSING) {
                this.checkout.close();
            }
        }
    }
}
