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
    private Stocker stocker;
    private ArrayList<Category> categories;
    private ArrayList<Item> aisleItems;
    private ArrayList<Item> items;

    public Aisle(String name, Vector2f location, ArrayList<Category> categories, ArrayList<Item> storeItems) {
        super(name, location);
        this.name = name;
        stocker = null;
        
        this.categories = new ArrayList<>();
        this.categories.addAll(categories);
        
        this.aisleItems = createListOfAisleItems(categories, storeItems);
        
        items = new ArrayList<>();
    }
    
    /**
     * Creates a list of items for this aisle
     * @param categories the category
     * @param storeItems the collection of items
     * @return 
     */
    private ArrayList<Item> createListOfAisleItems(ArrayList<Category> categories, ArrayList<Item> storeItems)
    {
        ArrayList<Item> tempArrayList = new ArrayList<>();
        
        for (Item item : storeItems)
        {
            if (categories.contains(item.getCategory()))
            {
                tempArrayList.add(item);
            }
        }
        
        return tempArrayList;
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
    
    /**
     * returns all the item names in this aisle
     * @return 
     */
    public ArrayList<String> getItemNames() {
        ArrayList<String> itemNames = new ArrayList<>();
        
        for (Item item : aisleItems)
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
        for (Item i : items) {
            if (i == item) {
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
        for (Item i : items) {
            if (i.getName() == itemname) {
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
        for (Item i : items) {
            if (i.getName().equals(item.getName())) {
                Item temp = i;
                items.remove(i);
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
        items.add(item);
    }
}
