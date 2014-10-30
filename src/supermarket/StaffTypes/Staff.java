package supermarket.StaffTypes;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Checkout;
import supermarket.Item;
import supermarket.ObjectInShop;
import supermarket.Storage;

public class Staff extends ObjectInShop {

    Cashier cashier;
    Unloader unloader;
    Stocker stocker;
    Supervisor supervisor;
    String function;
    Thread opperation;
    
    protected ObjectInShop workplace;
    protected final int maxItems = 10;
    protected ArrayList<Item> items;
    
    /**
     * Basic staff constructor
     * @param name 
     */
    public Staff(String name) {
        super(name, new Vector2f(0, 50), 2f);
        items = new ArrayList<>();
        this.opperation = new Thread();
    }
    
    /**
     * Constructor for stafftype cashier.
     * @param name
     * @param checkout 
     */
    public Staff(String name, Checkout checkout){
        cashier = new Cashier(name, checkout);
        this.function = "cashier";
        this.opperation = new Thread();
    }
    
    /**
     * Constructor for stafftype unloader.
     * @param name
     * @param storage 
     */
    public Staff(String name, Storage storage){
        unloader = new Unloader(name, storage);
        this.function = "unloader";
        this.opperation = new Thread();
    }
    
    /**
     * Constructor for stafftype stocker.
     * @param name
     */    
//    public Staff(String name){
//        stocker = new Stocker(name);  
//        this.function = "stocker";
//    }
  
     /**
     * Constructor for stafftype supervisor.
     * @param name
     * 
//    public Staff(String name){
//        supervisor = new Supervisor(name);
//        this.function = "supervisor";
//    }

    /**
     * Get cashier class.
     * @return cashier
     */
    public Cashier getCashier() {
        return cashier;
    }

    /**
     * Get unloader class.
     * @return unloader
     */
    public Unloader getUnloader() {
        return unloader;
    }

    /**
     * Get stocker class.
     * @return stocker
     */
    public Stocker getStocker() {
        return stocker;
    }
    
    /**
     * Get supervisor class.
     * @return supervisor
     */
    public Supervisor getSupervisor() {
        return supervisor;
    }

    public ObjectInShop getWorkplace() {
        return workplace;
    }
    
    public void doNothing()
    {
        
    }
}
