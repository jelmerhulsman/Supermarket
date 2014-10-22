package supermarket;

public class Item {

    public enum Category {

        WINE, SNACK, SODA, BEER, LIQUOR, DAIRY, SPICYFOOD, NONSPICYFOOD, FROZENFOOD, CANNEDFOOD, FRESH, VEGTABLES
    }

    public enum Status {

        LOADED, INSTORAGE, ONCUSTOMER, ONSTAFF, INTRUCK
    }
    private String name;
    private float price;
    private boolean primary;
    private Status status;
    private Category category;

    public Item(String name, Category category, float price, boolean primary) {
        this.name = name;
        this.price = price;
        this.primary = primary;
        this.category = category;
        this.status = Status.INTRUCK;
    }

    /**
     * Gets the Status of the Item
     *
     * @return wether the item is loaded, at a customer, purchased and whatever
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets the current category of this item
     *
     * @return The current category
     */
    public Category getCategory() {
        return category;
    }
    
    /**
     * Returns the primary status of this item
     *
     * @return The primary status of this item
     */
    public boolean isPrimary() {
        return primary;
    }

    /**
     * Gets the current price for the customer
     *
     * @return The current price for the customer
     */
    public float getPrice() {
        return price;
    }

    /**
     * Gets the name of this item
     *
     * @return The name of this item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the category of this item to something else
     *
     * @param category The new category for this item
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Sets the status of this item to something else
     *
     * @param status wether the item is loaded, at a customer, purchased and
     * whatever
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
