package supermarket;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="items")
public class Item {
    
    private int id;
    

    public enum Category {

        BEER, LIQUOR, WINE, CAFFEINE, SNACK, SODA, DAIRY, SPICY, NONFOOD, FROZEN, PRESERVATION, BREAD, BREAKFAST, SPREAD, VEGTABLES, FRUIT, FOREIGN, READY_TO_EAT
    }

    public enum Status {

        LOADED, IN_STORAGE, ON_CUSTOMER, ON_STAFF, IN_TRUCK
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
        this.status = Status.IN_TRUCK;
    }

    /**
     * Gets the Status of the Item
     *
     * @return wether the item is loaded, at a customer, purchased and whatever
     */
    @Enumerated(EnumType.ORDINAL)
    public Status getStatus() {
        return status;
    }

    /**
     * Gets the current category of this item
     *
     * @return The current category
     */
    @Enumerated(EnumType.ORDINAL)
    public Category getCategory() {
        return category;
    }

    /**
     * sets the primary thingy
     * @param primary 
     */
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
    
    /**
     * Returns the primary status of this item
     *
     * @return The primary status of this item
     */
    @Column(name = "primary")
    public boolean isPrimary() {
        return primary;
    }

    /**
     * gets the ID of the item
     * @return 
     */
    @Id @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }
    
    /**
     * sets the ID of the item
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
    @Column(name="price")
    public float getPrice() {
        return price;
    }
    
    /**
     * sets the price of the product
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
    @Column(name="name")
    public String getName() {
        return name;
    }

    /**
     * sets the name of the item
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
     * Sets the status of this item to something else
     *
     * @param status wether the item is loaded, at a customer, purchased and
     * whatever
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
