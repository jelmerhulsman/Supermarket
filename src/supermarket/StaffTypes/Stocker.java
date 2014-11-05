package supermarket.StaffTypes;

import java.util.ArrayList;
import supermarket.Aisle;
import supermarket.Item;
import supermarket.Item.Category;
import supermarket.ObjectInShop;
import supermarket.Storage;

public class Stocker extends Staff {

    private Storage storage;
    private Aisle aisle;

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
            items.addAll(storage.getItems(maxItems, category));
        } else {
            doNothing();
        }
        putItemsInAisle(locations);
    }

    public void setAisle(Aisle aisle) {
        this.aisle = aisle;
    }

    public void putItemsInAisle(ArrayList<ObjectInShop> locations) {
        ArrayList<Aisle> aisles = new ArrayList<>();
        for (Item i : items) {
            for (ObjectInShop o : locations) {
                if (o instanceof Aisle) {
                    aisle = (Aisle) o;
                    if (aisle.getCategories().contains(i.getCategory())) {
                        aisles.add(aisle);
                    }
                }
            }
        }

        for (Aisle a : aisles) {
            this.action = action.WALKING;
            this.targetLocationName = a.getName();
            doThings(locations);
            for (Item i : items) {
                if (a.getCategories().contains(i.getCategory())) {
                    System.out.println("STAFF MEMBER " + name + " is putting item" + i.getName() + " in aisle " + a.getName());
                    a.loadAisle(i);
                }
            }
        }
    }
}
