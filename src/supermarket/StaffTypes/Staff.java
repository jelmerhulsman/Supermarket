package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Item;
import supermarket.ObjectInShop;

public class Staff extends ObjectInShop {

    public Staff(String name) {
        super(name, new Vector2f(0, 50), 2f);
        items = new ArrayList<>();
    }
    protected ObjectInShop workplace;
    protected final int maxItems = 10;
    protected ArrayList<Item> items;

    public ObjectInShop getWorkplace() {
        return workplace;
    }
    
    public void doNothing()
    {
        
    }
}
