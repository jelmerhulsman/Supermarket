package supermarket.StaffTypes;

import java.util.ArrayList;
import supermarket.Item;
import supermarket.Item.Status;
import supermarket.PartOfShop;
import supermarket.Storage;
import supermarket.Truck;

public class Unloader extends Staff {

    /**
     * Constructor for the Unloader staff member
     *
     * @param name Specify the name of this person
     * @param storage Specify the workplace of this person
     */
    public Unloader(String name, Storage storage) {
        super(name);
        this.workplace = storage;
    }

    /**
     * Gets the maximum # of items this person can carry from the truck and puts it in the Storage
     *
     * @param locations This method needs all the possible location objects in
     * an arraylist
     */
    public void getItemsFromTruck(ArrayList<PartOfShop> locations) {
        gotoLocation("Truck", locations);
        Truck truck = (Truck) getCurLocObject();
        if(truck.getCurUnloader() == null)
        {
            System.out.println("STAFF MEMBER " + name + " is picking up items from truck...");
            truck.setCurUnloader(this);
            sleep(5000);
            
            items.addAll(truck.unload(maxItems));
        }
        else if(truck.getCurUnloader() == this)
        {
            System.out.println("STAFF MEMBER " + name + " is picking up items from truck...");
            sleep(5000);
            items.addAll(truck.unload(maxItems));
        }
        else
        {
            getItemsFromTruck(locations);
        }
        truck.setCurUnloader(null);
        gotoLocation("Storage", locations);
        
        Storage storage = (Storage) getCurLocObject();
        for (Item i : items) {
            System.out.println("STAFF MEMBER " + name + " is adding item " + i.getName() + " to the storage.");
            i.setStatus(Status.IN_STORAGE);
            storage.addItem(i);
            sleep(1000);
        }
        
    }
    

}
