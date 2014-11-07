package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.cfg.*;
import supermarket.Item.Category;
import supermarket.StaffTypes.Unloader;

/**
 *
 * @author SDJM
 */
public class Storage extends ObjectInShop {

    private ArrayList<Item> items;
    private static SessionFactory factory;
    boolean isChanged = true;
    /**
     * creates the storage
     */
    public Storage(String name, Vector2f location) {
        super(name, location);
        items = new ArrayList<>();
        try {
            factory = new AnnotationConfiguration().
                    configure().
                    //addPackage("com.xyz") //add package if used.
                    addAnnotatedClass(Item.class).
                    buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * returs a list of all the items in the storage
     *
     * @return items
     */
    public ArrayList<Item> getItems() {

        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM supermarket.Item";
            List itemsList = session.createQuery(hql).list();
            for (Iterator iterator = itemsList.iterator(); iterator.hasNext();) {
                items.add((Item) iterator.next());
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return items;
    }

    /**
     * returns gets items from the storage and removes it in the database
     *
     * @param count Total number of items to pick
     * @param category From which category
     * @return
     */
    public ArrayList<Item> getItems(int count, Category category) {
        items.clear();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM supermarket.Item WHERE Category = '" + category.toString() + "'";
            List itemsList = session.createQuery(hql).list();
            if (itemsList.size() < count) {
                count = itemsList.size();
            }
            for (int i = 0; i < count; i++) {
                Iterator iterator = itemsList.iterator();
                Item item = (Item) iterator.next();
                items.add(item);
                session.delete(item);
                itemsList.remove(item);
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        isChanged = true;
        return items;
    }

    /**
     * return the item count from the item in the storage
     *
     * @return count
     */
    public int getItemCount(String itemName) {
        int count = 0;
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "SELECT I.name FROM supermarket.Item I";
            List itemsList = session.createQuery(hql).list();
            for (Object o : itemsList) {
                if (itemName.equals(o)) {
                    count++;
                }
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
            return count;
        }
    }

    /**
     * adds an item to the storage
     *
     * @param item the item to add to the storage
     */
    public void addItem(Item item) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(item);
            tx.commit();
            isChanged = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
    }

    /**
     * will move items from the storage to where you want it if storage doesn't
     * contain enough of the item, it will give all the items
     *
     * @param cat the category the item is in
     * @param amount the amount you need of the item
     * @return the list of the items
     */
    public ArrayList<Item> moveItem(Item.Category cat, int amount) {
        items.clear();
        ArrayList<Item> allItems = getItems();
        int counter = 0;
        for (int i = 0; i < allItems.size(); i++) {
            if (counter < amount && allItems.get(i).getCategory() == cat) {
                items.add(getAndRemoveItem(allItems.get(i).getId()));
                counter++;
            }
        }
        isChanged = true;
        return items;
    }

    /**
     * removes an item from the storage
     *
     * @param item the item to remove
     */
    private Item getAndRemoveItem(int ID) {
        Session session = factory.openSession();
        Transaction tx = null;
        Item item = null;
        try {
            tx = session.beginTransaction();
            item = (Item) session.get(Item.class, ID);
            session.delete(item);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return item;
    }

    public boolean isIsChanged() {
        return isChanged;
    }

    public void setIsChanged(boolean isChanged) {
        this.isChanged = isChanged;
    }
}
