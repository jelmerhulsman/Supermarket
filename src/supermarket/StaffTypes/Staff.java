package supermarket.StaffTypes;

public class Staff {

    protected String name;

    public Staff(String name) {
        this.name = name;
    }

    /**
     * Gets the name of this Staff member
     *
     * @return the name of this staff member
     */
    public String getName() {
        return name;
    }
}
