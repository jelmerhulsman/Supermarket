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

    /**
     * Test of getCurrentUnloader method, of class Storage.
     */
    @Test
    public void testGetCurrentUnloader() {
        System.out.println("getCurrentUnloader");
        Storage instance = new Storage("storage", Vector2f.ZERO);
        Unloader unloader = new Unloader("derp", instance);
        instance.setCurrentUnloader(unloader);
        Unloader result = null;
        try{
        result = instance.getCurrentUnloader();
        }catch(Exception e){
            fail("failed because " + e);
        }
        if(result != null){
            
        }else{
            fail("no unloader returned");
        }
    }

    //setCurrentUnloader is not tested, is already tested in the 
    //getCurrentunloader test

    /**
     * Test of getItems method, of class Storage.
     */
    @Test
    public void testGetItems() {
        System.out.println("getItems");
        Storage instance = new Storage("storage", Vector2f.ZERO);
        ArrayList<Item> result = instance.getItems();
        if(result.size()>0){
            
        }else{
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
        Item item = new Item("BudWeiser", Item.Category.BEER, 3);
        Storage instance = new Storage("storage", Vector2f.ZERO);
        try{
            instance.addItem(item);
            System.out.println("addItem OK");
        }catch(Exception e){
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
        if(result.size() <= amount){
            
        }
        else{
            fail("did not return the righ amount of items");
        }
    }
}