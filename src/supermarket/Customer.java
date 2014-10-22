package supermarket;

import java.util.List;
import supermarket.Item.Category;

public class Customer {

    public enum Stereotype {

        ELDER, MOTHER, STUDENT, WORKER
    };
    private String name;
    private Stereotype stereotype;
    private float saldo;
    private float speed;
    private List<Item> shoppingList, shoppingCart;

    public Customer(String name, Stereotype stereotype, List<Item> uniqueItems) {
        this.name = name;
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

        shoppingList = getShoppingList(stereotype, uniqueItems);
        shoppingCart = null;
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

    private List<Item> getShoppingList(Stereotype stereotype, List<Item> uniqueItems) {
        List<Category> blackList = null;
        List<Category> likingList = null;

        switch (stereotype) {
            case ELDER:
                blackList.add(Category.SPICYFOOD);
                likingList.add(Category.VEGTABLES);
                break;
            case MOTHER:
                blackList.add(Category.SPICYFOOD);
                likingList.add(Category.VEGTABLES);
                break;
            case STUDENT:
                blackList.add(Category.SPICYFOOD);
                likingList.add(Category.VEGTABLES);
                break;
            case WORKER:
                blackList.add(Category.SPICYFOOD);
                likingList.add(Category.VEGTABLES);
                break;
        }

        List<Item> items = null;
        float costs = 0;

        do {
            for (Item item : uniqueItems) {
                boolean addToList = false;
                if (blackList == null || !blackList.contains(item.getCategory())) {
                    if (item.isPrimary()) {
                        addToList = chanceOf(90);
                    } else if (likingList == null) {
                        addToList = chanceOf(60);
                    } else if (likingList.contains(item.getCategory())) {
                        addToList = chanceOf(80);
                    } else {
                        addToList = chanceOf(20);
                    }
                }

                if (addToList) {
                    if (costs + item.getPrice() <= saldo) {
                        costs += item.getPrice();
                        shoppingList.add(item);
                    }
                }
            }
        } while (items == null);

        return items;
    }
}
