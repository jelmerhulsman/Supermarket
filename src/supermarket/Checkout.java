package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author SDJM
 */
public class Checkout extends ObjectInShop {

    final int CROWDED_SIZE = 3;
    
    public enum Status {

        CLOSED, CLOSING, OPEN, CROWDED
    };
    private int number;
    private Status status;
    private ArrayList<Customer> customers;

    public Checkout(int number, Vector2f location) {
        super("Checkout " + number, location);
        this.number = number;
        status = Status.CLOSED;
        customers = new ArrayList<>();
    }

    public boolean isClosed() {
        return (status == Status.CLOSED);
    }

    public boolean isClosing() {
        return (status == Status.CLOSING);
    }

    public boolean isOpen() {
        return (status == Status.OPEN);
    }

    public boolean isCrowded() {
        return (status == Status.CROWDED);
    }

    /**
     * Let cashier open the checkout
     *
     * @param cashier
     */
    public void open() {
        status = Status.OPEN;
        customers = new ArrayList<>();
    }

    /**
     * Let cashier close the checkout
     */
    public void close() {
        status = Status.CLOSED;
        customers = new ArrayList<>();
    }

    /**
     * Sets checkout status closing
     */
    public void closing() {
        this.status = Status.CLOSING;
    }

    /**
     * Returns unique checkout number
     *
     * @return number
     */
    public int getNumber() {
        return number;
    }

    public boolean noCustomersLeft() {
        return customers.isEmpty();
    }
    
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

        if (customers.size() >= CROWDED_SIZE) {
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
