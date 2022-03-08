package systemEmployee;



import generateId.IdGenerator;
import list.KWLinkedList;
import systemtools.Branch;
import systemtools.Product;
import systemuser.UserType;

/**
 * class extending employee class
 * @author sarwar
 *
 */
public class Admin extends Employee {

	
	/**
	 * all the administrative user have access to all the branches so the branch is of type static
	 */
    public static KWLinkedList<Branch> branches = new KWLinkedList<Branch>();
    
    /**
     * constructor of Admin class
     * @param name takes a string value is the name of administrator
     * @param surname takes a string value is the surname of administrator
     */
    public Admin(String name, String surname) {
	super(name, surname, UserType.admin);
	this.id = new IdGenerator().getId();
    }
    
    
    /**
     * 
     * @return returns a integer value is the id of the administrator
     */
    public int getId() {
    	return this.id;
    }
    
    /**
     * initiating branches with some values
     */
    public static void initBranch() {
    	branches.add(new Branch("Birampur"));
    	branches.add(new Branch("Dinajpur"));
    	branches.add(new Branch("Rangpur"));
    	branches.add(new Branch("Dhaka"));
    	
    	branches.get(0).addProduct(new Product("office chair", "model 1", 1000, 1, "red"));
    	branches.get(0).addProduct(new Product("office chair", "model 1", 1000, 1, "green"));
    	branches.get(0).addProduct(new Product("office desk", "model 1", 1000, 1, "red"));
    	branches.get(0).addProduct(new Product("office desk", "model 1", 1000, 1, "green"));
    	branches.get(0).addProduct(new Product("meeting table", "model 1", 1000, 1, "red"));
    	branches.get(0).addProduct(new Product("meeting table", "model 1", 1000, 1, "green"));
    	branches.get(0).addProduct(new Product("book case", "model 1", 1000, 1, "__"));
    	branches.get(0).addProduct(new Product("book case", "model 1", 1000, 1,"__"));
    	
    	branches.get(1).addProduct(new Product("office chair", "model 1", 1000, 1, "red"));
    	branches.get(1).addProduct(new Product("office chair", "model 1", 1000, 1, "green"));
    	branches.get(1).addProduct(new Product("office desk", "model 1", 1000, 1, "red"));
    	branches.get(1).addProduct(new Product("office desk", "model 1", 1000, 1, "green"));
    	branches.get(1).addProduct(new Product("meeting table", "model 1", 1000, 1, "red"));
    	branches.get(1).addProduct(new Product("meeting table", "model 1", 1000, 1, "green"));
    	branches.get(1).addProduct(new Product("book case", "model 1", 1000, 1, "__"));
    	branches.get(1).addProduct(new Product("book case", "model 1", 1000, 1,"__"));
    	
    	branches.get(2).addProduct(new Product("office chair", "model 1", 1000, 1, "red"));
    	branches.get(2).addProduct(new Product("office chair", "model 1", 1000, 1, "green"));
    	branches.get(2).addProduct(new Product("office desk", "model 1", 1000, 1, "red"));
    	branches.get(2).addProduct(new Product("office desk", "model 1", 1000, 1, "green"));
    	branches.get(2).addProduct(new Product("meeting table", "model 1", 1000, 1, "red"));
    	branches.get(2).addProduct(new Product("meeting table", "model 1", 1000, 1, "green"));
    	branches.get(2).addProduct(new Product("book case", "model 1", 1000, 1, "__"));
    	branches.get(2).addProduct(new Product("book case", "model 1", 1000, 1,"__"));
    	
    	branches.get(3).addProduct(new Product("office chair", "model 1", 1000, 1, "red"));
    	branches.get(3).addProduct(new Product("office chair", "model 1", 1000, 1, "green"));
    	branches.get(3).addProduct(new Product("office desk", "model 1", 1000, 1, "red"));
    	branches.get(3).addProduct(new Product("office desk", "model 1", 1000, 1, "green"));
    	branches.get(3).addProduct(new Product("meeting table", "model 1", 1000, 1, "red"));
    	branches.get(3).addProduct(new Product("meeting table", "model 1", 1000, 1, "green"));
    	branches.get(3).addProduct(new Product("book case", "model 1", 1000, 1, "__"));
    	branches.get(3).addProduct(new Product("book case", "model 1", 1000, 1,"__"));
    }
    
   
   /**
    * fucntion to get a branch employee 
    * @param branchId is integer type
    * @param employeeId is integer type
    * @return returns a Branch employee if found else returns null
    */
    public BranchEmployee getBranchEmployee(int branchId,int employeeId) {
    	int branchIndex = getBranchIndexById(branchId);
    	if(branchIndex!=-1) {
    		int employeeIndex = getBranchEmployeeIndexById(branchIndex, employeeId);
    		if(employeeIndex!=-1)
    		return branches.get(branchIndex).getEmployees().get(employeeIndex);
    		else return null;
    	}
    	else return null;
    }
    
    
    
    
   /**
    * 
    * @return returns a string value is the name of the branch
    */
   public String getName() {
	   return this.name;
   }
    
   /**
    * function to read the messages sent by branch Employee
    */
    public void readMsg() {    	
    	for(int i = 0 ; i < Employee.messages.size();i++) {
			System.out.println(i+1+" : "+ Employee.messages.get(i));
		}	
    }
   
    
    /**
     * function to get branch index of a branch by it's name
     * @param branchName takes a string value
     * @return returns branch index if found else -1
     */
    private int getBranchIndexByName(String branchName) {
	for (int i = 0; i < branches.size(); i++) {
	    if (branches.get(i).getName().equals(branchName))
		return i;
	}
	return -1;
    }
    
    
    /**
     * function to add a branch to system
     * @param branchName takes a branch name
     */
    public void addBranch(String branchName) {
 
	if (branches.size() == 0) {
	    branches.add(new Branch(branchName));
	} else {
	    for (int i = 0; i < branches.size(); i++) {
	    	if (getBranchIndexByName(branchName) != -1) {
		    	System.out.println("\nBranch already exists !!! \n");
		    	return;
			}
	    }
	    branches.add(new Branch(branchName));
		}
    }

    /**
     * function to delete a branch
     * @param branchName is a string value
     * @return return the deleted Branch in success else returns null
     */
    public Branch deleteBranch(String branchName) {
	int searchBranchIndex = getBranchIndexByName(branchName);
	if (searchBranchIndex != -1) {
	    return branches.remove(searchBranchIndex);
	}
	return null;
    }

    
    /**
     * function to get branch index by id
     * @param branchId takes a string value
     * @return returns index of the branch in success else -1
     */
    private int getBranchIndexById(int branchId) {
	for (int i = 0; i < branches.size(); i++) {
	    if (branches.get(i).getId() == branchId) {
		return i;
	    }
	}
	return -1;
    }

    /**
     * function to get  branch name by it's id
     * @param id takes a int value is the branch id
     * @return returns index in success else -1
     */
    private String getBranchNamebyId(int id) {
	for (int i = 0; i < branches.size(); i++) {
	    if (branches.get(i).getId() == id)
		return branches.get(i).getName();
	}
	return null;
    }
    
    /**
     * add a branch employee to a branch
     * @param name takes string value
     * @param surName takes a string value
     * @param branchId takes a integer value
     * @return returns true in success else false
     */
    public boolean addBranchEmployee(String name, String surName, int branchId) {

	String branchName = getBranchNamebyId(branchId);

	if (branchName != null) {
	    int branchIndex = getBranchIndexById(branchId);
	    BranchEmployee employee = new BranchEmployee(name, surName, UserType.branchEmployee, branches.get(branchIndex));
	    branches.get(branchIndex).getEmployees()
		    .add(employee);
	    System.out.println("\nEmployee Id : "+employee.getId()+" (need this id to login later)\n");
	    return true;
	} else {
	    return false;
	}

    }
    
    /**
     * add branch employee to branch
     * @param name takes a string value
     * @param surName takes a string value
     * @param branchName takes a string value
     * @return returns true in success else false
     */
    public boolean addBranchEmployee(String name, String surName, String branchName) {
	int branchIndex = getBranchIndexByName(branchName);
	if (branchIndex != -1) {
		BranchEmployee employee = new BranchEmployee(name, surName, UserType.branchEmployee, branches.get(branchIndex));
	    branches.get(branchIndex).getEmployees()
		    .add(employee);
	    System.out.println("\nEmployee Id : "+employee.getId()+" (need this id to login later)\n");
	    return true;
	} else {
	    return false;
	}
    }
    
    /**
     * function to get branch employee index
     * @param branchIndex takes a integer value is branch id
     * @param id takes a employee id
     * @return  returns index in success else -1
     */
    private int getBranchEmployeeIndexById(int branchIndex, int id) {
    	for (int i = 0; i < branches.get(branchIndex).getEmployees().size(); i++) {
    	    if (branches.get(branchIndex).getEmployees().get(i).getId() == id)
    		return i;
    	}
    	return -1;
        }

    /**
     * function to delete branch employee 
     * @param branchId takes a integer value
     * @param employeeId takes a integer value
     * @return returns deleted Branch Employee in success else null
     */
    public BranchEmployee deleteBranchEmployee(int branchId, int employeeId) {
	
	    int branchIndex = getBranchIndexById(branchId);
	    if(branchIndex!=-1) {
	    	int employeeIndex = getBranchEmployeeIndexById(branchIndex, employeeId);
		    if (employeeIndex != -1) {
		    	
			return branches.get(branchIndex).getEmployees().remove(employeeIndex);
		    } else {
			System.out.println("\n Invalid Employee Id !!! \n");
		    }
	    }else {
	    	System.out.println("\n Invalid branchId !!! \n");
	    }
	    
	

	return null;
    }

    /**
     * prints all the branches
     */

    public void seeBranches() {
    System.out.println("\t\t---List of Branches---\n");
    for(int i = 0 ; i < branches.size();i++) {
    	System.out.println("Branch Id : " + branches.get(i).getId() + "\t  Branch Name :  " + branches.get(i).getName());
    }
	System.out.println();
    }

    /**
     * function to print the list of branch employee
     * @return return true if there is branch employee else false
     */
    public boolean seeBranchEmployees() {
    int count = 0 ; 
	for (int i = 0; i < branches.size(); i++) {
	    for (BranchEmployee employee : branches.get(i).getEmployees()) {
	    	count++;
		System.out.println(employee);
	    }

	}
	if(count == 0) {
		System.out.println("\nNo Branch employees!!!. Try adding them first\n");
		return false;
	} 
	else System.out.println();
	return true;
    }

    

}
