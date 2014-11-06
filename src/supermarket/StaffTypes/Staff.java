package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Aisle;
import supermarket.Checkout;
import supermarket.Item;
import supermarket.Person;
import supermarket.Storage;
import supermarket.Truck;

public class Staff extends Person {

    private Cashier cashier;
    private Unloader unloader;
    private Stocker stocker;
    private String function;
    
    protected final int MAX_ITEMS = 10;
    protected ArrayList<Item> items;
    
    /**
     * Basic staff constructor
     * @param name 
     */
    public Staff(String name, Vector2f spawnLocation) {
        super(name, spawnLocation);
        speed = 2f;
        items = new ArrayList<>();
    }
    
    /**
     * Constructor for stafftype cashier.
     * @param name
     * @param checkout 
     */
    public Staff(String name, Vector2f spawnLocation, Checkout checkout){
        cashier = new Cashier("CASHIER " + name, spawnLocation, checkout);
        this.function = "cashier";
    }
    
    /**
     * Constructor for stafftype stocker.
     * @param name
     * @param storage & aisle
     */
    public Staff(String name, Vector2f spawnLocation, Storage storage){
        stocker = new Stocker("STOCKER " + name, spawnLocation, storage);
        function = "stocker";
    }
    
    /**
     * Constructor for stafftype unloader.
     * @param name
     * @param storage & truck
     */
    public Staff(String name, Vector2f spawnLocation, Storage storage, Truck truck, ArrayList<Item> shopItems){
        unloader = new Unloader("UNLOADER " + name, spawnLocation, storage, truck, shopItems);
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
