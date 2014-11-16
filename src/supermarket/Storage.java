package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.*;
import org.hibernate.cfg.*;

/**
 *
 * @author SDJM
 */
public class Storage extends ObjectInShop {

    private ArrayList<Item> items;
    private static SessionFactory factory;
    boolean changed = true;

    /**
     * Make a new storage
     *
     * @param name The name of this storage
     * @param location the location of this storage
     */
    public Storage(String name, Vector2f location) {
        super(name, location);
        items = new ArrayList<>();
        try {
            factory = new AnnotationConfiguration().
                    configure().
                    //addPackage("com.xyz");
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
    public ArrayList<Item> getAllItems() {

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
     * @param amount Total number of items to pick
     * @param category From which category
     * @return
     */
    public ArrayList<Item> getItems(int amount, Item item) {
        items = new ArrayList<>();
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM supermarket.Item WHERE name = '" + item.getName() + "'";
            List itemsList = session.createQuery(hql).list();
            if (itemsList.size() < amount) {
                amount = itemsList.size();
            }
            for (int i = 0; i < amount; i++) {
                Iterator iterator = itemsList.iterator();
                Item temp = (Item) iterator.next();
                items.add(temp);
                itemsList.remove(temp);
                
                try {
                    hql = "DELETE FROM supermarket.Item WHERE id = '" + temp.getId() + "'";
                    Query delete = session.createQuery(hql);
                    delete.executeUpdate();
                } catch (HibernateException e) {
                } //Hibernate bug

            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        changed = true;
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
            changed = true;
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
        items = new ArrayList<>();
        ArrayList<Item> allItems = getAllItems();
        int counter = 0;
        for (int i = 0; i < allItems.size(); i++) {
            if (counter < amount && allItems.get(i).getCategory() == cat) {
                items.add(getAndRemoveItem(allItems.get(i).getId()));
                counter++;
            }
        }
        changed = true;
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

    public boolean isChanged() {
        return changed;
    }

    public void setIsChanged(boolean isChanged) {
        this.changed = isChanged;
    }
}
