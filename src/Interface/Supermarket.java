package Interface;

import com.jme3.math.Vector2f;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.List;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.SwingUtilities;
import supermarket.Aisle;
import supermarket.Sales;
import supermarket.Checkout;
import supermarket.Customer;
import supermarket.Department;
import supermarket.Item;
import supermarket.Item.Category;
import supermarket.ObjectInShop;
import supermarket.Person;
import supermarket.StaffTypes.Artisan;
import supermarket.StaffTypes.Cashier;
import supermarket.StaffTypes.Staff;
import supermarket.StaffTypes.Stocker;
import supermarket.StaffTypes.Unloader;
import supermarket.Storage;
import supermarket.Truck;

/**
 *
 * @author SDJM
 */
public class Supermarket extends javax.swing.JFrame {

    private final int MAX_CUSTOMERS = 10; //10;
    private final int CHANCE_OF_ENTERING = 12; //12;
    private ArrayList<Aisle> aisles;
    private Sales sales;
    private ArrayList<Department> departments;
    private Department bakery;
    private Department butchery;
    private ArrayList<Checkout> checkouts;
    private Storage storage;
    private Truck truck;
    private ObjectInShop doorway;
    private ArrayList<ObjectInShop> staticLocations;
    private ArrayList<Person> people;
    private ArrayList<Staff> workforce;
    private Unloader unloader;
    private Artisan baker;
    private Artisan butcher;
    private ArrayList<Stocker> stockers;
    private ArrayList<Cashier> cashiers;
    private ArrayList<Item> storeItems;
    private ArrayList<Item> departmentItems;
    private ArrayList<Item> aisleItems;
    private int customerCounter;
    private ArrayList<Customer> customers;
    //Graphics attributes
    private Graphics g;
    private ArrayList<List> aislesListboxList;

    /**
     * Creates new form Supermarket
     */
    @SuppressWarnings("empty-statement")
    public Supermarket() {
        initComponents();

        //Graphics
        mapCanvas.setBackground(Color.gray);
        g = mapCanvas.getGraphics();
        customerSelector.setLightWeightPopupEnabled(false);
        staffComboBox.setLightWeightPopupEnabled(false);

        //Add all unique items to a list
        departmentItems = new ArrayList<>();
        departmentItems.add(new Item("Spareribs", 5.30f, Item.Category.MEAT));
        departmentItems.add(new Item("Gluten-free Bread", 4.75f, Item.Category.BREAD));
        storeItems.addAll(departmentItems);

        aisleItems = new ArrayList<>();
        aisleItems.add(new Item("Heimstel-Jan", 0.80f, Item.Category.BEER));
        aisleItems.add(new Item("Ricewaffle", 1.20f, Item.Category.BREAKFAST));
        aisleItems.add(new Item("Slurpys", 2.00f, Item.Category.SODA));
        aisleItems.add(new Item("Ready 2 Eat Lasagne", 2.50f, Item.Category.READY_TO_EAT));
        aisleItems.add(new Item("Nazi-kraut", 2.80f, Item.Category.VEGTABLES));
        aisleItems.add(new Item("Tomahawkto", 0.50f, Item.Category.FRUIT));
        aisleItems.add(new Item("Moo-Moo Milk", 1.25f, Item.Category.DAIRY));
        aisleItems.add(new Item("Lice", 1.00f, Item.Category.FOREIGN));
        aisleItems.add(new Item("Ass-Whipe Deluxe", 1.40f, Item.Category.NONFOOD));
        aisleItems.add(new Item("Heimstel-Jan 25% off", 0.75f, Item.Category.BEER_IN_SALE));
        aisleItems.add(new Item("Moo-Moo Milk 25% off", 1.05f, Item.Category.DAIRY_IN_SALE));
       
        storeItems.addAll(aisleItems);


        //Create aisles

        aisles = new ArrayList<>();
        ArrayList<Category> aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.BEER);
        aisleCategories.add(Category.LIQUOR);
        aisleCategories.add(Category.WINE);
        aisles.add(new Aisle("Liquor", new Vector2f(150, 50), aisleCategories, aisleItems));
        lblLiqour.setText("Liquor");
        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.SPREAD);
        aisleCategories.add(Category.BREAKFAST);
        aisles.add(new Aisle("Lunch & Breakfast", new Vector2f(250, 200), aisleCategories, aisleItems));
        lblLunchAndBreakfast.setText("Lunch & Breakfast");
        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.FROZEN);
        aisleCategories.add(Category.READY_TO_EAT);
        aisleCategories.add(Category.DAIRY);
        aisles.add(new Aisle("Cooling", new Vector2f(150, 125), aisleCategories, aisleItems));
        lblCooling.setText("Cooling");
        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.SNACK);
        aisleCategories.add(Category.SODA);
        aisleCategories.add(Category.CAFFEINE);
        aisles.add(new Aisle("Luxury", new Vector2f(250, 50), aisleCategories, aisleItems));
        lblLuxury.setText("Luxury");


        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.SPICES);
        aisleCategories.add(Category.FOREIGN);
        aisleCategories.add(Category.PRESERVATION);
        aisles.add(new Aisle("Durable", new Vector2f(250, 275), aisleCategories, aisleItems));
        lblDurable.setText("Durable");
        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.VEGTABLES);
        aisleCategories.add(Category.FRUIT);
        aisles.add(new Aisle("Vegtables & Fruit", new Vector2f(250, 350), aisleCategories, aisleItems));
        label10.setText("Vegtables & Fruit");
        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.NONFOOD);
        aisles.add(new Aisle("Nonfood", new Vector2f(250, 125), aisleCategories, aisleItems));
        lblNonfoon.setText("Nonfood");

        aisleCategories = new ArrayList<>();
        aisleCategories.add(Category.BEER_IN_SALE);
        aisleCategories.add(Category.DAIRY_IN_SALE);
        sales = new Sales("Sales", new Vector2f(100, 50), aisleCategories, storeItems);
        lblSales.setText("Sales");
        
        //Create departments
        departments = new ArrayList<>();
        bakery = new Department("Bakery", new Vector2f(100, 190), Category.BREAD, departmentItems);
        departments.add(bakery);
        butchery = new Department("Butchery", new Vector2f(100, 220), Category.MEAT, departmentItems);
        departments.add(butchery);

        //Create checkouts
        checkouts = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            checkouts.add(new Checkout(i + 1, new Vector2f(100, 380 - (i * 35))));
        }

        //Create storage and truck
        storage = new Storage("Storage", new Vector2f(10, 100));
        truck = new Truck("Truck", new Vector2f(10, 10));
        doorway = new ObjectInShop("Doorway", new Vector2f(20, 400)) {
        };

        //Assign locations in the shop
        staticLocations = new ArrayList<>();
        staticLocations.addAll(aisles);        
        staticLocations.addAll(departments);
        staticLocations.addAll(checkouts);
        staticLocations.add(sales);
        staticLocations.add(storage);
        staticLocations.add(truck);
        staticLocations.add(doorway);

        //Create Staff members
        unloader = new Unloader("Jannes Panzerfaust", storage.getLocation(), storage, truck, aisleItems);

        baker = new Artisan("Bartje Zaanbrood", storage.getLocation(), bakery);
        butcher = new Artisan("Timo Gehaktbal", storage.getLocation(), butchery);

        stockers = new ArrayList<>();
        stockers.add(new Stocker("Jan de Bierman", storage.getLocation(), storage));
        stockers.add(new Stocker("Jip de Chip", storage.getLocation(), storage));
        stockers.add(new Stocker("Grietje Gezond", storage.getLocation(), storage));
        stockers.add(new Stocker("Kees Koeler", storage.getLocation(), storage));
//      stockers.add(new Stocker("Jacob Dubbelfris", storage.getLocation(), storage));
//      stockers.add(new Stocker("Pietje Nietsnut", storage.getLocation(), storage));
//      stockers.add(new Stocker("Achmed Joseph Adam Gelovig", storage.getLocation(), storage));
//      stockers.add(new Stocker("Triensje Treintje", storage.getLocation(), storage));


        cashiers = new ArrayList<>();
        cashiers.add(new Cashier("Johanna Doekoe", storage.getLocation(), checkouts.get(0)));

        workforce = new ArrayList<>();
        workforce.add(unloader);
        workforce.add(baker);
        workforce.add(butcher);
        workforce.addAll(stockers);
        workforce.addAll(cashiers);

        //List for the listboxes from aisles
        aislesListboxList = new ArrayList<>();
        aislesListboxList.add(lstLiqour);
        aislesListboxList.add(lstLunchAndBreakfast);
        aislesListboxList.add(lstCooling);
        aislesListboxList.add(listLuxury);
        aislesListboxList.add(lstDurable);
        aislesListboxList.add(lstVegAndFruit);
        aislesListboxList.add(lstNonfood);

        //Add staff to combo box
        for (Staff staff : workforce) {
            staffComboBox.addItem(staff.getName());
        }

        //List of customers
        customerCounter = 0;
        customers = new ArrayList<>();

        //List of all people
        people = new ArrayList<>();

        //Fills the departmentlistst
        lstDepartment1.add("Bakery makes:");
        lstDepartment2.add("Butchery makes:");
        for (String s : departments.get(0).getItemNames()) {
            lstDepartment1.add(s);
        }
        for (String s : departments.get(1).getItemNames()) {
            lstDepartment2.add(s);
        }
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
        lstSales = new java.awt.List();
        lblSales = new java.awt.Label();
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
        jPanel7 = new javax.swing.JPanel();
        textField1 = new java.awt.TextField();
        textArea1 = new java.awt.TextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        department1textpane = new javax.swing.JTextPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        lstDepartment1 = new java.awt.List();
        lstDepartment2 = new java.awt.List();

        popupMenu1.setLabel("popupMenu1");

        popupMenu2.setLabel("popupMenu2");

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        customerSelector.setMaximumRowCount(100);
        customerSelector.setBorder(new javax.swing.border.MatteBorder(null));
        customerSelector.setCursor(new java.awt.Cursor(java.awt.Cursor.W_RESIZE_CURSOR));
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
                                        .addGap(0, 182, Short.MAX_VALUE))
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
                .addContainerGap(72, Short.MAX_VALUE))
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

        lblSales.setText("label11");

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
                                    .addComponent(lstNonfood, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNonfoon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lstSales, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(44, 44, 44))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(label12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
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
                        .addGap(0, 1, Short.MAX_VALUE)
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
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(lblNonfoon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lstNonfood, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(lblSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lstSales, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                .addComponent(lstStorage, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addContainerGap())
        );

        Panes.addTab("Storage", jPanel4);

        txtConsole.setEditable(false);
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
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
                                .addGap(0, 226, Short.MAX_VALUE))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblOtherStaffInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        lblOtherStaffInfo.getAccessibleContext().setAccessibleDescription("");

        Panes.addTab("Staff", jPanel6);

        textField1.setText("textField1");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jScrollPane3.setViewportView(department1textpane);

        jScrollPane4.setViewportView(jTextPane2);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lstDepartment1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(lstDepartment2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lstDepartment2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addComponent(lstDepartment1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        Panes.addTab("Departments", jPanel7);

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
    }//GEN-LAST:event_customerSelectorActionPerformed

    /**
     * Start the simulation!
     *
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


        Supermarket simulation = new Supermarket();
        simulation.setVisible(true);
        simulation.redirectSystemStreams();
        simulation.executeStaffUpdate();
        simulation.drawStore();

        while (true) { //Update loop
            simulation.customersLoop();
            simulation.staffLoop();

            simulation.interfaceUpdate();
            simulation.sleep(1000); //Sleep at the end of the loop
        }
    }

    /**
     * Make sure all the staff updates are being called once
     */
    private void executeStaffUpdate() {
        for (Staff staff : workforce) {
            staff.update(staticLocations);
        }
    }

    /**
     * Used to generate new customers or properly leave customers when they're
     * out of bounds
     */
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

    /**
     * Used to add customers to the simulation with a random stereotype
     */
    private void addEnteringCustomers() {
        if (customers.size() < MAX_CUSTOMERS) {
            if (chanceOf(CHANCE_OF_ENTERING)) {
                ArrayList<Customer.Stereotype> stereotype;
                do {
                    stereotype = new ArrayList<>();
                    for (Customer.Stereotype s : Customer.Stereotype.values()) {
                        if (chanceOf(25)) {
                            stereotype.add(s);
                        }
                    }
                } while (stereotype.size() != 1);


                customerCounter++;
                Customer customer = new Customer("#" + customerCounter, doorway.getLocation(), stereotype.get(0), storeItems);
                customer.update(staticLocations);

                customers.add(customer);
                customerSelector.addItem(customer.getName());
            }
        }
    }

    /**
     * An extra staff loop used to check if there's an idle cashiere and
     * reassign him to be a stocker
     */
    private void staffLoop() {
        ArrayList<Cashier> formerCashiers = new ArrayList<>();
        for (Cashier cashier : cashiers) {
            if (cashier.isWaiting()) {
                Stocker stocker = new Stocker(cashier.getName(), cashier.getLocation(), storage);
                stocker.gotoLocation(storage.getName(), staticLocations);
                stockers.add(stocker);
                workforce.add(stocker);
                stocker.update(staticLocations);
                cashier.kill();
                formerCashiers.add(cashier);
            }
        }
        cashiers.removeAll(formerCashiers);
        workforce.removeAll(formerCashiers);

        handleCheckouts();
    }

    /**
     * Checks the checkouts if they need an extra cashiere
     */
    private void handleCheckouts() {
        for (int i = 1; i < checkouts.size(); i++) {
            Checkout currentCheckout = checkouts.get(i);
            Checkout previousCheckout = checkouts.get(i - 1);
            if (previousCheckout.isOpen() && (currentCheckout.isOpen() || currentCheckout.isCrowded())) {
                currentCheckout.closing();
            } else if (previousCheckout.isCrowded()) {
                if (currentCheckout.isClosed() || currentCheckout.isClosing() && !currentCheckout.isManned()) {
                    Stocker formerStocker = null;
                    for (Stocker stocker : stockers) {
                        if (stocker.isWaiting()) {
                            currentCheckout.setManned(true);
                            Cashier cashier = new Cashier(stocker.getName(), storage.getLocation(), currentCheckout);
                            cashiers.add(cashier);
                            workforce.add(cashier);
                            stocker.kill();
                            formerStocker = stocker;
                            cashier.update(staticLocations);
                            break;
                        }
                    }

                    if (formerStocker != null) {
                        stockers.remove(formerStocker);
                        workforce.remove(formerStocker);
                    }
                }
            }


        }
    }

    /**
     * used for an internal list of total people in the simulation
     */
    private void updatePeoplesList() {
        people = new ArrayList<>();
        people.addAll(workforce);
        people.addAll(customers);
    }

    /**
     * Draws the map with squares representing entities
     */
    private void drawStore() {
        String storeLocationName;

        g.setColor(Color.BLUE);// sets the colour for the storages on the map
        //draws the rectangle for the storage
        g.drawRect((int) storage.getLocation().x, (int) storage.getLocation().y, 50, 50);
        storeLocationName = storage.getName() + " " + peopleAtObject(storage);
        g.drawString(storeLocationName, (int) storage.getLocation().x, (int) storage.getLocation().y);

        g.setColor(Color.BLACK);// sets the colour for the Departments on the map
        for (Department department : departments) {
            //draws the rectangle for the department
            g.drawRect((int) department.getLocation().x, (int) department.getLocation().y, 50, 10);
            storeLocationName = department.getName() + " " + peopleAtObject(department);
            g.drawString(storeLocationName, (int) department.getLocation().x, (int) department.getLocation().y);
        }

        g.setColor(Color.GREEN);// sets the colour for the ailsel on the map
        for (Aisle aisle : aisles) {
            //draws the rectangle for the ailse
            g.drawRect((int) aisle.getLocation().x, (int) aisle.getLocation().y, 15, 30);
            storeLocationName = aisle.getName() + " " + peopleAtObject(aisle);
            g.drawString(storeLocationName, (int) aisle.getLocation().x, (int) aisle.getLocation().y);
        }

        g.setColor(Color.WHITE);
        g.drawRect((int) sales.getLocation().x, (int) sales.getLocation().y, 15, 30);
        storeLocationName = sales.getName() + " " + peopleAtObject(sales);
        g.drawString(storeLocationName, (int) sales.getLocation().x, (int) sales.getLocation().y);
        
        for (Checkout checkout : checkouts) {
            if (checkout.isClosed()) {
                g.setColor(Color.ORANGE);// sets the colour for the unmanned checkouts on the map
            } else {
                g.setColor(Color.YELLOW);// sets the colour for the manned checkouts on the map
            }
            //draws the rectangle for the checkout
            g.drawRect((int) checkout.getLocation().x, (int) checkout.getLocation().y, 10, 10);
            storeLocationName = checkout.getName() + " " + peopleAtObject(checkout);
            g.drawString(storeLocationName, (int) checkout.getLocation().x, (int) checkout.getLocation().y);
        }

        g.setColor(Color.DARK_GRAY);// sets the colour for the trucks on the map
        if (!truck.isEmpty() || unloader.getLocationObject() == truck) {
            //draws the rectangle for the truck
            g.drawRect((int) truck.getLocation().x, (int) truck.getLocation().y, 25, 10);
            storeLocationName = truck.getName() + " " + peopleAtObject(truck);
            g.drawString(storeLocationName, (int) truck.getLocation().x, (int) truck.getLocation().y);
        }
    }

    /**
     * Used for the map; Shows number of type of people there ( 'S' - staff, 'C'
     * - customers )
     *
     * @param object The location where you are checking
     * @return A string used on the map
     */
    private String peopleAtObject(ObjectInShop object) {
        int counter = 0;
        int counter2 = 0;
        updatePeoplesList();
        for (Person person : people) {
            if (person.getLocationObject() == object) {
                if (person instanceof Staff) {
                    counter++;
                } else {
                    counter2++;
                }
            }
        }

        if (counter == 0 && counter2 == 0) {
            return "(0)";
        } else if (counter == 0) {
            return "(" + counter2 + "C)";
        } else if (counter2 == 0) {
            return "(" + counter + "S)";
        } else {
            return "(" + counter + "S/" + counter2 + "C)";
        }
    }

    /**
     * Update for the interface with the latest simulation data
     */
    private void interfaceUpdate() {
        if (Panes.getSelectedIndex() == 0) {
            try {
                //cleans the other info list from the customers
                lstOtherCustomerInfo.removeAll();
                lstOtherCustomerInfo.add("Name: " + customers.get(customerSelector.getSelectedIndex()).getName());
                lstOtherCustomerInfo.add("Saldo: " + customers.get(customerSelector.getSelectedIndex()).getSaldo());
                lstOtherCustomerInfo.add("Action: " + customers.get(customerSelector.getSelectedIndex()).getAction());
                lstOtherCustomerInfo.add("Stereotype: " + customers.get(customerSelector.getSelectedIndex()).getStereotype());
                lstOtherCustomerInfo.add("Location: X->" + customers.get(customerSelector.getSelectedIndex()).getLocation().x + ", Y->"
                        + customers.get(customerSelector.getSelectedIndex()).getLocation().y);
                lstOtherCustomerInfo.add("Shop Location: " + customers.get(customerSelector.getSelectedIndex()).getLocationObject().getName());
            } catch (Exception e) {
            }

            try {
                lstShoppingList.removeAll();
                lstShoppingCart.removeAll();
                if (customers.get(customerSelector.getSelectedIndex()).getShoppingList().size() > 0) {
                    for (Item item : customers.get(customerSelector.getSelectedIndex()).getShoppingList()) {
                        lstShoppingList.add(item.getName());
                    }
                } else {
                    lstShoppingList.add("Currently has nothing...");
                }
                if (customers.get(customerSelector.getSelectedIndex()).getShoppingBasket().size() > 0) {
                    for (Item item : customers.get(customerSelector.getSelectedIndex()).getShoppingBasket()) {
                        lstShoppingCart.add(item.getName());
                    }
                } else {
                    lstShoppingCart.add("Currently has nothing...");
                }
            } catch (Exception e) {
            }
        } else if (Panes.getSelectedIndex() == 1) {
            //cleans the map
            g.clearRect(0, 0, 400, 400);
            drawStore();
            updatePeoplesList();
            for (Person person : people) {
                if (person.getLocationObject() == null) {
                    if (person instanceof Unloader) {
                        g.setColor(Color.CYAN);
                    } else if (person instanceof Artisan) {
                        g.setColor(Color.BLACK);
                    } else if (person instanceof Stocker) {
                        g.setColor(Color.PINK);
                    } else if (person instanceof Cashier) {
                        g.setColor(Color.YELLOW);
                    } else if (person instanceof Customer) {
                        g.setColor(Color.RED);
                    }

                    //draws the rectangle for this person
                    g.drawRect((int) person.getLocation().x, (int) person.getLocation().y, 10, 10);
                    g.drawString(person.getName(), (int) person.getLocation().x, (int) person.getLocation().y);
                }
            }
        } //update for the other customer info
        else if (Panes.getSelectedIndex() == 2) {
            //update for the listboxen to display what's in the aisles
            for (int i = 0; i < aislesListboxList.size(); i++) {
                if (aisles.get(i).isChanged()) {
                    aislesListboxList.get(i).removeAll();
                    try {
                        for (Item item : aisles.get(i).getItems()) {
                            aislesListboxList.get(i).add(item.getName());
                        }
                    } catch (Exception e) {
                    }

                    aisles.get(i).setChanged(false);
                }
            }
            
            if (sales.isChanged()) {
                lstSales.removeAll();
                if(!sales.getItems().isEmpty()){
                    for (Item item : sales.getItems()) {
                        lstSales.add(item.getName());
                    }
                }
            }
            sales.setChanged(false);            
        } else if (Panes.getSelectedIndex() == 3) {
            //update for the storage items list
            if (storage.isChanged()) {
                lstStorage.clear();
                for (Item item : aisleItems) {
                    int count = storage.getItemCount(item.getName());
                    if (count > 0) {
                        lstStorage.add(count + " " + item.getName() + " stored");
                    }
                }
                storage.setIsChanged(false);
            }
        } else if (Panes.getSelectedIndex() == 5) {
            //update for the staff
            try {
                lstItems.removeAll();
                lstOtherStaffInfo.removeAll();

                if (workforce.get(staffComboBox.getSelectedIndex()).getItems().size() > 0) {
                    for (Item item : workforce.get(staffComboBox.getSelectedIndex()).getItems()) {
                        lstItems.add(item.getName());
                    }
                } else {
                    lstItems.add("Currently holds nothing...");
                }

                lstOtherStaffInfo.add("Name: " + workforce.get(staffComboBox.getSelectedIndex()).getName());
                lstOtherStaffInfo.add("Location: X->" + workforce.get(staffComboBox.getSelectedIndex()).getLocation().x
                        + ", Y->" + workforce.get(staffComboBox.getSelectedIndex()).getLocation().y);
                try {
                    lstOtherStaffInfo.add("Current Object: " + workforce.get(staffComboBox.getSelectedIndex()).getLocationObject().getName());
                } catch (Exception e) {
                    lstOtherStaffInfo.add("Current Object: Nothing");
                }

                if (workforce.get(staffComboBox.getSelectedIndex()) instanceof Unloader) {
                    lstOtherStaffInfo.add("Function: Unloader");
                } else if (workforce.get(staffComboBox.getSelectedIndex()) instanceof Stocker) {
                    lstOtherStaffInfo.add("Function: Stocker");
                } else if (workforce.get(staffComboBox.getSelectedIndex()) instanceof Cashier) {
                    lstOtherStaffInfo.add("Function: Cashier");
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * needed for the redirectSystemStreams
     *
     * @param text
     */
    private void updateConsole(final String text) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                txtConsole.append(text);
            }
        });
    }

    /**
     * redirects the console output to a textbox
     */
    private void redirectSystemStreams() {
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                updateConsole(String.valueOf((char) b));
            }

            @Override
            public void write(byte[] b, int off, int len) throws IOException {
                updateConsole(new String(b, off, len));
            }

            @Override
            public void write(byte[] b) throws IOException {
                write(b, 0, b.length);
            }
        };

        System.setOut(new PrintStream(out, true));
        System.setErr(new PrintStream(out, true));
    }

    /**
     * Used to make a percentage chance
     *
     * @param percent The percentage you are using
     * @return either true or false
     */
    private boolean chanceOf(int percent) {
        return (int) (Math.random() * 101) <= percent;
    }

    /**
     * Used for threads to make them sleep for a little while
     *
     * @param milliseconds total milliseconds for a thread to sleep
     */
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds); //1000 milliseconds is one second.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Panes;
    private javax.swing.JComboBox customerSelector;
    private javax.swing.JTextPane department1textpane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane2;
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
    private java.awt.Label lblSales;
    private java.awt.Label lblSelectCustomer;
    private java.awt.Label lblShoppingCart;
    private java.awt.Label lblSjoppingList;
    private javax.swing.JLabel lblStaffName;
    private java.awt.List listLuxury;
    private java.awt.List lstCooling;
    private java.awt.List lstDepartment1;
    private java.awt.List lstDepartment2;
    private java.awt.List lstDurable;
    private java.awt.List lstItems;
    private java.awt.List lstLiqour;
    private java.awt.List lstLunchAndBreakfast;
    private java.awt.List lstNonfood;
    private java.awt.List lstOtherCustomerInfo;
    private java.awt.List lstOtherStaffInfo;
    private java.awt.List lstSales;
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
    private java.awt.TextArea textArea1;
    private java.awt.TextField textField1;
    private javax.swing.JTextArea txtConsole;
    // End of variables declaration//GEN-END:variables
}
