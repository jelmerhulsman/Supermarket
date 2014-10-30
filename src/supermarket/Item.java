package supermarket;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    public enum Category {

        BEER, LIQUOR, WINE, CAFFEINE, SNACK, SODA, DAIRY, SPICES, NONFOOD, FROZEN, PRESERVATION, BREAD, BREAKFAST, SPREAD, VEGTABLES, FRUIT, FOREIGN, READY_TO_EAT
    }

    public enum Status {

        LOADED, IN_STORAGE, ON_CUSTOMER, ON_STAFF, IN_TRUCK, VIRTUAL
    }
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private float price;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    public Item() {
        this.status = Status.IN_TRUCK;
    }
    
    public Item(Item item) {
        this.name = item.name;
        this.price = item.price;
        this.status = Status.IN_TRUCK;
        this.category = item.category;
    }

    public Item(String name, float price, Category category) {
        this.name = name;
        this.price = price;
        this.status = Status.VIRTUAL;
        this.category = category;
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
     * Sets the status of this item to something else
     *
     * @param status wether the item is loaded, at a customer, purchased and
     * whatever
     */
    public void setStatus(Status status) {
        this.status = status;
    }
}
