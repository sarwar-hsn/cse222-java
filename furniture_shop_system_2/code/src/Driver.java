
import systemEmployee.Admin;
import systemrunner.AutomationSystem;

public class Driver {
    public static void main(String[] args) {  	
    	Admin.initBranch();
    	new AutomationSystem().run();
 	
    }

}

