/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import com.jme3.math.Vector2f;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import supermarket.Aisle;
import supermarket.Checkout;
import supermarket.Checkout.Status;
import supermarket.Customer;
import supermarket.Department;
import supermarket.Item;
import supermarket.Item.Category;
import supermarket.ObjectInShop;
import supermarket.Person;
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
    private ObjectInShop entrance;
    private ArrayList<ObjectInShop> staticLocations;
    private ArrayList<Staff> staffMembers;
    private ArrayList<Item> storeItems;
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
        mapCanvas.setBackground(Color.white);
        g = mapCanvas.getGraphics();

        //Add all unique items to a list
        storeItems = new ArrayList<>();
        storeItems.add(new Item("Heimstel-Jan", 0.80f, Item.Category.BEER));
        storeItems.add(new Item("Ricewaffle", 1.20f, Item.Category.BREAKFAST));
        storeItems.add(new Item("Slurpys", 2.00f, Item.Category.SODA));
        storeItems.add(new Item("Ready 2 Eat Lasagne", 2.50f, Item.Category.READY_TO_EAT));
        storeItems.add(new Item("Nazi-kraut", 2.80f, Item.Category.VEGTABLES));
        storeItems.add(new Item("Tomahawkto", 0.50f, Item.Category.FRUIT));
        storeItems.add(new Item("Moo-Moo Milk", 1.25f, Item.Category.DAIRY));
        storeItems.add(new Item("Lice", 1.00f, Item.Category.FOREIGN));
        storeItems.add(new Item("Ass-Whipe Deluxe", 1.40f, Item.Category.NONFOOD));

        //Create aisles
        aisles = new ArrayList<>();
        ArrayList<Category> aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.BEER);
        aisleCategories.add(Category.LIQUOR);
        aisleCategories.add(Category.WINE);
        aisles.add(new Aisle("Liquor", new Vector2f(30, 60), aisleCategories, storeItems));
        lblLiqour.setText("Liquor");
        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.BREAD);
        aisleCategories.add(Category.SPREAD);
        aisleCategories.add(Category.BREAKFAST);
        aisles.add(new Aisle("Lunch & Breakfast", new Vector2f(40, 60), aisleCategories, storeItems));
        lblLunchAndBreakfast.setText("Lunch & Breakfast");
        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.FROZEN);
        aisleCategories.add(Category.READY_TO_EAT);
        aisleCategories.add(Category.DAIRY);
        aisles.add(new Aisle("Cooling", new Vector2f(50, 60), aisleCategories, storeItems));
        lblCooling.setText("Cooling");
        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.SNACK);
        aisleCategories.add(Category.SODA);
        aisleCategories.add(Category.CAFFEINE);
        aisles.add(new Aisle("Luxury", new Vector2f(60, 60), aisleCategories, storeItems));
        lblLuxury.setText("Luxury");
        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.SPICES);
        aisleCategories.add(Category.FOREIGN);
        aisleCategories.add(Category.PRESERVATION);
        aisles.add(new Aisle("Durable", new Vector2f(70, 60), aisleCategories, storeItems));
        lblDurable.setText("Durable");
        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.VEGTABLES);
        aisleCategories.add(Category.FRUIT);
        aisles.add(new Aisle("Vegtables & Fruit", new Vector2f(80, 60), aisleCategories, storeItems));
        label10.setText("Vegtables & Fruit");
        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.NONFOOD);
        aisles.add(new Aisle("Nonfood", new Vector2f(90, 60), aisleCategories, storeItems));
        lblNonfoon.setText("Nonfood");

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
        storage = new Storage("Storage", new Vector2f(0, 22.5f));
        truck = new Truck("Truck", new Vector2f(0, 0));
        entrance = new ObjectInShop("Entrance/Exit", new Vector2f(10, 100));

        //Assign locations in the shop
        staticLocations = new ArrayList<>();
        staticLocations.addAll(departments);
        staticLocations.addAll(aisles);
        staticLocations.addAll(checkouts);
        staticLocations.add(storage);
        staticLocations.add(truck);
        staticLocations.add(entrance);

        //Create Staff members
        staffMembers = new ArrayList<>();
        staffMembers.add(new Staff("Jannes", storage.getLocation(), storage, truck, storeItems));
        staffMembers.add(new Staff("Johanna", storage.getLocation(), checkouts.get(0)));
        staffMembers.add(new Staff("Jan de Bierman", storage.getLocation(), storage));
        //staffMembers.add(new Staff("Jip de Chip", storage.getLocation(), storage));
        //staffMembers.add(new Staff("Grietje Gezond", storage.getLocation(), storage));
        //staffMembers.add(new Staff("Koel Cooler", storage.getLocation(), storage));

        for (Person staff : staffMembers) {
            String test = staff.getName();
            staffComboBox.addItem(test);
        }

        //List of customers
        customers = new ArrayList<>();

        //Choose debugger
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
        Panes = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        customerSelector = new javax.swing.JComboBox();
        lstOtherCustomerInfo = new java.awt.List();
        lstShoppingList = new java.awt.List();
        lblSjoppingList = new java.awt.Label();
        lstShoppingCart = new java.awt.List();
        lblShoppingCart = new java.awt.Label();
        lblOtherCustomerInfo = new java.awt.Label();
        lblSelectCustomer = new java.awt.Label();
        jPanel2 = new javax.swing.JPanel();
        mapCanvas = new java.awt.Canvas();
        jPanel3 = new javax.swing.JPanel();
        lblLiqour = new java.awt.Label();
        lstLiqour = new java.awt.List();
        lstLunchAndBreakfast = new java.awt.List();
        listLuxury = new java.awt.List();
        lstDurable = new java.awt.List();
        lstNonfood = new java.awt.List();
        lstVegAndFruit = new java.awt.List();
        lblLunchAndBreakfast = new java.awt.Label();
        lblCooling = new java.awt.Label();
        lblLuxury = new java.awt.Label();
        lblDurable = new java.awt.Label();
        label10 = new java.awt.Label();
        lblNonfoon = new java.awt.Label();
        lstCooling = new java.awt.List();
        label12 = new java.awt.Label();
        jPanel4 = new javax.swing.JPanel();
        lstStorage = new java.awt.List();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        lblStaffName = new javax.swing.JLabel();
        staffComboBox = new javax.swing.JComboBox();
        lstItems = new java.awt.List();
        lblItems = new java.awt.Label();
        lstOtherStaffInfo = new java.awt.List();
        lblOtherStaffInfo = new java.awt.Label();

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

        lblSjoppingList.setText("Shopping List");

        lblShoppingCart.setText("Shopping Cart");

        lblOtherCustomerInfo.setText("Other Info");

        lblSelectCustomer.setText("Select customer");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lstShoppingList, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lstShoppingCart, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSelectCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customerSelector, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblSjoppingList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(lblShoppingCart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lstOtherCustomerInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblOtherCustomerInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 151, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerSelector, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelectCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSjoppingList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblShoppingCart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lstShoppingList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(lstOtherCustomerInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 177, Short.MAX_VALUE))
                                    .addComponent(lstShoppingCart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblOtherCustomerInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        Panes.addTab("Customers", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(mapCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(mapCanvas, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        Panes.addTab("Map", jPanel2);

        lblLiqour.setText("label5");

        lblLunchAndBreakfast.setText("label6");

        lblCooling.setText("label7");

        lblLuxury.setText("label8");

        lblDurable.setText("label9");

        label10.setText("label10");

        lblNonfoon.setText("label11");

        label12.setText("The Aisles contain:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lstDurable, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                .addComponent(lstLiqour, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblLiqour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDurable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lstVegAndFruit, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                                .addComponent(lstLunchAndBreakfast, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblLunchAndBreakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(lblCooling, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(97, 97, 97))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .addComponent(lstCooling, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblLuxury, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(listLuxury, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNonfoon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lstNonfood, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(44, 44, 44))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLiqour, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLunchAndBreakfast, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCooling, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLuxury, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(listLuxury, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lstLiqour, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lstCooling, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lstLunchAndBreakfast, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDurable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lstDurable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lstVegAndFruit, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblNonfoon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lstNonfood, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        Panes.addTab("Aisles", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(lstStorage, javax.swing.GroupLayout.PREFERRED_SIZE, 509, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lstStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
                .addContainerGap())
        );

        Panes.addTab("Storage", jPanel4);

        txtConsole.setColumns(20);
        txtConsole.setRows(5);
        jScrollPane1.setViewportView(txtConsole);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );

        Panes.addTab("Console", jPanel5);

        lblStaffName.setText("Staff Name:");

        lblItems.setText("Items");

        lblOtherStaffInfo.setText("Other Info");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblStaffName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(staffComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lstItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lstOtherStaffInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblOtherStaffInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblStaffName)
                    .addComponent(staffComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblItems, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lstItems, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(lstOtherStaffInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 221, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblOtherStaffInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        lblOtherStaffInfo.getAccessibleContext().setAccessibleDescription("");

        Panes.addTab("Staff", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panes)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panes)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void customerSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerSelectorActionPerformed

        lstShoppingList.clear();
        lstShoppingCart.clear();



        for (Item item : customers.get(customerSelector.getSelectedIndex()).getShoppingList()) {
            lstShoppingList.add(item.getName());
        }

        for (Item item : customers.get(customerSelector.getSelectedIndex()).getShoppingCart()) {
            lstShoppingCart.add(item.getName());
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Supermarket.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>


        Supermarket simulation;
        simulation = new Supermarket();
        simulation.setVisible(true);
        System.out.println("Supermarket initialized...");

        simulation.execStaffUpdate();
        simulation.redirectSystemStreams();
        while (true) { //Update loop
            simulation.customersLoop();
            simulation.staffLoop();
            simulation.interfaceUpdate();
            //Sleep at the end of the loop
            simulation.sleep(1000);
        }
    }

    private void execStaffUpdate() {
        for (Staff staff : staffMembers) {
            switch (staff.getFunction()) {
                case "stocker":
                    Stocker a = staff.getStocker();
                    a.update(staticLocations);
                    break;
                case "unloader":
                    Unloader unloader = staff.getUnloader();
                    unloader.update(staticLocations);
                    break;
            }
        }
    }

    private void interfaceUpdate() {
        g.clearRect(0, 0, 5000, 5000);
        lstShoppingCart.removeAll();
        lstOtherCustomerInfo.removeAll();

        g.setColor(Color.blue);
        g.drawRect((int) storage.getLocation().x * 4, (int) storage.getLocation().y * 4, 50, 50);

        g.setColor(Color.green);
        for (Aisle aisle : aisles) {
            g.drawRect((int) aisle.getLocation().x * 4, (int) aisle.getLocation().y * 4, 15, 30);
        }

        g.setColor(Color.red);
        for (Customer customer : customers) {
            g.drawRect((int) customer.getLocation().x * 4, (int) customer.getLocation().y * 4, 10, 10);
            g.drawString(customer.getName(), (int) customer.getLocation().x * 4, (int) customer.getLocation().y * 4);
        }


        for (Customer customer : customers) {
            g.drawRect((int) customer.getLocation().x * 4, (int) customer.getLocation().y * 4, 10, 10);
        }
        g.setColor(Color.PINK);
        for (Department department : departments) {
            g.drawRect((int) department.getLocation().x * 4, (int) department.getLocation().y * 4, 15, 15);
        }

        g.setColor(Color.orange);
        for (Checkout checkout : checkouts) {
            g.drawRect((int) checkout.getLocation().x * 4, (int) checkout.getLocation().y * 4, 10, 10);
        }

        g.setColor(Color.DARK_GRAY);
        g.drawRect((int) truck.getLocation().x * 4, (int) truck.getLocation().y * 4, 20, 40);
        try {
            lstOtherCustomerInfo.clear();
            lstOtherCustomerInfo.add("Name: " + customers.get(customerSelector.getSelectedIndex()).getName());
            lstOtherCustomerInfo.add("Saldo: " + customers.get(customerSelector.getSelectedIndex()).getSaldo());
            lstOtherCustomerInfo.add("Action: " + customers.get(customerSelector.getSelectedIndex()).getAction());
            lstOtherCustomerInfo.add("Stereotype: " + customers.get(customerSelector.getSelectedIndex()).getStereotype());
            lstOtherCustomerInfo.add("Locatio X:" + customers.get(customerSelector.getSelectedIndex()).getLocation().x + " Y:"
                    + customers.get(customerSelector.getSelectedIndex()).getLocation().y);
        } catch (Exception e) {
        }

        ArrayList<List> lists = new ArrayList<>();
        lists.add(lstLiqour);
        lists.add(lstLunchAndBreakfast);
        lists.add(lstCooling);
        lists.add(listLuxury);
        lists.add(lstDurable);
        lists.add(lstVegAndFruit);
        lists.add(lstNonfood);


        int counter = 0;
        for (int i = 0; i < lists.size(); i++) {
            lists.get(i).clear();
            for (Item shopItem : storeItems) {
                counter = aisles.get(i).getItemCount(shopItem);
                if (counter > 0) {
                    lists.get(i).add(counter + " " + shopItem.getName());
                }
            }
        }

        if (storage.isIsChanged()) {
            lstStorage.clear();
            for (Item item : storeItems) {
                counter = storage.getItemCount(item.getName());
                if (counter > 0) {
                    lstStorage.add(counter + " " + item.getName());
                }
            }
            storage.setIsChanged(false);
        }
        try {
            lstItems.clear();
            lstOtherStaffInfo.clear();
            if (staffMembers.get(staffComboBox.getSelectedIndex()).getCashier() != null) {
                lstItems.add("Cashier has no Items");
                lstOtherStaffInfo.add("Name: " + staffMembers.get(staffComboBox.getSelectedIndex()).getName());
                lstOtherStaffInfo.add("Function: Cashier");
            } else if (staffMembers.get(staffComboBox.getSelectedIndex()).getUnloader() != null) {
                for (Item item : staffMembers.get(staffComboBox.getSelectedIndex()).getUnloader().getShopItems()) {
                    lstItems.add(item.getName());
                }
                lstOtherStaffInfo.add("Name: " + staffMembers.get(staffComboBox.getSelectedIndex()).getName());
                lstOtherStaffInfo.add("Function: Unloader");
            } else if (staffMembers.get(staffComboBox.getSelectedIndex()).getStocker() != null) {
                for (Item item : staffMembers.get(staffComboBox.getSelectedIndex()).getStocker().getItems()) {
                    lstItems.add(item.getName());
                }
                lstOtherStaffInfo.add("Name: " + staffMembers.get(staffComboBox.getSelectedIndex()).getName());
                lstOtherStaffInfo.add("Function: Stocker");
            }
        } catch (Exception e) {
        }


    }

    private void updateTextArea(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtConsole.append(text);
            }
        });
    }

    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateTextArea(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateTextArea(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

    private void staffLoop() {
        if (!storage.getItems().isEmpty()) {
            for (Staff staff : staffMembers) {
                if (staff.getFunction().equals("stocker")) {
                    Stocker stocker = staff.getStocker();
                    if (!stocker.isWorking() && stocker.getAisle() == null) {
                        for (Aisle aisle : aisles) {
                            if (aisle.getItems().size() < 10) {
                                stocker.setAisle(aisle);
                            }
                        }
                    }
                }
            }
        }
        
        for (int i = 1; i < checkouts.size(); i++) {
            Checkout currentCheckout = checkouts.get(i);
            Checkout previousCheckout = checkouts.get(i - 1);
            if (previousCheckout.getStatus() == Status.CROWDED) {
                if (currentCheckout.getStatus() == Status.CLOSED || currentCheckout.getStatus() == Status.CLOSING) {
                    for (Staff staff : staffMembers) {
                        if (staff.getFunction() == "cashier") {
                            Cashier cashier = staff.getCashier();
                            if (cashier.isWaiting()) {
                                cashier.setCheckOut(currentCheckout);
                            }
                        }
                    }
                }
            }

            if (previousCheckout.getStatus() == Status.OPEN) {
                currentCheckout.closing();
            }
        }
    }

    private void customersLoop() {
        addEnteringCustomers();

        ArrayList<Customer> leavingCustomers = new ArrayList<>();
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).isLeaving()) {
                leavingCustomers.add(customers.get(i));
                customerSelector.removeItemAt(i);
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


                String name = "Nr. " + ((int) customers.size() + 1);
                customers.add(new Customer("CUSTOMER " + name, entrance.getLocation(), stereotype.get(0), storeItems));
                customerSelector.addItem(customers.get(customers.size() - 1).getName());
                customers.get(customers.size() - 1).update(staticLocations);
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
    private javax.swing.JTabbedPane Panes;
    private javax.swing.JComboBox customerSelector;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label10;
    private java.awt.Label label12;
    private java.awt.Label lblCooling;
    private java.awt.Label lblDurable;
    private java.awt.Label lblItems;
    private java.awt.Label lblLiqour;
    private java.awt.Label lblLunchAndBreakfast;
    private java.awt.Label lblLuxury;
    private java.awt.Label lblNonfoon;
    private java.awt.Label lblOtherCustomerInfo;
    private java.awt.Label lblOtherStaffInfo;
    private java.awt.Label lblSelectCustomer;
    private java.awt.Label lblShoppingCart;
    private java.awt.Label lblSjoppingList;
    private javax.swing.JLabel lblStaffName;
    private java.awt.List listLuxury;
    private java.awt.List lstCooling;
    private java.awt.List lstDurable;
    private java.awt.List lstItems;
    private java.awt.List lstLiqour;
    private java.awt.List lstLunchAndBreakfast;
    private java.awt.List lstNonfood;
    private java.awt.List lstOtherCustomerInfo;
    private java.awt.List lstOtherStaffInfo;
    private java.awt.List lstShoppingCart;
    private java.awt.List lstShoppingList;
    private java.awt.List lstStorage;
    private java.awt.List lstVegAndFruit;
    private java.awt.Canvas mapCanvas;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private java.awt.PopupMenu popupMenu1;
    private java.awt.PopupMenu popupMenu2;
    private javax.swing.JComboBox staffComboBox;
    private javax.swing.JTextArea txtConsole;
    // End of variables declaration//GEN-END:variables
}
