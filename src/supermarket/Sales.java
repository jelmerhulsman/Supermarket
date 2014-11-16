package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item.Category;

/**
 *
 * @author SDJM
 */
public class Sales extends Aisle {

    public Sales(String name, Vector2f location, ArrayList<Category> categories, ArrayList<Item> storeItems) {
        super(name, location, categories, storeItems);
    }

    public void newSales(ArrayList<Item> aisleItems) {
        ArrayList<Item> newItems = new ArrayList<>();

        do {
            for (Item item : aisleItems) {
                if (chanceOf(100 / aisleItems.size()) && !items.contains(item)) {
                    newItems.add(item);
                    
                    if(newItems.size() == 2) {
                        break;
                    }
                }
            }
        } while (newItems.size() < 2);
        
        items = new ArrayList<>();
        items.addAll(newItems);
    }

    /**
     * Used to make a percentage chance
     *
     * @param percent The percentage you are using
     * @return either true or false
     */
    private boolean chanceOf(int percent) {
        return (int) (Math.random() * 101) <= percent;
    }
}
