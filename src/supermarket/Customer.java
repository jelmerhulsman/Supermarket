package supermarket;

import com.jme3.math.Vector2f;
import java.util.ArrayList;
import supermarket.Checkout.Status;
import supermarket.Item.Category;

public class Customer extends ObjectInShop {

    private enum Action {

        SHOPPING, CHOOSE_CHECKOUT, WAITING_AT_CHECKOUT
    }

    public enum Stereotype {

        ELDER, MOTHER, STUDENT, WORKER
    };
    private Stereotype stereotype;
    private float saldo;
    private Action action;
    private ArrayList<Item> shoppingList, shoppingCart;

    public Customer(String name, Stereotype stereotype, ArrayList<Item> uniqueItems) {
        super(name, new Vector2f(100, 100));
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

        action = Action.SHOPPING;
        shoppingList = generateShoppingList(stereotype, uniqueItems);
        shoppingCart = new ArrayList<>();
    }

    private float giveSaldo(int min, int max) {
        float amount = (int) (Math.random() * (max - min)) + min;
        amount += (int) (Math.random() * 10) / 10;
        if (chanceOf(50)) {
            amount += 0.05f;
        }

        return amount;
    }

    private float giveSpeed(boolean slow) {
        if (slow) {
            return 0.5f;
        } else {
            return 1f;
        }
    }

    private boolean chanceOf(int percent) {
        return (int) (Math.random() * 101) <= percent;
    }

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

    public Aisle getFirstItemLocation(ArrayList<ObjectInShop> staticLocations) {
        for (ObjectInShop object : staticLocations) {
            if (object instanceof Aisle) {
                Aisle aisle = (Aisle) object;
                for (Category category : aisle.getCategories()) {
                    if (shoppingList.get(0).getCategory() == category) {
                        return aisle;
                    }
                }
            }
        }

        return null;
    }

    public void getItemsFromAisle(Aisle aisle) {
        ArrayList<Category> aisleCategories = aisle.getCategories();
        ArrayList<Item> checkedItems = new ArrayList<>();

        for (Item item : shoppingList) {
            if (aisleCategories.contains(item.getCategory())) {
                if (aisle.getItemCount(item) > 0) {
                    shoppingCart.add(aisle.pickFromShelve(item));
                }

                checkedItems.add(item);
            }
        }

        shoppingList.removeAll(checkedItems);
    }

    public Checkout chooseCheckout(ArrayList<Checkout> checkouts) {
        Checkout c = null;
        int size = 1000;

        for (Checkout checkout : checkouts) { //Check all open checkouts
            Status status = checkout.getStatus();
            if ((status == Status.OPEN || status == Status.CROWDED) && checkout.getCustomersCount() < size) {
                c = checkout;
                size = checkout.getCustomersCount();
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

    public boolean update(ArrayList<ObjectInShop> staticLocations, ArrayList<Checkout> checkouts) {
        boolean stopUpdating = false;

        switch (action) {
            case SHOPPING:
                Aisle aisle = getFirstItemLocation(staticLocations);
                if (location != aisle.getLocation()) {
                    gotoLocation(aisle.getName(), staticLocations);
                }
                
                getItemsFromAisle(aisle);

                if (shoppingList.isEmpty()) {
                    action = Action.CHOOSE_CHECKOUT;
                }
                break;
            case CHOOSE_CHECKOUT:
                if (shoppingCart.isEmpty()) {
                    stopUpdating = true;
                } else {
                    Checkout checkout = chooseCheckout(checkouts);
                    checkout.addCustomer(this);
                    action = action.WAITING_AT_CHECKOUT;
                }
                break;
            case WAITING_AT_CHECKOUT:
                if (shoppingCart.isEmpty()) {
                    stopUpdating = true;
                }
                break;
        }

        return stopUpdating;
    }
}
