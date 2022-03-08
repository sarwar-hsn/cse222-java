package systemEmployee;

import systemuser.UserType;

import java.util.*;

import systemcustomer.Customer;
import systemtools.*;


/**
 * class that holds Branch Employee info and it's functionality
 * @author sarwar
 *
 */
public class BranchEmployee extends Employee implements ProductQuery {
	
	/*
	 * related variables
	 */
    private Branch assignedBranch;
    public static List<SellsHistory> sells = new ArrayList<>();
   
   /**
    * constructor of the branch Employee 
    * @param name takes string value
    * @param surname takes string value
    * @param userType takes a UserType enumeration value
    * @param branch takes a branch 
    */
    public BranchEmployee(String name, String surname, UserType userType, Branch branch) {
	super(name, surname, userType);
	this.assignedBranch = branch;
    }
    
    

    @Override
    public String toString() {
	String catenate = "";
	catenate += "\n Employee Id : " + this.id + "\n";
	catenate += " Name : " + this.name + " " + this.surname + "\n";
	catenate += " UserType : " + this.userType + "\n";
	catenate += " branchId : " + this.assignedBranch.getId() + "\n";
	catenate += " branchName : " + this.assignedBranch.getName()+"\n";
	return catenate;
    }
    
    
    /**
     * function to get branch employee name
     * @return return a string value
     */
    public String getName() {
    	return this.name+" "+this.surname;
    }
    /**
     * function to get branch employee id
     * @return returns a integer value
     */
    public int getId() {
    	return this.id;
    }
    
    /**
     * function get assigned branch
     * @return returns a Branch
     */
    public Branch getBranch() {
    	return this.assignedBranch;
    }



    /**
     * function to  print list of all products
     */
	@Override
	public boolean listProducts() {
		System.out.println("\t\t\t\t---List of Products In Your Branch---");
		if(this.assignedBranch.getProducts().size()==0) {
			System.out.println("\nNo Prducts !!!");
			return false;
		}
		else
		for(int i = 0 ; i < this.assignedBranch.getProducts().size();i++) {
			System.out.println(this.assignedBranch.getProducts().get(i));
		}
		System.out.println();
		return true;
	}
	
	/**
	 * function to print a customer's shopping history
	 * @param customerId takes a integer value
	 */
	public void customerHistory(int customerId) {
		for(int i = 0 ; i < BranchEmployee.sells.size(); i++) {
			if(BranchEmployee.sells.get(i).getCustomer().customerUniqueId() == customerId) {
				System.out.println("\n\t\t---Customer History---");
				//if the uniqueId in sellshistory matches with provided unique id then show info
				System.out.println("customer id : "+customerId +"\t name : "+ BranchEmployee.sells.get(i).getCustomer().getCustomerName());
				System.out.println();
				if(BranchEmployee.sells.get(i).getCustomerProducts().size()==0) {
					System.out.println("\nCustomer has no product !!!\n");
					return;
				}
				for(int j = 0 ; j < BranchEmployee.sells.get(i).getCustomerProducts().size(); j++) {
					System.out.println(BranchEmployee.sells.get(i).getCustomerProducts().get(j));
				}
				System.out.println();
			}else {
				System.out.println("\nNo Customer Found with this Id !!!\n");
			}
		}
	}
	
	/**
	 * function to send message to manager
	 * @param msg takes a Message value
	 */
	public void informManager(Message msg) {
		messages.add(msg);
	}
	
	/**
	 * function to add product (increase product quantity)
	 * @param productId takes a integer value
	 * @param amountToAdd takes a integer value
	 * @return
	 */
	public boolean addProduct(int productId,int amountToAdd) {
		if(amountToAdd <= 0) return false;
		for(int i = 0 ; i < this.assignedBranch.getProducts().size(); i++) {
			if(this.assignedBranch.getProducts().get(i).getProductId()==productId) {
				this.assignedBranch.getProducts().get(i).increaseAmount(amountToAdd);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * function to get a product index by it's id
	 * @param productId takes int value
	 * @return returns index in success else -1
	 */
	private int getProductIndexbyId(int productId) {
		for(int i = 0 ; i < this.assignedBranch.getProducts().size(); i++) {
			if(this.assignedBranch.getProducts().get(i).getProductId()==productId) {
				return i;
			}
		}
		return -1;
	}

	
	/**
	 * function a get a Customer by it's id
	 * @param id takes a int value
	 * @return returns customer in success else null
	 */
	public Customer getCustomerById(int id) {
		for(int i = 0 ; i < BranchEmployee.sells.size(); i++) {
			if(BranchEmployee.sells.get(i).getCustomer().customerUniqueId() == id) {
				return BranchEmployee.sells.get(i).getCustomer();
			}
		}
		return null;
	}
	
	/**
	 * function to add a customer to the system
	 * @param customer takes a customer 
	 */
	public void addCustomer(Customer customer) {
		BranchEmployee.sells.add(new SellsHistory(customer));
	}
	
	
	/**
	 * function to sell a product
	 * @param productId takes a integer value
	 * @param amount takes a integer value
	 * @param customer takes a customer
	 * @return returns true in success else false
	 */
	public boolean sellProduct(int productId,int amount,Customer customer) {
		int productIndex = getProductIndexbyId(productId);
		if(productIndex!=-1) {
			if(this.assignedBranch.getProducts().get(productIndex).getAmount() < amount) {
				System.out.println("\nNot enough product in stock !!!\n");
				return false;
			}else {
				Product product = this.assignedBranch.getProducts().get(productIndex);
				//decrease stock amount
				product.decreaseAmount(amount);
				//add to sells history 
				//if customer is already present in sells history
				for(int i = 0 ; i < BranchEmployee.sells.size();i++) {
					if(BranchEmployee.sells.get(i).getCustomer().customerUniqueId() == customer.customerUniqueId()) {
						BranchEmployee.sells.get(i).addToSellsHistory(product, amount);
						return true;
					}
				}
				//if customer is not present in the system
				SellsHistory temp = new SellsHistory(customer);
				temp.addToSellsHistory(product, amount);
				BranchEmployee.sells.add(temp);
			}
			return true;
		}
		System.out.println("\nInvalid Product Id !!!\n");
		return false;
	}
	
	
	
   
    
    

}
