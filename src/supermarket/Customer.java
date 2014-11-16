package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import supermarket.Item.Category;

/**
 *
 * @author SDJM
 */
public class Customer extends Person {

    final private int ITEM_SEARCH_TIME = 750;

    private enum Action {

        ENTERING, SHOPPING_AT_DEPARTMENTS, SHOPPING_AT_AISLES, CHOOSING_CHECKOUT, WAITING, LEAVING
    }

    public enum Stereotype {

        ELDER, MOTHER, STUDENT, WORKER
    };
    private Stereotype stereotype;
    private float beginWithSaldo;
    private float saldo;
    private Action action;
    private int collectedItemsCount;
    private ArrayList<Item> shoppingList, shoppingBasket;
    private Customer myself;

    /**
     * Make a customer
     *
     * @param name The name of the customer
     * @param location The startinglocation of the customer
     * @param stereotype The type of stereotype of this customer
     * @param uniqueItems A list of unique items favoured by this customer
     */
    public Customer(String name, Vector2f location, Stereotype stereotype, ArrayList<Item> uniqueItems) {
        super(name, location);

        this.stereotype = stereotype;
        switch (stereotype) {
            case ELDER:
                saldo = giveSaldo(70, 250);
                this.multiplySpeed(0.5f);
                break;
            case MOTHER:
                saldo = giveSaldo(40, 80);
                this.multiplySpeed(0.8f);
                break;
            case STUDENT:
                saldo = giveSaldo(5, 35);
                this.multiplySpeed(1f);
                break;
            case WORKER:
                saldo = giveSaldo(100, 150);
                this.multiplySpeed(1.1f);
                break;
        }

        beginWithSaldo = saldo;
        action = Action.ENTERING;
        setShoppingList(stereotype, uniqueItems);
        collectedItemsCount = 0;
        shoppingBasket = new ArrayList<>();

        myself = this; //needed for runnable
    }

    /**
     * Give this customer some random $$$ cash
     *
     * @param min the minimum amount of cash to be expected
     * @param max the maximum amount of cash to be expected
     * @return a random amount of $$$ between the min and max
     */
    private float giveSaldo(int min, int max) {
        float amount = (int) (Math.random() * (max - min)) + min;
        amount += (int) (Math.random() * 10) / 10;
        if (chanceOf(50)) {
            amount += 0.05f;
        }

        return amount;
    }

    /**
     * Used to give something an appropriate chance to occurfawegae
     *
     * @param percent the chance
     * @return either true or false in correllation with the chance
     */
    private boolean chanceOf(int percent) {
        return (int) (Math.random() * 101) <= percent;
    }

    /**
     * Used to check wether this customer is leaving or not
     *
     * @return true/false
     */
    public boolean isLeaving() {
        if (action == Action.LEAVING) {
            operation.stop();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gives the corresponding shopping list to a specified customer type
     *
     * @param stereotype which customer type are we dealing with?
     * @param availableItems which total collection of items are we dealing
     * with?
     * @return
     */
    private void setShoppingList(Stereotype stereotype, ArrayList<Item> availableItems) {
        ArrayList<Category> blackList = new ArrayList<>();
        ArrayList<Category> likingList = new ArrayList<>();

        switch (stereotype) {
            case ELDER:
                blackList.add(Category.SPICES);
                blackList.add(Category.FOREIGN);
                blackList.add(Category.CAFFEINE);
                likingList.add(Category.VEGTABLES);
                likingList.add(Category.DAIRY);
                break;
            case MOTHER:
                blackList.add(Category.READY_TO_EAT);
                break;
            case STUDENT:
                blackList.add(Category.NONFOOD);
                blackList.add(Category.WINE);
                blackList.add(Category.VEGTABLES);
                blackList.add(Category.FRUIT);
                likingList.add(Category.BEER);
                likingList.add(Category.LIQUOR);
                likingList.add(Category.CAFFEINE);
                likingList.add(Category.FROZEN);
                likingList.add(Category.READY_TO_EAT);
                break;
            case WORKER:
                blackList.add(Category.NONFOOD);
                likingList.add(Category.BEER);
                likingList.add(Category.LIQUOR);
                likingList.add(Category.FROZEN);
                likingList.add(Category.CAFFEINE);
                likingList.add(Category.SNACK);
                likingList.add(Category.READY_TO_EAT);
                break;
        }

        shoppingList = new ArrayList<>();
        float costs = 0;
        do {
            for (Item item : availableItems) {
                boolean addToList = false;
                if (blackList.isEmpty() || !blackList.contains(item.getCategory())) {
                    if (likingList.isEmpty()) {
                        addToList = chanceOf(60);
                    } else if (likingList.contains(item.getCategory())) {
                        addToList = chanceOf(80);
                    } else {
                        addToList = chanceOf(50);
                    }
                }

                if (addToList) {
                    if (costs + item.getPrice() <= saldo) {
                        costs += item.getPrice();
                        shoppingList.add(item);
                    }
                }
            }
        } while (shoppingList.isEmpty());

        long seed = System.nanoTime();
        Collections.shuffle(shoppingList, new Random(seed));
    }

    /**
     * Gets the first department to go to
     *
     * @param staticLocations a collection of all possible locations
     * @return
     */
    private Department getFirstDepartment(ArrayList<ObjectInShop> staticLocations) {
        for (ObjectInShop o : staticLocations) {
            if (o instanceof Department) {
                Department temp = (Department) o;
                ArrayList<String> departmentItemNames = temp.getItemNames();
                for (Item item : shoppingList) {
                    if (departmentItemNames.contains(item.getName())) {
                        return temp;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Gets the first aisle to go to
     *
     * @param staticLocations a collection of all possible locations
     * @return
     */
    private Sales getSalesAisle(ArrayList<ObjectInShop> staticLocations) {
        for (ObjectInShop o : staticLocations) {
            if (o instanceof Sales) {
                Sales sales = (Sales) o;
                for (Item item : shoppingList) {
                    if (sales.getItems().contains(item)) {
                        return sales;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Gets the first aisle to go to
     *
     * @param staticLocations a collection of all possible locations
     * @return
     */
    private Aisle getFirstAisle(ArrayList<ObjectInShop> staticLocations) {
        for (ObjectInShop o : staticLocations) {
            if (o instanceof Aisle) {
                Aisle temp = (Aisle) o;
                ArrayList<String> aisleItemNames = temp.getItemNames();
                if (aisleItemNames.contains(shoppingList.get(0).getName())) {
                    return temp;
                }
            }
        }

        shoppingList.clear();
        return null;
    }

    /**
     * Adds an item to this customer his shopping cart/basket
     *
     * @param item the item being added
     */
    public void addItemToBasket(Item item) {
        shoppingBasket.add(item);

        Item i = null;
        for (Item i2 : shoppingList) {
            if (item.getName().equals(i2.getName())) {
                i = i2;
                break;
            }
        }

        shoppingList.remove(i);
    }

    public ArrayList<Item> getShoppingList() {
        return shoppingList;
    }

    /**
     * returns all the item names from the shopping list
     *
     * @return
     */
    public ArrayList<String> getShoppingListItemNames() {
        ArrayList<String> itemNames = new ArrayList<>();

        for (Item item : shoppingList) {
            if (!itemNames.contains(item.getName())) {
                itemNames.add(item.getName());
            }
        }
        return itemNames;
    }

    public Action getAction() {
        return action;
    }

    public Stereotype getStereotype() {
        return stereotype;
    }

    /**
     * Used for departments
     */
    public void helped() {
        action = Action.SHOPPING_AT_DEPARTMENTS;
    }

    /**
     * Gets items which are listed on the shopping list from the aisle
     *
     * @param aisle the aisle we are picking items from
     */
    private void getItemsFromAisle(Aisle aisle) {
        ArrayList<String> aisleItemNames = aisle.getItemNames();
        ArrayList<Item> checkedItems = new ArrayList<>();

        for (Item item : shoppingList) {
            sleep(ITEM_SEARCH_TIME);

            if (aisleItemNames.contains(item.getName())) {
                if (aisle.getItemCount(item) > 0 && aisle.getItems().get(0).isAvailable()) {
                    if (aisle instanceof Sales) {
                        item.discount();
                    }
                    shoppingBasket.add(aisle.pickFromShelve(item));
                }

                sleep(ITEM_INTERACTION_TIME);
                checkedItems.add(item);
            }
        }

        shoppingList.removeAll(checkedItems);
    }

    /**
     * Used for the customer to choose an open checkout
     *
     * @param staticLocations a collection of all the checkouts
     * @return
     */
    public Checkout chooseCheckout(ArrayList<ObjectInShop> staticLocations) {
        Checkout c = null;
        int size = 1000;

        for (ObjectInShop o : staticLocations) { //Check all open checkouts
            if (o instanceof Checkout) {
                Checkout temp = (Checkout) o;
                if ((temp.isOpen() || temp.isCrowded()) && temp.getCustomersCount() < size) {
                    c = temp;
                    size = temp.getCustomersCount();
                }
            }
        }

        return c;
    }

    public ArrayList<Item> getShoppingBasket() {
        return shoppingBasket;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }

    /**
     * this function will be looped over and over
     *
     * @param staticLocations the collection of locations
     */
    @Override
    public void update(final ArrayList<ObjectInShop> staticLocations) {
        operation = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    switch (action) {
                        case ENTERING:
                            gotoCoords(new Vector2f(location.x, 200));
                            action = Action.SHOPPING_AT_DEPARTMENTS;
                            break;
                        case SHOPPING_AT_DEPARTMENTS:
                            Department department = getFirstDepartment(staticLocations);

                            if (department != null) {
                                gotoLocation(department.getName(), staticLocations);
                                department.addCustomer(myself);
                                action = Action.WAITING;
                            } else {
                                action = Action.SHOPPING_AT_AISLES;
                            }
                            break;
                        case SHOPPING_AT_AISLES:
                            Sales sales = getSalesAisle(staticLocations);
                            if (sales != null) {
                                gotoLocation(sales.getName(), staticLocations);
                                getItemsFromAisle(sales);
                            } else {
                                Aisle aisle = getFirstAisle(staticLocations);

                                gotoLocation(aisle.getName(), staticLocations);
                                getItemsFromAisle(aisle);
                            }

                            if (shoppingList.isEmpty()) {
                                action = Action.CHOOSING_CHECKOUT;
                            }
                            break;
                        case CHOOSING_CHECKOUT:
                            if (shoppingBasket.isEmpty()) {
                                gotoLocation("Doorway", staticLocations);
                                action = Action.LEAVING;
                                System.out.println("Customer " + name + "is now leaving (without paying)...");
                            } else {
                                Checkout checkout = chooseCheckout(staticLocations); //Choose from far away
                                gotoLocation(checkout.getName(), staticLocations);
                                checkout = chooseCheckout(staticLocations); //Check from close distance
                                gotoLocation(checkout.getName(), staticLocations);
                                checkout.addCustomer(myself);
                                collectedItemsCount = shoppingBasket.size();
                                action = Action.WAITING;
                                System.out.println("Customer " + name + "is now waiting in line at " + checkout.getName() + " #" + checkout.getNumber() + "...");
                            }
                            break;
                        case WAITING:
                            if (saldo < beginWithSaldo && shoppingBasket.size() == collectedItemsCount) {
                                gotoLocation("Doorway", staticLocations);
                                action = Action.LEAVING;
                                System.out.println("Customer " + name + " has left.");
                            }
                            break;
                    }
                }
            }
        });
        operation.start();
        operation.setName("Customer " + name);
    }
}
