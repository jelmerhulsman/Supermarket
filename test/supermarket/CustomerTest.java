/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sander
 */
public class CustomerTest {
    
    public CustomerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getItemsFromAisle method, of class Customer.
     */
    @Test
    public void testGetItemsFromAisle() {
        System.out.println("getItemsFromAisle");
        Aisle aisle = new Aisle("Liquor",new Vector2f(40,40), Item.Category.BEER, Item.Category.LIQUOR);
        Item item;
        item= new Item("Budweiser", Item.Category.BEER, 3);
        item.setStatus(Item.Status.LOADED);
        aisle.loadAisle(item);
        ArrayList<Item> uniqueItems = new ArrayList<Item>();
        uniqueItems.add(item);
        Customer instance = new Customer("derp", Customer.Stereotype.STUDENT, uniqueItems);
        try{
        instance.getItemsFromAisle(aisle);
        }catch(Exception e){
            fail("failed to get items from isle");
        }
    }
    
    
}