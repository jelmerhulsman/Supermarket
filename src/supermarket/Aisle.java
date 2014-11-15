package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item.Category;

/**
 *
 * @author SDJM
 */
public class Aisle extends ObjectInShop {

    private final int ITEM_LIMIT_PER_SHELVE = 25;
    private ArrayList<Category> categories;
    private ArrayList<Item> items;
    private ArrayList<Item> stock;
    private boolean changed;
    private boolean manned;

    public Aisle(String name, Vector2f location, ArrayList<Category> categories, ArrayList<Item> storeItems) {
        super(name, location);
        
        this.categories = new ArrayList<>();
        this.categories.addAll(categories);
        
        createListOfAisleItems(categories, storeItems);
        stock = new ArrayList<>();
        changed = false;
        manned = false;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }
    
    public boolean  isManned() {
        return manned;
    }
    
    public void setManned(boolean manned) {
        this.manned = manned;
    }
    
    /**
     * Creates a list of items for this aisle
     * @param categories the category
     * @param storeItems the collection of items
     */
    private void createListOfAisleItems(ArrayList<Category> categories, ArrayList<Item> storeItems)
    {
        items = new ArrayList<>();
        
        for (Item item : storeItems)
        {
            if (categories.contains(item.getCategory()))
            {
                items.add(item);
            }
        }
    }

    /**
     * Gets all the items that are in the Aisle
     *
     * @return a list of all the items
     */
    public ArrayList<Item> getItems() {
        return stock;
    }
    
    /**
     * returns all the item names in this aisle
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

    /**
     * returns the total amount of items
     * @param item
     * @return 
     */
    public int getItemCount(Item item) {
        int counter = 0;
        for (Item i : stock) {
            if (item.getName().equals(i.getName())) {
                counter++;
            }
        }
        return counter;
    }
    
    /**
     * get the item count by checking the itemname
     * @param itemname
     * @return 
     */
    public int getItemCount(String itemname){
        int counter = 0;
        for (Item i : stock) {
            if (i.getName().equals(itemname)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * picks an item from this aisle
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
        item.setAvailable(true);
        stock.add(item);
        changed = true;
    }
    
    public boolean fullShelve(Item item) {
        int counter = 0;
        for (Item i : stock)
        {
            if (item.getName().equals(i.getName()))
            {
                counter++;
            }
        }
        
        return (counter == ITEM_LIMIT_PER_SHELVE);
    }
}
