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
        item= new Item("Heimstel-Jan", 0.80f, Item.Category.BEER);
        item.setStatus(Item.Status.LOADED);
        aisle.loadAisle(item);
        ArrayList<Item> uniqueItems = new ArrayList<Item>();
        uniqueItems.add(item);
        Customer instance = new Customer("derp", Vector2f.ZERO, Customer.Stereotype.STUDENT, uniqueItems);
        try{
        instance.getItemsFromAisle(aisle);
        }catch(Exception e){
            fail("failed to get items from isle");
        }
    }

    /**
     * Test of isLeaving method, of class Customer.
     */
    @Test
    public void testIsLeaving() {
        System.out.println("isLeaving");
        Customer instance = null;
        boolean expResult = false;
        boolean result = instance.isLeaving();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstItemLocation method, of class Customer.
     */
    @Test
    public void testGetFirstItemLocation() {
        System.out.println("getFirstItemLocation");
        ArrayList<ObjectInShop> staticLocations = null;
        Item item;
        item= new Item("Heimstel-Jan", 0.80f, Item.Category.BEER);
        ArrayList<Item> uniqueItems = new ArrayList<Item>();
        uniqueItems.add(item);
        Customer instance = new Customer("derp", Vector2f.ZERO, Customer.Stereotype.STUDENT, uniqueItems);
        Aisle result = instance.getFirstItemLocation(staticLocations);
        if(result ==null)
            fail("Nothing returned");
    }

    /**
     * Test of getShoppingList method, of class Customer.
     */
    @Test
    public void testGetShoppingList() {
        System.out.println("getShoppingList");
        Customer instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getShoppingList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chooseCheckout method, of class Customer.
     */
    @Test
    public void testChooseCheckout() {
        System.out.println("chooseCheckout");
        ArrayList<ObjectInShop> staticLocations = null;
        Customer instance = null;
        Checkout expResult = null;
        Checkout result = instance.chooseCheckout(staticLocations);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShoppingCart method, of class Customer.
     */
    @Test
    public void testGetShoppingCart() {
        System.out.println("getShoppingCart");
        Customer instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getShoppingCart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSaldo method, of class Customer.
     */
    @Test
    public void testSetSaldo() {
        System.out.println("setSaldo");
        float saldo = 0.0F;
        Customer instance = null;
        instance.setSaldo(saldo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSaldo method, of class Customer.
     */
    @Test
    public void testGetSaldo() {
        System.out.println("getSaldo");
        Customer instance = null;
        float expResult = 0.0F;
        float result = instance.getSaldo();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class Customer.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        ArrayList<ObjectInShop> staticLocations = null;
        Customer instance = null;
        instance.update(staticLocations);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
}