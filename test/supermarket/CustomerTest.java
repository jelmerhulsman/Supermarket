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
        item= new Item("Budweiser", Item.Category.BEER, 3, true);
        aisle.loadAisle(item);
        ArrayList<Item> uniqueItems = new ArrayList<Item>();
        
        Customer instance = new Customer("derp", Customer.Stereotype.STUDENT, uniqueItems);
        ArrayList expResult = new ArrayList();
        expResult.add(new Item("Budweiser", Item.Category.BEER, 3, true));
        ArrayList result = instance.getItemsFromAisle(aisle);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
}