package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Checkout;
import supermarket.Item;
import supermarket.Person;
import supermarket.Storage;
import supermarket.Truck;

/**
 *
 * @author SDJM
 */
public class Staff extends Person {

    private Cashier cashier;
    private Unloader unloader;
    private Stocker stocker;
    private String function;
    protected final int MAX_ITEMS = 10;
    protected ArrayList<Item> items;
    
    public Staff() {
        
    }
    
    private Staff(String name, Vector2f spawnLocation) {
        super(name, spawnLocation);
        speed = 2f;
        items = new ArrayList<>();
    }

    /**
     * Constructor for stafftype cashier.
     *
     * @param name
     * @param checkout
     */
    public Staff(String name, Vector2f spawnLocation, Checkout checkout) {
        this(name, spawnLocation);
        cashier = new Cashier(checkout);
        cashier.setName(name);
        cashier.setLocation(spawnLocation);
        this.function = "cashier";
    }

    /**
     * Constructor for stafftype stocker.
     *
     * @param name
     * @param storage & aisle
     */
    public Staff(String name, Vector2f spawnLocation, Storage storage) {
        this(name, spawnLocation);
        stocker = new Stocker(storage);
        stocker.setName(name);
        stocker.setLocation(spawnLocation);
        function = "stocker";
    }

    /**
     * Constructor for stafftype unloader.
     *
     * @param name
     * @param storage & truck
     */
    public Staff(String name, Vector2f spawnLocation, Storage storage, Truck truck, ArrayList<Item> shopItems) {
        this(name, spawnLocation);
        unloader = new Unloader(storage, truck, shopItems);
        unloader.setName(name);
        unloader.setLocation(spawnLocation);
        function = "unloader";
    }

    public Cashier getCashier() {
        return cashier;
    }

    public Unloader getUnloader() {
        return unloader;
    }

    public Stocker getStocker() {
        return stocker;
    }

    public String getFunction() {
        return function;
    }
}
