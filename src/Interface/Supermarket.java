/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.jme3.math.Vector2f;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Scanner;
import supermarket.Aisle;
import supermarket.Checkout;
import supermarket.Customer;
import supermarket.Department;
import supermarket.Item;
import supermarket.ObjectInShop;
import supermarket.StaffTypes.Cashier;
import supermarket.StaffTypes.Staff;
import supermarket.StaffTypes.Stocker;
import supermarket.StaffTypes.Unloader;
import supermarket.Storage;
import supermarket.Truck;

/**
 *
 * @author Sander
 */
public class Supermarket extends javax.swing.JFrame {
    
    private final int MAX_CUSTOMERS = 20;
    private ArrayList<Aisle> aisles;
    private ArrayList<Checkout> checkouts;
    private ArrayList<Department> departments;
    private Storage storage;
    private Truck truck;
    private ArrayList<ObjectInShop> staticLocations;
    private ArrayList<Staff> staffMembers;
    private ArrayList<Item> shopItems;
    private ArrayList<Customer> customers;
    private Graphics g;
    
    /**
     * Creates new form Supermarket
     */
    @SuppressWarnings("empty-statement")
    public Supermarket() {
        initComponents();
        final int MAX_AISLES = 4;
        final int MAX_ITEMS_PER_AISLE = 2;
        final int MAX_CHECKOUTS = 4;
        final int MAX_DEPARTMENTS = 2;
        final int MAX_ITEMS_PER_DEPARTMENT = 1;
        final int MAX_STAFF_MEMBERS = 6;
        final int MAX_UNIQUE_ITEMS = (MAX_AISLES * MAX_ITEMS_PER_AISLE) + (MAX_DEPARTMENTS * MAX_ITEMS_PER_DEPARTMENT);
        
        //Graphics
        canvas1.setBackground(Color.white);
        g = canvas1.getGraphics();
        
        //Add all unique items to a list
        shopItems = new ArrayList<>();
        shopItems.add(new Item("Heimstel-Jan", 0.80f, Item.Category.BEER));
        shopItems.add(new Item("Ricewaffle", 1.20f, Item.Category.BREAKFAST));
        shopItems.add(new Item("Slurpys", 2.00f, Item.Category.SODA));
        shopItems.add(new Item("Ready 2 Eat Lasagne", 2.50f, Item.Category.READY_TO_EAT));
        shopItems.add(new Item("Nazi-kraut", 2.80f, Item.Category.VEGTABLES));
        shopItems.add(new Item("Tomahawkto", 0.50f, Item.Category.FRUIT));
        shopItems.add(new Item("Moo-Moo Milk", 1.25f, Item.Category.DAIRY));
        shopItems.add(new Item("Lice", 1.00f, Item.Category.FOREIGN));
        shopItems.add(new Item("Ass-Whipe Deluxe", 1.40f, Item.Category.NONFOOD));
        
        //Create aisles
        aisles = new ArrayList<>();
        aisles.add(new Aisle("Liquor", new Vector2f(40, 60), Item.Category.BEER, Item.Category.LIQUOR, Item.Category.WINE));
        aisles.add(new Aisle("Lunch & Breakfast", new Vector2f(80, 60), Item.Category.BREAD, Item.Category.SPREAD, Item.Category.BREAKFAST));
        aisles.add(new Aisle("Cooling", new Vector2f(10, 60), Item.Category.FROZEN, Item.Category.READY_TO_EAT, Item.Category.DAIRY));
        aisles.add(new Aisle("Luxury", new Vector2f(60, 60), Item.Category.SNACK, Item.Category.SODA, Item.Category.CAFFEINE));
        aisles.add(new Aisle("Durable", new Vector2f(20, 60), Item.Category.SPICES, Item.Category.FOREIGN, Item.Category.PRESERVATION));
        aisles.add(new Aisle("Vegtables & Fruit", new Vector2f(160, 60), Item.Category.VEGTABLES, Item.Category.FRUIT));
        aisles.add(new Aisle("Nonfood", new Vector2f(60, 60), Item.Category.NONFOOD));

        //Create checkouts
        checkouts = new ArrayList<>();
        for (int i = 0; i < MAX_CHECKOUTS; i++) {
            checkouts.add(new Checkout(i + 1, new Vector2f(90 - 10 * i, 80)));
        }

        //Create departments
        departments = new ArrayList<>();
        for (int i = 0; i < MAX_DEPARTMENTS; i++) {
            departments.add(new Department("Drinks Department", new Vector2f(50, 50)));

        }

        //Create storage and truck
        storage = new Storage("Storage", new Vector2f(0, 50));
        truck = new Truck("Truck", new Vector2f(0, 0));

        //Create Staff members
        staffMembers = new ArrayList<>();
        staffMembers.add(new Staff("Jannes", storage.getLocation(), storage, truck, shopItems));
        staffMembers.add(new Staff("Jan de Bierman", storage.getLocation(), storage, aisles.get(0)));
        for (Staff staff: staffMembers) {
            staff.update(staticLocations);
        }

        //Assign locations in the shop
        staticLocations = new ArrayList<>();
        staticLocations.addAll(departments);
        staticLocations.addAll(aisles);
        staticLocations.addAll(checkouts);
        staticLocations.add(storage);
        staticLocations.add(truck);
        staticLocations.add(new ObjectInShop("Entrance/Exit", new Vector2f(100,100)));

        

        //List of customers
        customers = new ArrayList<>();

        //Choose debugger
        chooseDebugger();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        list2 = new java.awt.List();
        list3 = new java.awt.List();
        jPanel2 = new javax.swing.JPanel();
        canvas1 = new java.awt.Canvas();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(list2, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(list3, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(list2, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
                    .addComponent(list3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(239, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Customers", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Map", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Supermarket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Supermarket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Supermarket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Supermarket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        Supermarket simulation;
        simulation = new Supermarket();
        simulation.setVisible(true);
        System.out.println("Supermarket initialized...");
        
        while (true) { //Update loop
            simulation.customersLoop();
            simulation.interfaceUpdate();
            
            //Sleep at the end of the loop
            simulation.sleep(1000);
        }
    }
    
    private void interfaceUpdate(){
        g.clearRect(0, 0, 5000, 5000);
        list2.removeAll();
        list3.removeAll();
        
        g.setColor(Color.blue);
        g.drawRect((int)storage.getLocation().x*4, (int)storage.getLocation().y*4, 20, 20);
        
        g.setColor(Color.green);
        for(Aisle aisle:aisles)
            g.drawRect((int)aisle.getLocation().x*4, (int)aisle.getLocation().y*4, 15, 30);
        
        g.setColor(Color.red);
        for(Customer customer:customers){
            g.drawRect((int)customer.getLocation().x*4, (int)customer.getLocation().y*4, 10, 10);
            list2.add(customer.getName());
            list3.add(customer.getLocation().x + "   " + customer.getLocation().y);
        }
        
        g.setColor(Color.PINK);
        for(Department department:departments)
            g.drawRect((int)department.getLocation().x, (int)department.getLocation().y, 15, 15);
        
        g.setColor(Color.orange);
        for(Checkout checkout:checkouts)
            g.drawRect((int)checkout.getLocation().x, (int)checkout.getLocation().y, 10, 10);
        
//        g.setColor(Color.CYAN);
//        for(Staff staff:staffMembers)
//            g.drawRect((int)staff.getLocation().x, (int)staff.getLocation().y, 5, 5);
//       g.drawRect((int)unloader.getLocation().x, (int)unloader.getLocation().y, 5, 5);
//       g.drawRect((int)stocker.getLocation().x, (int)stocker.getLocation().y, 5, 5);
        
        g.setColor(Color.DARK_GRAY);
        g.drawRect((int)truck.getLocation().x, (int)truck.getLocation().y, 20, 40);
    }

    private void customersLoop() {
        addEnteringCustomers();

        ArrayList<Customer> leavingCustomers = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.isLeaving()) {
                leavingCustomers.add(customer);
            }
        }

        customers.removeAll(leavingCustomers);
    }

    private void addEnteringCustomers() {
        if (customers.size() < MAX_CUSTOMERS) {
            int percent = 0;
            if (customers.size() < MAX_CUSTOMERS * 0.3f) {
                percent = 35;
            } else if (customers.size() < MAX_CUSTOMERS * 0.8f) {
                percent = 25;
            } else {
                percent = 15;
            }

            if (chanceOf(percent)) {
                ArrayList<Customer.Stereotype> stereotype;
                do {
                    stereotype = new ArrayList<>();
                    for (Customer.Stereotype s : Customer.Stereotype.values()) {
                        if (chanceOf(25)) {
                            stereotype.add(s);
                        }
                    }
                } while (stereotype.size() != 1);
                
                
                for (ObjectInShop o : staticLocations)
                {
                    if (o.getName().equals("Entrance/Exit"))
                    {
                        String name = "Nr. " + ((int) customers.size() + 1);
                        customers.add(new Customer("CUSTOMER " + name, o.getLocation(), stereotype.get(0), shopItems));
                        customers.get(customers.size() - 1).update(staticLocations);
                    }
                }
            }
        }
    }

    private boolean chanceOf(int percent) {
        return (int) (Math.random() * 101) <= percent;
    }

    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds); //1000 milliseconds is one second.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void chooseDebugger() {
        boolean loop = true;
        do {
            System.out.print("DEBUGGER: ");
            String debugger = new Scanner(System.in).next();
            debugger = debugger.trim();
            debugger = debugger.toUpperCase();

            switch (debugger) {
                case "MORENO":
                    Moreno();
                    loop = false;
                    break;
                case "DYLAN":
                    Dylan();
                    loop = false;
                    break;
                case "SANDER":
                    Sander();
                    loop = false;
                    break;
                case "JELMER":
                    Jelmer();
                    loop = false;
                    break;
                case "BREAK":
                    loop = false;
                    break;
            }
        } while (loop);
    }

    private void Moreno() { //Moreno's testing area
    }

    private void Dylan() { //Dylan's testing area
    }

    private void Sander() { //Sander's testing area
    }

    private void Jelmer() { //Jelmer's testing area
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas canvas1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private java.awt.List list2;
    private java.awt.List list3;
    // End of variables declaration//GEN-END:variables
}
