package supermarket;

import java.util.List;
import supermarket.Item.Category;
import supermarket.StaffTypes.AisleLoader;

public class Aisle {
    
    private final int ITEM_LIMIT_AISLE = 50;
    
    public enum Status {

        EMPTY, STOCKED, FULL
    }
    
    private String name;
    private Status status;
    private AisleLoader stocker;
    private List<Category> categories;
    private List<Item> items;
    private List<Customer> customers;

    public Aisle(String name, Category category1, Category category2) {
        this.name = name;
        status = Status.EMPTY;
        stocker = null;
        categories = null;
        items = null;
        customers = null;
        
        categories.add(category1);
        categories.add(category2);
    }

    /**
     * Gets the name of this Aisle
     *
     * @return The name e.g. "Snack Aisle" or w/e
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a Loader staff member to this Aisle
     *
     * @param currentLoader The loader that you are willing to assign
     */
    public void setCurrentLoader(AisleLoader currentLoader) {
        this.stocker = currentLoader;
    }

    /**
     * Gets the current Loader staff member of this Aisle
     *
     * @return the current loader
     */
    public AisleLoader getCurrentLoader() {
        return stocker;
    }

    /**
     * Gets all the items that are in the Aisle
     *
     * @return a list of all the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Gets the categories of this Aisle
     *
     * @return two categories that are assigned to this Aisle in a list
     */
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * Gets the status of this Aisle.
     *
     * @return This Aisle can be FULL, EMPTY or just NORMAL. ( NORMAL meaning
     * that it just is operating properly )
     */
    public Status getStatus() {
        return status;
    }

    /**
     * This will fill the Aisle with the specified item and sets the Status of
     * this Aisle to LOADED
     *
     * @param item The item you are willing to add to this Aisle
     */
    public void loadAisle(Item item) {
        items.add(item);
        item.setStatus(Item.Status.LOADED);
    }

    /**
     * Will check the list of items in this aisle for being at it's limit or
     * being empty and sets the Status accordingly.
     */
    public void checkAisle() {
        if (items.size() == ITEM_LIMIT_AISLE) {
            status = Status.FULL;
        } else if (items.isEmpty()) {
            status = Status.EMPTY;
        } else {
            status = Status.STOCKED;
        }
    }
}
