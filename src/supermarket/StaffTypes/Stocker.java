package supermarket.StaffTypes;

import java.util.ArrayList;
import supermarket.Aisle;
import supermarket.Item;
import supermarket.Item.Category;
import supermarket.ObjectInShop;
import supermarket.Storage;

public class Stocker extends Staff {

    Storage storage;
    Aisle aisle;

    public Stocker(String name, Storage storage) {
        super(name);
        workplace = storage;
        this.storage = storage;
    }

    public void getItemsFromStorage(ArrayList<ObjectInShop> locations, Category category) {
        this.action = action.WALKING;
        this.targetLocationName = "Storage";
        doThings(locations);

        if (!storage.getItems().isEmpty()) {
            System.out.println("STAFF MEMBER " + name + " is picking up items from storage...");
            items.addAll(storage.getItems(maxItems));
        } else {
            doNothing();
        }
        putItemsInAisle(locations);

    }

    public void putItemsInAisle(ArrayList<ObjectInShop> locations) {
        for (ObjectInShop o : locations) {
            if (o instanceof Aisle) {
                aisle = (Aisle) o;
                if (aisle.getCategories().contains(items.get(0).getCategory())) {
                    aisle = (Aisle) o;
                }
            }
        }

        this.action = action.WALKING;
        this.targetLocationName = aisle.getName();
        doThings(locations);

        for (Item i : items) {
            System.out.println("STAFF MEMBER " + name + " is putting item" + i.getName() + " in aisle " + aisle.getName());
            aisle.loadAisle(i);
        }
    }
}
