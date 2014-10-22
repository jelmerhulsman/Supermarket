package supermarket.StaffTypes;

import supermarket.Storage;

public class Unloader extends Staff {

     private Storage storage;
    public Unloader(String name)
    {
       
        super(name);
        this.objectType = storage;
    }
    
    
    
}
