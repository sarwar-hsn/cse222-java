package systemrunner;

import java.util.*;
import systemEmployee.Admin;
import systemEmployee.BranchEmployee;
import systemcustomer.Customer;
import systemuser.User;


/**
 * 
 * Class to run the system automatically
 *
 */
public class AutomationSystem {
	
	
	/**
	 * list of administrator
	 */
	public static List<Admin> administrators= new ArrayList<Admin>();
	
	
	/**
	 * constructor
	 */
	public AutomationSystem() {
	}
	
	
	/**
	 * function to authenticate administrator login
	 * @param id takes a integer value
	 * @return returns true in success else false
	 */
	public boolean adminLogin(int id) {
		for(int i = 0 ; i < administrators.size();i++) {
			if(administrators.get(i).getId() == id)return true;
		}
		return false;
	}
	
	/**
	 * function to get a admin by id
	 * @param id takes a integer value
	 * @return returns instance of a admin
	 */
	public Admin getAdminById(int id) {
		for(int i = 0 ; i < administrators.size();i++) {
			if(administrators.get(i).getId() == id) return administrators.get(i);
		}
		return null;
	}
	
	/**
	 * function to run the automation system
	 */
	public void run() {
		
		Scanner input = new Scanner(System.in);
		
		while(true) {	
				try {
				initialScreen();
				int	choice = input.nextInt();
				switch (choice) {
				case 1:
						loginSession(input);
					break;
				case 2:
					try {
						@SuppressWarnings("resource")
						Scanner obj = new Scanner(System.in);
						System.out.println("\t\t---Register as Admin--");
						System.out.print("Enter your name : ");
						String name = obj.nextLine();
						System.out.print("Enter your surname : ");
						String surname = obj.nextLine();
						Admin temp = new Admin(name, surname);
						administrators.add(temp);
						System.out.println("your admintrative login code is "+"'"+temp.getId()+"'");
					} catch (Exception e) {
						String badInput = input.next();
						System.out.println("\n"+badInput + " is not a valid input !!!\n");
						continue;
					}
					break;
				case 0:
					System.out.println("BYE ...");
					input.close();
					return;
				default:
					System.out.println("invalid input !!!!");
					break;
				}
				} catch (Exception e) {
					String badInput = input.next();
					System.out.println("\n"+badInput + " is not a valid input !!!\n");
					continue;
				}
				
		
		}
	}
	
	/**
	 * prints the initial screen options
	 */
	public void initialScreen() {
		System.out.println("\t\t---Welcome---");
		System.out.println("1. Login");
		System.out.println("2. Register as Admin");
		System.out.println("0. Exit");
		System.out.print("Enter your choice : ");
	}
	
	/**
	 * function to perform task of user's respective task
	 * @param input takes a scanner value as parameter
	 */
	public void loginSession(Scanner input) {
		User user = new Login().run(input);
		while(user!=null) {
			if (user instanceof Admin) {
				new AdminTasks().run(input,(Admin) user);
			}else if(user instanceof BranchEmployee) {
				new BranchEmployeeTask().run(input,(BranchEmployee) user);
			}else if(user instanceof Customer) {
				new CustomerTasks().run(input,(Customer) user);
			}
			user = new Login().run(input);
		}
		
		
	}
	
	

}
