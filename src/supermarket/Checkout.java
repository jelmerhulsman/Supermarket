package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.StaffTypes.Cashier;

public class Checkout extends ObjectInShop {

    public enum Status {

        CLOSED, CLOSING, OPEN, CROWDED
    };
    private int number;
    private Status status;
    private Cashier cashier;
    private ArrayList<Customer> customers;

    public Checkout(int number, Vector2f location) {
        super("Checkout " + number, location);
        this.number = number;
        status = Status.CLOSED;
        cashier = null;
        customers = new ArrayList<>();
    }

    /**
     * Let cashier open the checkout
     *
     * @param cashier
     */
    public void open(Cashier cashier) {
        status = Status.OPEN;
        this.cashier = cashier;
        customers = new ArrayList<>();
    }

    /**
     * Let cashier close the checkout
     *
     * @return cashier
     */
    public Cashier close() {
        Cashier c = cashier;

        status = Status.CLOSED;
        cashier = null;
        customers = new ArrayList<>();

        return c;
    }

    /**
     * Returns unique checkout number
     *
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Returns the size of the customers in lane
     *
     * @return size
     */
    public int getCustomersCount() {
        return customers.size();
    }

    /**
     * Adds customer to the end of the lane
     *
     * @param customer
     */
    public void addCustomer(Customer customer) {
        this.customers.add(customer);

        if (customers.size() > 3) {
            status = Status.CROWDED;
        } else {
            status = Status.OPEN;
        }
    }

    /**
     * Returns the first customer in lane
     *
     * @return customer
     */
    public Customer getFirstCustomer() {
        if (customers.size() > 0) {
            return customers.get(0);
        } else {
            return null;
        }
    }

    /**
     * Removes the first customer in lane
     */
    public void removeFirstCustomer() {
        customers.remove(0);

        if (customers.size() > 3) {
            status = Status.CROWDED;
        } else {
            status = Status.OPEN;
        }
    }

    /**
     * Returns checkout status
     *
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets checkout status closing
     */
    public void closing() {
        this.status = Status.CLOSING;
    }

    /**
     * Returns price of all the customer's items
     *
     * @param items
     * @return price
     */
    public float printReceipt(ArrayList<Item> items) {
        float price = 0;

        for (Item i : items) {
            price += i.getPrice();
        }

        return price;
    }
}
