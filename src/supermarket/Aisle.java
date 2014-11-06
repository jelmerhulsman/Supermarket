package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item.Category;
import supermarket.StaffTypes.Stocker;

/**
 *
 * @author SDJM
 */
public class Aisle extends ObjectInShop {

    private final int ITEM_LIMIT_AISLE = 50;

    public enum Status {

        EMPTY, STOCKED, FULL
    }
    private Status status;
    private Stocker stocker;
    private ArrayList<Category> categories;
    private ArrayList<Item> items;
    private ArrayList<Customer> customers;

    public Aisle(String name, Vector2f location, Category category1) {
        super(name, location);
        this.name = name;
        status = Status.EMPTY;
        stocker = null;
        categories = new ArrayList<>();
        items = new ArrayList<>();
        customers = new ArrayList<>();

        categories.add(category1);
    }

    public Aisle(String name, Vector2f location, Category category1, Category category2) {
        super(name, location);
        this.name = name;
        status = Status.EMPTY;
        stocker = null;
        categories = new ArrayList<>();
        items = new ArrayList<>();
        customers = new ArrayList<>();

        categories.add(category1);
        categories.add(category2);
    }

    public Aisle(String name, Vector2f location, Category category1, Category category2, Category category3) {
        super(name, location);
        this.name = name;
        status = Status.EMPTY;
        stocker = null;
        categories = new ArrayList<>();
        items = new ArrayList<>();
        customers = new ArrayList<>();

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
    }

    /**
     * Sets a Loader staff member to this Aisle
     *
     * @param currentLoader The loader that you are willing to assign
     */
    public void setCurrentLoader(Stocker stocker) {
        this.stocker = stocker;
    }

    /**
     * Gets the current Loader staff member of this Aisle
     *
     * @return the current loader
     */
    public Stocker getCurrentLoader() {
        return stocker;
    }

    /**
     * Gets all the items that are in the Aisle
     *
     * @return a list of all the items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    public int getItemCount(Item item) {
        int counter = 0;
        for (Item i : items) {
            if (i == item) {
                counter++;
            }
        }

        return counter;
    }

    public Item pickFromShelve(Item item) {
        for (Item i : items) {
            if (i == item) {
                items.remove(i);
                return (i);
            }
        }

        return null;
    }

    /**
     * Gets the categories of this Aisle
     *
     * @return two categories that are assigned to this Aisle in a list
     */
    public ArrayList<Category> getCategories() {
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
