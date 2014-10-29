package supermarket.StaffTypes;

import supermarket.Storage;

public class Unloader extends Staff {

    Storage workplace;

    /**
     * Constructor for the Unloader staff member
     *
     * @param name Specify the name of this person
     * @param workplace Specify the workspace of this person
     */
    public Unloader(String name, Storage workplace) {

        super(name);
        this.workplace = workplace;
    }

    /**
     * Gets the CLASS of the current location. For example "Aisle" or "Storage"
     *
     * @return the class of the current location
     */
    public Storage getWorkPlace() {
        return workplace;
    }
}
