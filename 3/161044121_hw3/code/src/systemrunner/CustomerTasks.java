package systemrunner;

import java.util.Scanner;

import systemcustomer.Customer;
import java.util.*;
import systemtools.*;


/**
 * 
 * class to handle the task of a customer
 *
 */
public class CustomerTasks {
	
	/**
	 * function to run the tasks of a customer
	 * @param input takes a Scanner object
	 * @param customer takes a customer object
	 */
	public void run(Scanner input,Customer customer) {
		
		while(true) {
			try {
				options(customer);
				int choice = input.nextInt();
				switch (choice) {
				case 0:
					System.out.println("\nLogging out ...");
					return; 
				case 1:
					Scanner in= new Scanner(System.in);
					System.out.print("Please Enter a Product Name : ");
					String productName = in.nextLine();
					List<Product> temp = customer.search(productName);
					if(temp.size()!=0) {
						System.out.println("\n\t\t\t\t---Products by Search Query---");
						for(int i = 0 ; i < temp.size();i++) {
							System.out.println(temp.get(i));
						}
						System.out.println();
					}else {
						System.out.println("\nNo Product Found !!!\n");
					}
					break;
				case 2:
					customer.listProducts();
					break;
				case 3:
					System.out.print("Please Enter a Product id : ");
					int productId = input.nextInt();
					Branch tempBranch = customer.getBranchByProductId(productId);
					if(tempBranch!=null) {
						System.out.println("\n\t\t---Product Available in following branch---");
						System.out.println("Branch Id : "+tempBranch.getId()+"\tBranch Name : "+tempBranch.getName());
						System.out.println();
					}else {
						System.out.println("\nProduct isn't available at this moment");
					}
					break;
				case 4:
					
					if(customer.getCustomerAddress()!=null && customer.getCustomerPhoneNum()!=0) {
						System.out.println("\n\t\t---Your current billing address and phone number---\n");
						System.out.println("Address : "+customer.getCustomerAddress()+" phone number : "+customer.getCustomerPhoneNum());
						System.out.println();
					}
					
					Scanner obj = new Scanner(System.in);
					if(customer.getCustomerAddress() == null) {
						System.out.print("Enter your current Address : ");
						String address = obj.nextLine();
						customer.setAddress(address);
					}
					if(customer.getCustomerPhoneNum() == 0) {
						System.out.print("Enter your mobile number : ");
						int number = obj.nextInt();
						customer.setPhoneNumber(number);
					}
					try {
						System.out.print("Enter Product Id : ");
						productId = obj.nextInt();
						System.out.print("Enter Amount : ");
						int amount = obj.nextInt();
						if(customer.buyOnline(productId, amount)) {
							System.out.println("\nProduct Purchased Successfully !!!\n");
						}else {
							System.out.println("\nCouldn't buy Product !!!\n");
						}
					} catch (Exception e) {
						System.out.println(e);
					}
					
					break;
				case 5:
					customer.history();
					break;
				case 6:
					if(customer.getCustomerAddress()!=null && customer.getCustomerPhoneNum()!=0) {
						System.out.println("\n\t\t---Your current billing address and phone number---\n");
						System.out.println("Address : "+customer.getCustomerAddress()+" phone number : "+customer.getCustomerPhoneNum());
						System.out.println();
					}
					Scanner inp = new Scanner(System.in);
					System.out.print("Enter your current address : ");
					String address = inp.nextLine();
					System.out.print("Enter your phone number : ");
					int number = inp.nextInt();
					customer.updateInfo(address, number);
					break;
				default:
					System.out.println("\nInvalid input !!!\n");
					break;
				}
				
			} catch (Exception e) {
				String bad_input = input.next();
				System.out.println(bad_input + " is not a valid input\n");
				continue;
				
			}
		}
		
	}
	
	/**
	 * function to display the options of a customer
	 * @param customer takes the instance of a customer
	 */
	private void options(Customer customer) {
		System.out.println("\t\t---Welcome "+customer.getCustomerName()+"---");
		System.out.println("1. Search Product");
		System.out.println("2. List of Products");
		System.out.println("3. Find a Product's Branch");
		System.out.println("4. Shop Online");
		System.out.println("5. Shopping History");
		System.out.println("6. Update billing address");
		System.out.println("0. Logout\n");
		System.out.print("Enter your choice : ");
	}

}


