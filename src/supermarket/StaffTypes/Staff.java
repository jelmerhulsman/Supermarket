package supermarket.StaffTypes;

public class Staff {

    private String name;

    private Staff(String name) {
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
