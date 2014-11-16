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
import supermarket.StaffTypes.*;

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

    

    //setCurrentUnloader is not tested, is already tested in the 
    //getCurrentunloader test
    /**
     * Test of getAllItems method, of class Storage.
     */
    @Test
    public void testGetItems() {
        System.out.println("getItems");
        Storage instance = new Storage("storage", Vector2f.ZERO);
        ArrayList<Item> result = instance.getAllItems();
        if (result.size() > 0) {
        } else {
            fail("Nothing returned (is database empty?)");
        }
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
        try {
            instance.addItem(item);
            System.out.println("addItem OK");
        } catch (Exception e) {
            fail("Item was not added  " + e);
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
        ArrayList<Item> result = instance.moveItem(cat, amount);
        if (result.size() <= amount) {
        } else {
            fail("did not return the righ amount of items");
        }
    }

    /**
     * Test of getAllItems method, of class Storage.
     */
    @Test
    public void testGetItems_0args() {
        System.out.println("getItems");
        Storage instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getAllItems();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllItems method, of class Storage.
     */
    @Test
    public void testGetItems_int_ItemCategory() {
        System.out.println("getItems");
        int count = 0;
        Item item = null;
        Storage instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getItems(count, item);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getItemCount method, of class Storage.
     */
    @Test
    public void testGetItemCount() {
        System.out.println("getItemCount");
        String itemName = "";
        Storage instance = null;
        int expResult = 0;
        int result = instance.getItemCount(itemName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isChanged method, of class Storage.
     */
    @Test
    public void testIsIsChanged() {
        System.out.println("isIsChanged");
        Storage instance = null;
        boolean expResult = false;
        boolean result = instance.isChanged();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIsChanged method, of class Storage.
     */
    @Test
    public void testSetIsChanged() {
        System.out.println("setIsChanged");
        boolean isChanged = false;
        Storage instance = null;
        instance.setIsChanged(isChanged);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}