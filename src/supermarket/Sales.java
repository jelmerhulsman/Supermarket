package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import java.util.Arrays;
import supermarket.Item.Category;

/**
 *
 * @author SDJM
 */
public class Sales extends Aisle {

    private boolean stocked;

    public Sales(String name, Vector2f location, ArrayList<Category> aisleCategories) {
        super(name, location, aisleCategories, new ArrayList<Item>());
        stocked = true;
    }

    /**
     * Creates new sales for this sales aisle
     *
     * @param aisleItems
     */
    public void giveNewSales(ArrayList<Item> aisleItems) {
        ArrayList<Item> newItems = new ArrayList<>();

        do {
            for (Item item : aisleItems) {
                if (chanceOf(100 / aisleItems.size()) && !items.contains(item)) {
                    newItems.add(item);

                    if (newItems.size() == 2) {
                        break;
                    }
                }
            }
        } while (newItems.size() < 2);

        items = new ArrayList<>();
        items.addAll(newItems);
        stocked = false;
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

    /**
     * Gets a boolean wether the sales was fully stocked or not
     *
     * @return
     */
    public boolean isStocked() {
        return stocked;
    }

    /**
     * Sets the sales aisle as stocked when shelves are full
     *
     * @param stocked
     */
    public void stocked() {
        if (fullShelve(items.get(0)) && fullShelve(items.get(1))) {
            stocked = true;
            System.out.println(name + " -> New sales now available: " + items.get(0).getName() + " & " + items.get(1).getName());
        }
    }
}
