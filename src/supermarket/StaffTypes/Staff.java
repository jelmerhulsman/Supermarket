package supermarket.StaffTypes;

import java.util.List;

public class Staff {
    private String name;
    private List<Staff> functions;
    private Staff curFunctions;
    
    public Staff(String name, List<Staff> functions){
        this.name = name;
        this.functions = functions;
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
