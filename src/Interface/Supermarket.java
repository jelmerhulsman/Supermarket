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
    private final int MIN_STASH = 5;
    private final int MAX_STASH = 25;
    private ArrayList<Aisle> aisles;
    private ArrayList<Checkout> checkouts;
    private ArrayList<Department> departments;
    private ArrayList<Item> order;
    private Storage storage;
    private Truck truck;
    private ArrayList<ObjectInShop> staticLocations;
    private Unloader unloader;
    private Stocker stocker;
    private ArrayList<Staff> flexibleStaff;
    private ArrayList<Item> availableItems;
    private ArrayList<Cashier> cashier;
    private ArrayList<Item> shopItems;
    private ArrayList<Customer> customers;
    Graphics g;
    
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
        
        canvas1.setBackground(Color.white);
        g = canvas1.getGraphics();
        
        
        //Create aisles
        aisles = new ArrayList<>();
        aisles.add(new Aisle("Liquor", new Vector2f(40, 60), Item.Category.BEER, Item.Category.LIQUOR, Item.Category.WINE));
        label5.setText("Liquor");
        aisles.add(new Aisle("Lunch & Breakfast", new Vector2f(80, 60), Item.Category.BREAD, Item.Category.SPREAD, Item.Category.BREAKFAST));
        label6.setText("Lunch & Breakfast");
        aisles.add(new Aisle("Cooling", new Vector2f(10, 60), Item.Category.FROZEN, Item.Category.READY_TO_EAT, Item.Category.DAIRY));
        label7.setText("Cooling");
        aisles.add(new Aisle("Luxury", new Vector2f(60, 60), Item.Category.SNACK, Item.Category.SODA, Item.Category.CAFFEINE));
        label8.setText("Luxury");
        aisles.add(new Aisle("Durable", new Vector2f(20, 60), Item.Category.SPICES, Item.Category.FOREIGN, Item.Category.PRESERVATION));
        label9.setText("Durable");
        aisles.add(new Aisle("Vegtables & Fruit", new Vector2f(160, 60), Item.Category.VEGTABLES, Item.Category.FRUIT));
        label10.setText("Vegtables & Fruit");
        aisles.add(new Aisle("Nonfood", new Vector2f(60, 60), Item.Category.NONFOOD));
        label11.setText("Nonfood");
        
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
        unloader = new Unloader("Jannes", storage, truck);
        unloader.update(staticLocations);
        stocker = new Stocker("Jan de Bierman", storage);
        stocker.update(staticLocations);

        //Assign locations in the shop
        staticLocations = new ArrayList<>();
        staticLocations.addAll(departments);
        staticLocations.addAll(aisles);
        staticLocations.addAll(checkouts);
        staticLocations.add(storage);
        staticLocations.add(truck);
        staticLocations.add(new ObjectInShop("Entrance/Exit", new Vector2f(100,100)));

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

        popupMenu1 = new java.awt.PopupMenu();
        popupMenu2 = new java.awt.PopupMenu();
        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        list3 = new java.awt.List();
        customerSelector = new javax.swing.JComboBox();
        list1 = new java.awt.List();
        label1 = new java.awt.Label();
        list2 = new java.awt.List();
        label2 = new java.awt.Label();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        canvas1 = new java.awt.Canvas();
        jPanel3 = new javax.swing.JPanel();
        label5 = new java.awt.Label();
        list4 = new java.awt.List();
        list5 = new java.awt.List();
        list7 = new java.awt.List();
        list8 = new java.awt.List();
        list9 = new java.awt.List();
        list10 = new java.awt.List();
        label6 = new java.awt.Label();
        label7 = new java.awt.Label();
        label8 = new java.awt.Label();
        label9 = new java.awt.Label();
        label10 = new java.awt.Label();
        label11 = new java.awt.Label();
        list11 = new java.awt.List();

        popupMenu1.setLabel("popupMenu1");

        popupMenu2.setLabel("popupMenu2");

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        customerSelector.setMaximumRowCount(100);
        customerSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerSelectorActionPerformed(evt);
            }
        });

        label1.setText("Shopping List");

        label2.setText("Shopping Cart");

        label3.setText("Other Info");

        label4.setText("Select customer");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(list2, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(list3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 151, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(list1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(list3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 177, Short.MAX_VALUE))
                                    .addComponent(list2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Customers", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(canvas1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Map", jPanel2);

        label5.setText("label5");

        label6.setText("label6");

        label7.setText("label7");

        label8.setText("label8");

        label9.setText("label9");

        label10.setText("label10");

        label11.setText("label11");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(list8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                        .addComponent(list4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(list10, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                        .addComponent(list5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(list11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(list7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(list9, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(44, 44, 44))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(list7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(list5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(list4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(list11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(list8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(list10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(label11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(list9, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36))
        );

        jTabbedPane1.addTab("Aisles", jPanel3);

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

    private void customerSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerSelectorActionPerformed
        
        list1.clear();
        list2.clear();
       
        
        
        for(Item item: customers.get(customerSelector.getSelectedIndex()).getShoppingList()){
            list1.add(item.getName());
        }
        
        for(Item item: customers.get(customerSelector.getSelectedIndex()).getShoppingCart()){
            list2.add(item.getName());
        }
        
    }//GEN-LAST:event_customerSelectorActionPerformed

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
            simulation.orderLoop();
            simulation.customersLoop();
            simulation.interfaceUpdate();
            //simulation.customersLoop();
            //Sleep at the end of the loop

            simulation.sleep(1000);
        }
    }
    
    private void interfaceUpdate(){
        g.clearRect(0, 0, 5000, 5000);
        
        g.setColor(Color.blue);
        g.drawRect((int)storage.getLocation().x*4, (int)storage.getLocation().y*4, 20, 20);
        
        g.setColor(Color.green);
        for(Aisle aisle:aisles)
            g.drawRect((int)aisle.getLocation().x*4, (int)aisle.getLocation().y*4, 15, 30);
        
        g.setColor(Color.red);
        for(Customer customer:customers)
            g.drawRect((int)customer.getLocation().x*4, (int)customer.getLocation().y*4, 10, 10);
        
        g.setColor(Color.PINK);
        for(Department department:departments)
            g.drawRect((int)department.getLocation().x, (int)department.getLocation().y, 15, 15);
        
        g.setColor(Color.orange);
        for(Checkout checkout:checkouts)
            g.drawRect((int)checkout.getLocation().x, (int)checkout.getLocation().y, 10, 10);
        
        g.setColor(Color.CYAN);
//        for(Staff staff:flexibleStaff)
//            g.drawRect((int)staff.getLocation().x, (int)staff.getLocation().y, 5, 5);
       g.drawRect((int)unloader.getLocation().x, (int)unloader.getLocation().y, 5, 5);
       g.drawRect((int)stocker.getLocation().x, (int)stocker.getLocation().y, 5, 5);
        
        g.setColor(Color.DARK_GRAY);
        g.drawRect((int)truck.getLocation().x, (int)truck.getLocation().y, 20, 40);
        
        
        try{
        list3.clear();
        list3.add("Name: " +customers.get(customerSelector.getSelectedIndex()).getName());
        list3.add("Saldo: " + customers.get(customerSelector.getSelectedIndex()).getSaldo());
        list3.add("Locatio X:" + customers.get(customerSelector.getSelectedIndex()).getLocation().x + " Y:" + 
                customers.get(customerSelector.getSelectedIndex()).getLocation().y);
        list3.add("Current Aisle: " + customers.get(customerSelector.getSelectedIndex()).getCurLocObject().getName());
        }catch(Exception e){}
        
        for(Item item:aisles.get(0).getItems())
            list4.add(item.getName());
        for(Item item:aisles.get(1).getItems())
            list5.add(item.getName());
        for(Item item:aisles.get(2).getItems())
            list11.add(item.getName());
        for(Item item:aisles.get(3).getItems())
            list7.add(item.getName());
        for(Item item:aisles.get(4).getItems())
            list8.add(item.getName());
        for(Item item:aisles.get(5).getItems())
            list10.add(item.getName());
        for(Item item:aisles.get(6).getItems())
            list9.add(item.getName());
        
    }
    
    private void orderLoop() {
        ArrayList<Item> orderItems = new ArrayList<>();
        for (Item shopItem : shopItems) {
            int currentStash = storage.getItemCount(shopItem.getName());
            if (currentStash < MIN_STASH) {
                do {
                    orderItems.add(new Item(shopItem));
                    currentStash++;
                } while (currentStash < MAX_STASH);
            }
        }

        if (!orderItems.isEmpty()) {
            truck.order(orderItems);
            
            unloader = new Unloader("Jannes", storage,truck);
            unloader.getItemsFromTruck();
        }
        if(!storage.getItems().isEmpty())
        {
            stocker.getItemsFromStorage(Item.Category.BEER);
        }
    }

    private void customersLoop() {
        addEnteringCustomers();

        ArrayList<Customer> leavingCustomers = new ArrayList<>();
        for (int i = 0;i<customers.size();i++) {
            if (customers.get(i).isLeaving()) {
                leavingCustomers.add(customers.get(i));
                customerSelector.remove(i-1);
            }
        }
        
        customers.removeAll(leavingCustomers);
    }

    private ArrayList<Customer> addEnteringCustomers() {
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

                customers.add(new Customer("Nr. " + ((int) customers.size() + 1), stereotype.get(0), shopItems));
                customerSelector.addItem(customers.get(customers.size() - 1).getName());
                customers.get(customers.size() - 1).update(staticLocations, checkouts);
            }
        }

        return customers;
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
        cashier = new ArrayList<>();
        Staff johanna = new Staff("Johanna", checkouts.get(0));
        cashier.add(johanna.getCashier());

        cashier.get(0).gotoLocation(cashier.get(0).getWorkplace().getName(), staticLocations);
        cashier.get(0).getCashier().goToCheckout();
    }

    private void Sander() { //Sander's testing area
    }

    private void Jelmer() { //Jelmer's testing area
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Canvas canvas1;
    private javax.swing.JComboBox customerSelector;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private java.awt.Label label1;
    private java.awt.Label label10;
    private java.awt.Label label11;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private java.awt.Label label9;
    private java.awt.List list1;
    private java.awt.List list10;
    private java.awt.List list11;
    private java.awt.List list2;
    private java.awt.List list3;
    private java.awt.List list4;
    private java.awt.List list5;
    private java.awt.List list7;
    private java.awt.List list8;
    private java.awt.List list9;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.PopupMenu popupMenu2;
    // End of variables declaration//GEN-END:variables
}
