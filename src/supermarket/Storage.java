package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.cfg.*;
import supermarket.StaffTypes.Unloader;

public class Storage extends PartOfShop {
    private ArrayList<Item> items = null;
    private Unloader currentUnloader;
    private static SessionFactory factory; 
    /**
     * creates the storage
     */
    public Storage(String name, Vector2f location) {
        super(name,location);
        try{
        factory = new AnnotationConfiguration().
                   configure().
                   //addPackage("com.xyz") //add package if used.
                   addAnnotatedClass(Item.class).
                   buildSessionFactory();
        }
        catch (Throwable ex) { 
         System.err.println("Failed to create sessionFactory object." + ex);
         throw new ExceptionInInitializerError(ex); 
      }
    }
    
    /**
     * returs the current unloader
     * @return unloader
     */
    public Unloader getCurrentUnloader() {
        return currentUnloader;
    }
    
    /**
     * assigns an unloader to the storage
     * @param currentUnloader 
     */
    public void setCurrentUnloader(Unloader currentUnloader) {
        this.currentUnloader = currentUnloader;
    }

    /**
     * returs a list of all the items in teh storage
     * @return items
     */
    public ArrayList<Item> getItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List itemslist = session.createQuery("FROM supermarket.Item").list();
            for (Iterator iterator = itemslist.iterator(); iterator.hasNext();){
                items.add((Item) iterator.next());
            }
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally {
            session.close(); 
        }
        return items;
    }
    
    /**
     * adds an item to the storage
     * @param item the item to add to the storage
     */
    public void addItem(Item item){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(item);
            tx.commit();
        }
        catch(HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally {
            session.close(); 
        }
    }
    
    /**
     * will move items from the storage to where you want it
     * if storage doesn't contain enough of the item,
     * it will give all the items
     * @param cat the category the item is in
     * @param amount the amount you need of the item
     * @return the list of the items
     */
    public ArrayList<Item> moveItem(Item.Category cat, int amount){
        ArrayList<Item> items = new ArrayList<Item>();
        ArrayList<Item> allItems = getItems();
        int counter = 0;
        for(int i = 0; i<allItems.size();i++){
            if(counter<amount && allItems.get(i).getCategory() == cat){
                items.add( getAndRemoveItem(allItems.get(i).getId()));
                counter++;
            }
        }
        return items;
    }
    
    /**
     * removes an item from the storage
     * @param item the item to remove
     */
    private Item getAndRemoveItem(int ID){
        Session session = factory.openSession();
        Transaction tx = null;
        Item item = null;
        try{
            tx = session.beginTransaction();
            item = (Item)session.get(Item.class, ID);
            session.delete(item);
            tx.commit();
        }catch(HibernateException e){
            if (tx!=null) tx.rollback();
            e.printStackTrace(); 
        }finally{
            session.close(); 
        }
        return item;
    }
}
