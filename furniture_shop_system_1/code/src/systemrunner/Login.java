package systemrunner;

import java.util.Scanner;

import systemEmployee.Admin;
import systemEmployee.BranchEmployee;
import systemcustomer.Customer;
import systemuser.User;


/**
 * 
 * class to handle login tasks
 *
 */
public class Login {
	
	//Scanner input = new Scanner(System.in);
	int choice = 5,id;
	
	
	/**
	 * function to handle logins of system user
	 * @param input takes scanner object
	 * @return return instance of a user
	 */
	public User run(Scanner input) {
		
			while (choice!=0) {
				try {
					LoginScreen();
					choice=input.nextInt();
					switch (choice) {
					case 0:
						choice=0;
						break;
					case 1:
						Admin admin = adminLogin(input);
						if(admin!=null) return admin;
						else {
							System.out.println("\nInvalid Credential !!!\n");
							break;
						} 
						
					case 2:
						BranchEmployee employee =  branchEmployeeLogin(input);
						if(employee!=null) return employee;
						else {
							System.out.println("\nInvalid Credential !!!\n");
							break;
						} 
					case 3:
						Customer customer = customerLogin(input);
						if(customer!=null) return customer;
						else {
							System.out.println("\nInvalid Credential !!!\n");
							break;
						} 
					default:
						System.out.println("\nInvalid Choice !!!\n");
						break;
					}
				} catch (Exception e) {
					String badInput = input.next();
					System.out.println("\n"+badInput + " is not valid input !!!\n");
					continue;
				}
				
			}
		
		return null;
	}
	
	
	/**
	 * function to display the options of login task
	 */
	private  void LoginScreen() {
		System.out.println("\t\t---Login---");
		System.out.println("1. Login as Admin");
		System.out.println("2. Login as Branch Employee");
		System.out.println("3. Login as Customer");
		System.out.println("0. Exit\n");
		System.out.print("Enter your choice : ");
	}
	
	
	/**
	 * function to handle the login of administrator user
	 * @param input takes a scanner object
	 * @return returns instance of a Admin
	 */
	private  Admin adminLogin(Scanner input) {
			System.out.println("\t\t---Admin Login---");
			System.out.print("Please Enter Your Secret Login Code : ");
			try {
				int id = input.nextInt();
				Admin admin =  new AutomationSystem().getAdminById(id);
				return admin; 
			} catch (Exception e) {
				String badInput = input.next();
				System.out.println("\n"+badInput + " is not valid input !!!\n");
				return null;
			}
		}

	/**
	 * function to validate Branch Employee
	 * @param input takes a scanner object
	 * @return return instance of a BranchEmployee
	 */
	private BranchEmployee branchEmployeeLogin(Scanner input) {
		System.out.println("\t\t---Branch Employee Login---");
		System.out.print("Please Enter Your Employee Id : ");
		try {
			int id = input.nextInt();
			return getBranchEmployeeById(id);
			
		} catch (Exception e) {
			String badInput = input.next();
			System.out.println("\n"+badInput + " is not valid input !!!\n");
			return null;
		}
	}
	
	
	/**
	 * function to validate the login of a customer
	 * @param input takes a scanner object
	 * @return returns instance of a customer
	 */
	private Customer customerLogin(Scanner input) {
		System.out.println("\t\t---Customer Login---");
		System.out.print("Please Enter Your Unique Id : ");
		try {
			int id = input.nextInt();
			Customer customer =  getCustomerByUniqueId(id);
			return customer;
		} catch (Exception e) {
			String badInput = input.next();
			System.out.println("\n"+badInput + " is not valid input !!!\n");
			return null;
		}
	}
	
	/**
	 * function to get BranchEmployee object by it's id
	 * @param id takes a integer value 
	 * @return return BranchEmployee in success else null
	 */
	private BranchEmployee getBranchEmployeeById(int id) {
		for(int i = 0 ; i < Admin.branches.size(); i++) {
			for(int j = 0 ; j < Admin.branches.get(i).getEmployees().size(); j++) {
				if(Admin.branches.get(i).getEmployees().get(j).getId() == id){
					
					BranchEmployee employee =  Admin.branches.get(i).getEmployees().get(j);
					return employee;
					}
				
			}
		}
		return null;
	}
	
	/**
	 * function to get a customer by it's id
	 * @param id takes a integer value
	 * @return returns a customer object in success else null
	 */
	private Customer getCustomerByUniqueId(int id) {
		for(int i = 0 ; i < BranchEmployee.sells.size(); i++) {
			if(BranchEmployee.sells.get(i).getCustomer().customerUniqueId() == id) {
				return BranchEmployee.sells.get(i).getCustomer();
			}
		}
		return null;
	}
		
		
	}
	

