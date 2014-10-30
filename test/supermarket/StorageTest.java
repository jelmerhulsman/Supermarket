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
public class StorageTest {
    
    public StorageTest() {
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
     * Test of getCurrentUnloader method, of class Storage.
     */
    /*@Test
    public void testGetCurrentUnloader() {
        System.out.println("getCurrentUnloader");
        Storage instance = null;
        Unloader expResult = null;
        Unloader result = instance.getCurrentUnloader();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of setCurrentUnloader method, of class Storage.
     */
    /*@Test
    public void testSetCurrentUnloader() {
        System.out.println("setCurrentUnloader");
        Unloader currentUnloader = null;
        Storage instance = null;
        instance.setCurrentUnloader(currentUnloader);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/

    /**
     * Test of getItems method, of class Storage.
     */
    @Test
    public void testGetItems() {
        System.out.println("getItems");
        Storage instance = new Storage("storage", Vector2f.ZERO);
        ArrayList<Item> expResult = new ArrayList<Item>() ;
        ArrayList<Item> result = instance.getItems();
        for(int i = 0; i<result.size();i++){
            expResult.add(new Item("Heimstel-Jan", 0.80f, Item.Category.BEER));
            expResult.get(i).setId(result.get(i).getId());
            expResult.get(i).setStatus(Item.Status.IN_STORAGE);
        }
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of addItem method, of class Storage.
     */
    @Test
    public void testAddItem() {
        System.out.println("addItem");
        Item item = new Item("Heimstel-Jan", 0.80f, Item.Category.BEER);
        Storage instance = new Storage("storage", Vector2f.ZERO);
        try{
            instance.addItem(item);
            System.out.println("addItem OK");
        }catch(Exception e){
            fail("The test case is a prototype.");
        }
        
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of moveItem method, of class Storage.
     */
    @Test
    public void testMoveItem() {
        System.out.println("moveItem");
        Item.Category cat = Item.Category.BEER;
        int amount = 1;
        Storage instance = new Storage("storage", Vector2f.ZERO);
        ArrayList<Item> expResult = new ArrayList<Item>();
        for(int i = 0; i<amount;i++){
            Item item = new Item("Heimstel-Jan", 0.80f, Item.Category.BEER);
            item.setStatus(Item.Status.IN_STORAGE);
            expResult.add(item);
        }
        ArrayList<Item> result = instance.moveItem(cat, amount);
        for(int i = 0; i < result.size();i++){
            result.get(i).setId(0);
        }
        String expected = expResult.get(0).getName();
        String actual = result.get(0).getName();
        
        if(expected.equals(actual)){
            
        }
        else{
            fail("failed the test");
        }
    }
}