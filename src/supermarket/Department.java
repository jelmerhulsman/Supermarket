package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item.Category;

/**
 *
 * @author SDJM
 */
public class Department extends ObjectInShop {

    private ArrayList<Item> items;
    private ArrayList<Customer> customers;

    public Department(String name, Vector2f location, Category category, ArrayList<Item> storeItems) {
        super(name, location);

        createListOfDepartmentItems(category, storeItems);
        customers = new ArrayList<>();
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }
    
     /**
     * returns all the item names in this department
     * @return 
     */
    public ArrayList<String> getItemNames() {
        ArrayList<String> itemNames = new ArrayList<>();
        
        for (Item item : items)
        {
            if (!itemNames.contains(item.getName()))
            {
                itemNames.add(item.getName());
            }
        }
        return itemNames;
    }
    
    public boolean noCustomersLeft() {
        return customers.isEmpty();
    }

    private void createListOfDepartmentItems(Category category, ArrayList<Item> storeItems) {
        items = new ArrayList<>();

        for (Item item : storeItems) {
            if (category == item.getCategory()) {
                items.add(item);
            }
        }
    }
    
    /**
     * Adds customer to the end of the lane
     *
     * @param customer
     */
    public void addCustomer(Customer customer) {
        this.customers.add(customer);
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
        Customer customer = customers.get(0);
        customer.helped();
        customers.remove(customer);
    }
}
