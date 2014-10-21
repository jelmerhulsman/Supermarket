package supermarket;

public class Item {
    private String name;
    public enum Category  { WINE, SNACK, SODA, BEER, LIQUOR, DIARY, SPICYFOOD, NONSPICYFOOD, FROZENFOOD, CANNEDFOOD }
    public enum Status { LOADED, INSTORAGE, ATCUSTOMER, ATSTAFF, INTRUCK  } 
    private float price;
    private Status status;
    private Category category;
    
    public Item(String name, Category category, float price){
        this.name = name;
        this.price = price;
        this.category = category;
        this.status = Status.INTRUCK;
    }

    /**
     * Gets the Status of the Item
     * @return wether the item is loaded, at a customer, purchased and whatever
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets the current category of this item
     * @return The current category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Gets the current price for the customer
     * @return The current price for the customer
     */
    public float getPrice() {
        return price;
    }

    /**
     * Gets the name of this item
     * @return The name of this item
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the category of this item to something else
     * @param category The new category for this item
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * Sets the status of this item to something else
     * @param status wether the item is loaded, at a customer, purchased and whatever
     */
    public void setStatus(Status status) {
        this.status = status;
    }
    
}
