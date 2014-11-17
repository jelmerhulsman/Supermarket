package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item.Category;

/**
 *
 * @author SDJM
 */
public class Aisle extends ObjectInShop {

    private final int STOCK_LIMIT_PER_SHELVE = 25;
    private final int MINIMUM_STOCK_PER_SHELVE = 5;
    private ArrayList<Category> categories;
    protected ArrayList<Item> items;
    protected ArrayList<Item> stock;
    private boolean changed;
    private boolean manned;

    /**
     * Make a new aisle
     *
     * @param name The name of this aisle
     * @param location The location in the store
     * @param categories List of categories which this aisle will contain
     * @param aisleItems All the available items in the supermarket
     */
    public Aisle(String name, Vector2f location, ArrayList<Category> categories, ArrayList<Item> aisleItems) {
        super(name, location);

        this.categories = new ArrayList<>();
        this.categories.addAll(categories);

        createListOfAisleItems(categories, aisleItems);
        stock = new ArrayList<>();
        changed = false;
        manned = false;
    }

    /**
     * Gets wether aisle has changed or not
     *
     * @return True if changes have been made
     */
    public boolean isChanged() {
        return changed;
    }

    /**
     * Sets boolean for changed
     *
     * @param changed True if changes have been made
     */
    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    /**
     * Gets wether aisle is manned or not
     *
     * @return True if stocker has this aisle
     */
    public boolean isManned() {
        return manned;
    }

    /**
     * Sets boolean for manned
     *
     * @param manned True if stocker has this aisle
     */
    public void setManned(boolean manned) {
        this.manned = manned;
    }

    /**
     * Gets boolean wether aisle needs stock
     *
     * @return True if aisle needs stock
     */
    public boolean needsStock() {
        if (items == null) {
            return false;
        }

        for (Item item : items) {
            if (getStockCount(item) < MINIMUM_STOCK_PER_SHELVE) {
                return true;
            }
        }

        return false;
    }

    /**
     * Gets item with the lowest stock
     *
     * @return Item
     */
    public Item getLowestStockItem() {
        Item temp = null;
        int count = STOCK_LIMIT_PER_SHELVE;

        for (Item item : items) {
            if (getStockCount(item) < count) {
                temp = item;
            }
        }

        return temp;
    }

    /**
     * Creates a list of items for this aisle
     *
     * @param categories the category
     * @param aisleItems the collection of items
     */
    private void createListOfAisleItems(ArrayList<Category> categories, ArrayList<Item> aisleItems) {
        items = new ArrayList<>();

        for (Item item : aisleItems) {
            if (categories.contains(item.getCategory())) {
                items.add(item);
            }
        }
    }

    /**
     * Gets all the stock that are in the Aisle
     *
     * @return a list of all the items
     */
    public ArrayList<Item> getStock() {
        return stock;
    }

    /**
     * Gets all the items that are in the Aisle
     *
     * @return a list of all the items
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * returns all the item names in this aisle
     *
     * @return
     */
    public ArrayList<String> getItemNames() {
        ArrayList<String> itemNames = new ArrayList<>();

        for (Item item : items) {
            itemNames.add(item.getName());
        }

        return itemNames;
    }

    /**
     * get the item count by checking the itemname
     *
     * @param itemname
     * @return counter
     */
    public int getStockCount(Item item) {
        try {
            int counter = 0;
            for (Item i : stock) {
                if (i.getName().equals(item.getName())) {
                    counter++;
                }
            }
            return counter;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * picks an item from this aisle
     *
     * @param item the item you are going to pick
     * @return
     */
    public Item pickFromShelve(Item item) {
        for (Item i : stock) {
            if (i.getName().equals(item.getName())) {
                Item temp = i;
                stock.remove(i);
                changed = true;
                return temp;
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
     * This will fill the Aisle with the specified item and sets the Status of
     * this Aisle to LOADED
     *
     * @param item The item you are willing to add to this Aisle
     */
    public void loadAisle(Item item) {
        item.setAvailable(false);
        stock.add(item);
        changed = true;
    }

    /**
     * Returns wether this aisle is full or not
     *
     * @param item the specified item
     * @return
     */
    public boolean fullShelve(Item item) {
        int counter = 0;
        for (Item i : stock) {
            if (item.getName().equals(i.getName())) {
                counter++;
            }
        }

        return (counter == STOCK_LIMIT_PER_SHELVE);
    }
}
