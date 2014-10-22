package supermarket.StaffTypes;

import java.util.List;

public class Staff {
    private String name;
    private List<Staff> functions;
    private Staff curFunction;
    
    public Staff(String name){
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

    /**
     * Assign functions for this staff member
     * 
     * @param functions 
     */
    public void setFunctions(List<Staff> functions) {
        this.functions = functions;
    }  

    /**
     * Return the assigned functions of this staff member
     * 
     * @return functions
     */
    public List<Staff> getFunctions() {
        return functions;
    }
    
    /**
     * Sets the current function for this staff member
     * 
     * @param curFunction 
     */
    public void setCurFunction(Staff curFunction) {
        this.curFunction = curFunction;
    }

    /**
     * Gets the current function of this staff member
     * @return curFunction
     */
    public Staff getCurFunction() {
        return curFunction;
    }
}
