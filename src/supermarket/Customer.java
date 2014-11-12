package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Checkout.Status;
import supermarket.Item.Category;

/**
 *
 * @author SDJM
 */
public class Customer extends Person {

    private enum Action {

        ENTERING, SHOPPING, CHOOSING_CHECKOUT, WAITING, LEAVING
    }

    public enum Stereotype {

        ELDER, MOTHER, STUDENT, WORKER
    };
    private Stereotype stereotype;
    private float beginWithSaldo;
    private float saldo;
    private Action action;
    private ArrayList<Item> shoppingList, shoppingCart;
    private int collectedItemsCount;
    private Customer me;

    public Customer(String name, Vector2f spawnLocation, Stereotype stereotype, ArrayList<Item> uniqueItems) {
        super(name, spawnLocation);
        this.stereotype = stereotype;

        switch (stereotype) {
            case ELDER:
                saldo = giveSaldo(70, 250);
                speed = giveSpeed(chanceOf(90));
                break;
            case MOTHER:
                saldo = giveSaldo(40, 80);
                speed = giveSpeed(chanceOf(15));
                break;
            case STUDENT:
                saldo = giveSaldo(5, 35);
                speed = giveSpeed(chanceOf(5));
                break;
            case WORKER:
                saldo = giveSaldo(100, 150);
                speed = giveSpeed(chanceOf(20));
                break;
        }

        beginWithSaldo = saldo;
        action = Action.ENTERING;
        shoppingList = generateShoppingList(stereotype, uniqueItems);
        shoppingCart = new ArrayList<>();
        collectedItemsCount = 0;

        me = this;
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
     * Gives this customer either a normal walking speed or a slow walking speed
     *
     * @param slow is this person an old fart?
     * @return the speed
     */
    private float giveSpeed(boolean slow) {
        if (slow) {
            return 0.5f;
        } else {
            return 1f;
        }
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
    private ArrayList<Item> generateShoppingList(Stereotype stereotype, ArrayList<Item> availableItems) {
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

        ArrayList<Item> items = new ArrayList<>();
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
                        items.add(item);
                    }
                }
            }
        } while (items.isEmpty());

        return items;
    }

    /**
     * Gets the first item from the aisle
     *
     * @param staticLocations a collection of all possible locations
     * @return
     */
    private Aisle getFirstItemLocation(ArrayList<ObjectInShop> staticLocations) {
        for (ObjectInShop o : staticLocations) {
            if (o instanceof Aisle) {
                Aisle tempAisle = (Aisle) o;
                ArrayList<String> aisleItemNames = tempAisle.getItemNames();
                if (aisleItemNames.contains(shoppingList.get(0).getName())) {
                    return tempAisle;
                }
            }
        }

        shoppingList.clear();
        return null;
    }

    public ArrayList<Item> getShoppingList() {
        return shoppingList;
    }

    public Action getAction() {
        return action;
    }

    public Stereotype getStereotype() {
        return stereotype;
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
            if (aisleItemNames.contains(item.getName())) {
                if (aisle.getItemCount(item) > 0) {
                    shoppingCart.add(aisle.pickFromShelve(item));
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
                Checkout tempCheckout = (Checkout) o;
                Status status = tempCheckout.getStatus();
                if ((status == Status.OPEN || status == Status.CROWDED) && tempCheckout.getCustomersCount() < size) {
                    c = tempCheckout;
                    size = tempCheckout.getCustomersCount();
                }
            }
        }

        return c;
    }

    public ArrayList<Item> getShoppingCart() {
        return shoppingCart;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }

    /**
     * this method will loop over and over
     * @param staticLocations the collection of all the possible locations
     */
    @Override
    public void update(final ArrayList<ObjectInShop> staticLocations) {
        operation = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    switch (action) {
                        case ENTERING:
                            String targetName = "Entrance";
                            gotoCoords(new Vector2f(location.x, location.y - 20), targetName);
                            action = Action.SHOPPING;
                            break;
                        case SHOPPING:
                            Aisle aisle = getFirstItemLocation(staticLocations);

                            gotoLocation(aisle.getName(), staticLocations);
                            getItemsFromAisle(aisle);

                            if (shoppingList.isEmpty()) {
                                action = Action.CHOOSING_CHECKOUT;
                            }
                            break;
                        case CHOOSING_CHECKOUT:
                            if (shoppingCart.isEmpty()) {
                                gotoLocation("Entrance/Exit", staticLocations);
                                action = Action.LEAVING;
                            } else {
                                Checkout checkout = chooseCheckout(staticLocations);
                                checkout.addCustomer(me);
                                gotoCoords(checkout.getLocation(), "Checkout " + checkout.getNumber());
                                collectedItemsCount = shoppingCart.size();
                                action = Action.WAITING;
                            }
                            break;
                        case WAITING:
                            if (saldo < beginWithSaldo && shoppingCart.size() == collectedItemsCount) {
                                gotoLocation("Entrance/Exit", staticLocations);
                                action = Action.LEAVING;
                            }
                            break;
                    }
                }
            }
        });
        operation.start();
    }
}
