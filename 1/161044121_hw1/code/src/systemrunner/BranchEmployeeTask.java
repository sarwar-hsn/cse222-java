package systemrunner;

import java.util.Scanner;

import systemEmployee.BranchEmployee;
import systemcustomer.Customer;
import systemtools.Message;

/**
 * 
 * class to handle Branch Employee tasks
 *
 */
public class BranchEmployeeTask {
	
	
	/**
	 * function to run the the tasks of a BranchEmployee
	 * @param input takes a Scanner object
	 * @param employee takes a BranchEmployee object
	 */
	public void run(Scanner input,BranchEmployee employee) {
		while(true) {
			try {
				options(employee);
				int choice = input.nextInt();
				switch (choice) {
				case 0:
					System.out.println("\nLogging out ...\n");
					return;
				case 1:
					employee.listProducts();
					break;
				case 2:
					Scanner obj = new Scanner(System.in);
					System.out.print("Please name the product you need : ");
					String productName = obj.nextLine();
					System.out.println("Please enter the required amount : ");
					int amount = obj.nextInt();
					System.out.print("Please enter today's day (0 - 30) : ");
					int day = obj.nextInt();
					System.out.print("Please enter today's month (0 - 12) : ");
					int month = obj.nextInt();
					System.out.print("Please enter year (e.g:2021) : ");
					int year = obj.nextInt();
					employee.informManager(new Message(productName, amount, day, month, year, employee));
					break;
				case 3:
					employee.listProducts();
					System.out.print("Please enter the product id : ");
					int productIndex = input.nextInt();
					System.out.print("Enter amount you want to add : ");
					int amountToAdd = input.nextInt();
					if(employee.addProduct(productIndex, amountToAdd))System.out.println("\nProducts added\n");
					else System.out.println("\nFailed to add Product !!!\n");
					break;
				case 4:
					System.out.print("Please Enter Customer Name : ");
					String name = input.next();
					System.out.print("Please Enter Customer Surname : ");
					String surName = input.next();
					Customer customer = new Customer(name, surName);
					employee.listProducts();
					System.out.print("Please enter the product id : ");
					productIndex = input.nextInt();
					System.out.print("Enter amount : ");
					amountToAdd = input.nextInt();
					if(employee.sellProduct(productIndex, amountToAdd, customer)) {
						System.out.println("\nProduct Sold !!!");
						System.out.println("\nCustomer's Unique Id : "+customer.customerUniqueId()+". Customer will use this ID to login in the system\n");
					}
					break;
				case 5:
					System.out.print("Please Enter Customer's Unique Id : ");
					int uniqueId = input.nextInt();
					customer = employee.getCustomerById(uniqueId);
					if(customer!=null) {
						employee.listProducts();
						System.out.print("Please enter the product id : ");
						productIndex = input.nextInt();
						System.out.print("Enter amount : ");
						amountToAdd = input.nextInt();
						if(employee.sellProduct(productIndex, amountToAdd, customer)) {
							System.out.println("\nProduct Sold !!!");
						}
					}else {
						System.out.println("\nInvalid Customer Id\n");
					}
					break;
				case 6:
					System.out.print("Please Enter Customer's Unique Id : ");
					int customerId = input.nextInt();
					employee.customerHistory(customerId);
					break;
				default:
					System.out.println("\nInvalid input !!!\n");
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
	 * function that displays the options of a branch employee
	 * @param employee takes instance of a branch employee
	 */
	private void options(BranchEmployee employee) {
		System.out.println("\t\t---Welcome "+employee.getName()+"---");
		System.out.println("1. See Products");
		System.out.println("2. Meassage to Manager");
		System.out.println("3. Add Product");
		System.out.println("4. Sell Products (new customer)");
		System.out.println("5. Sell Product (existing customer)");
		System.out.println("6. Sales History of a customer");
		System.out.println("0. Logout\n");
		System.out.print("Enter your choice : ");
	}

}
