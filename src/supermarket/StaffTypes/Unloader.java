package supermarket.StaffTypes;

import supermarket.Storage;

public class Unloader extends Staff {

    /**
     * Constructor for the Unloader staff member
     * @param name Specify the name of this person
     * @param storage Specify the workspace of this person
     */
    public Unloader(String name, Storage storage)
    {
       
        super(name);
        this.objectType = storage;
    }
    
    
    
}
