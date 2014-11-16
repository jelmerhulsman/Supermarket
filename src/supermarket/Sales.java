/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;

/**
 *
 * @author Dylankuyjt67hr
 */
public class Sales extends Aisle {
    
    public Sales(String name, Vector2f location, ArrayList<Item.Category> categories, ArrayList<Item> storeItems){
        super(name, location, categories, storeItems);
    }
    
}
