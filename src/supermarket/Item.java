package supermarket;

import javax.persistence.*;

/**
 *
 * @author SDJM
 */
@Entity
@Table(name = "items")
public class Item {
    @Transient
    private final int DISCOUNT_PERCENTAGE = 20;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    public enum Category {

        BEER, LIQUOR, WINE, CAFFEINE, SNACK, SODA, DAIRY, SPICES, NONFOOD, FROZEN, PRESERVATION, BREAD, MEAT, BREAKFAST, SPREAD, VEGTABLES, FRUIT, FOREIGN, READY_TO_EAT
    }
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private float price;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;
    @Transient
    private boolean available;

    public Item() {
    }

    /**
     * Creates an item -INTERNAL USE ONLY-
     *
     * @param item
     */
    public Item(Item item) {
        this.name = item.name;
        this.price = item.price;
        this.category = item.category;
        available = false;
    }

    /**
     * Creates an item
     *
     * @param name the name of this item
     * @param price the price of this item
     * @param category the category of this item
     */
    public Item(String name, float price, Category category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public void discount() {
        price *= (100f - DISCOUNT_PERCENTAGE) / 100f;
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
     * gets the ID of the item
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * sets the ID of the item
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
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
     * sets the price of the product
     *
     * @param price
     */
    public void setPrice(float price) {
        this.price = price;
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
     * sets the name of the item
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * Sets availabillity for this item
     * 
     * @param available 
     */
    public void setAvailable(boolean available) {
        this.available = available;
    }

    /**
     * Gets availabillity of this item
     * 
     * @return available The availability of this item
     */
    public boolean isAvailable() {
        return available;
    }
}
