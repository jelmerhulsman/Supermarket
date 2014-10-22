package supermarket;

import java.util.ArrayList;
import supermarket.StaffTypes.Unloader;

public class Storage {
    private ArrayList<Item> items = null;
    private Unloader currentUnloader;
    
    /**
     * creates the storage
     */
    public Storage() {
        
    }
    
    /**
     * returs the current unloader
     * @return unloader
     */
    public Unloader getCurrentUnloader() {
        return currentUnloader;
    }
    
    /**
     * assigns an unloader to the storage
     * @param currentUnloader 
     */
    public void setCurrentUnloader(Unloader currentUnloader) {
        this.currentUnloader = currentUnloader;
    }

    /**
     * returs a list of all the items in teh storage
     * @return items
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    
    /**
     * adds an item to the storage
     * @param item the item to add to the storage
     */
    public void addItem(Item item){
        items.add(item);
    }
    
    /**
     * removes an item from the storage
     * @param item the item to remove
     */
    public void removeItem(Item item){
        items.remove(item);
    }
}
