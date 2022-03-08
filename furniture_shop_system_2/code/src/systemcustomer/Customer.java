package systemcustomer;

import generateId.IdGenerator;
import list.HybridList;
import systemEmployee.*;
import systemuser.*;
import systemtools.*;
import java.util.*;


public class Customer extends User implements ProductQuery{
	
	private String address = null;
	private int phoneNum = 0;
	private HybridList<Product> cart;

	/**
	 * this is the constructor for the customer
	 * @param name is type of string 
	 * @param surname is type of string 
	 */
	public Customer(String name, String surname) {
		super(name, surname, UserType.customer);
		this.id = new IdGenerator().getId();
		cart = new HybridList<Product>();
	}
	
	/**
	 * this function gets the List of purchased product
	 * @return return a List of Product
	 */
	public List<Product> getCart(){
		return this.cart;
	}
	
	/**
	 * function to look for a particular product
	 * @param productName takes a string value
	 * @return returns a list of product
	 */
	public List<Product> search(String productName){
		ArrayList<Product> temp = new ArrayList<Product>();
		for(int i = 0 ; i < Admin.branches.size(); i++) {
			for(int j = 0 ; j <Admin.branches.get(i).getProducts().size();j++) {
				if(Admin.branches.get(i).getProducts().get(j).getProductName().equals(productName)) {
					temp.add(Admin.branches.get(i).getProducts().get(j));
				}	
			}
		}
		return temp;
	}
	
	
	/**
	 * function to get customer's unique id
	 * @return return a int value
	 */
	public int customerUniqueId() {
		return this.id;
	}
	
	/**
	 * function to get customer's name
	 * @return return's a string value
	 */
	public String getCustomerName() {
		return this.name;
	}
	
	
	/**
	 * function to get customer's phone number
	 * @return return's a int value
	 */
	public int getCustomerPhoneNum() {
		return this.phoneNum;
	}
	
	
	/**
	 * function to get customer's address
	 * @return returns a string value
	 */
	public String getCustomerAddress() {
		return this.address;
	}
	
	
	/**
	 * function to set customer's address
	 * @param address takes a string value
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * function to set customer's phone number
	 * @param number takes a int value as parameter
	 */
	public void setPhoneNumber(int number){
		this.phoneNum = number;
	}
	
	
	/**
	 * function to list all the products
	 */
	@Override
	public boolean listProducts() {
		System.out.println("\n---List of All Products---");
		int count = 0 ;
		for(int i = 0 ; i < Admin.branches.size(); i++) {
			for(int j = 0 ; j < Admin.branches.get(i).getProducts().size();j++) {
				System.out.println(Admin.branches.get(i).getProducts().get(j));
				count++;
			}
		}
		if(count == 0) {
			System.out.println("\nNo products !!!\n");
			return false;
		}else return true;
	}
	
	
	/**
	 * function to get the branch of a product by product id 
	 * @param productId takes a int value 
	 * @return returns a branch
	 */
	public Branch getBranchByProductId(int productId) {
		for(int i = 0 ; i < Admin.branches.size(); i++) {
			for(int j = 0 ; j < Admin.branches.get(i).getProducts().size();j++) {
				if(Admin.branches.get(i).getProducts().get(j).getProductId() == productId && Admin.branches.get(i).getProducts().get(j).getAmount()!=0) {
					return Admin.branches.get(i);
				}
			}
		}
		return null;
	}
	
	

	
	
	/**
	 * function to print customer's order history
	 */
	public void history() {
		for(int i = 0 ; i < BranchEmployee.sells.size(); i++) {
			if(BranchEmployee.sells.get(i).getCustomer().customerUniqueId() == this.id) {
				System.out.println("\n\t\t---Your Shopping History---");
				//if the uniqueId in sells history matches with provided unique id then show info
				System.out.println("customer id : "+this.id +"\t name : "+ BranchEmployee.sells.get(i).getCustomer().getCustomerName());
				System.out.println();
				if(BranchEmployee.sells.get(i).getCustomerProducts().size()==0) {
					System.out.println("\nNo product !!!\n");
					return;
				}
				for(int j = 0 ; j < BranchEmployee.sells.get(i).getCustomerProducts().size(); j++) {
					System.out.println(BranchEmployee.sells.get(i).getCustomerProducts().get(j));
				}
				System.out.println();
			}else {
				System.out.println("\nNo History Found!!!\n");
			}
		}
	}
	
	
	
	/**
	 * function to buy a product online
	 * @param productId takes a int value is the product id
	 * @param amount takes a string value is the amount of product to buy
	 * @return return true in successful purchase else returns false
	 */
	public boolean buyOnline(int productId,int amount) {
		Branch tempBranch = getBranchByProductId(productId);
		if(tempBranch == null) return false;
		for(int i = 0 ; i < tempBranch.getProducts().size();i++) {
			if(tempBranch.getProducts().get(i).getProductId() == productId) { // if product id matches
				if(tempBranch.getProducts().get(i).getAmount()>=amount) { //if amount matches
					tempBranch.getProducts().get(i).decreaseAmount(amount); // amount decreased
					for(int j = 0 ; j < BranchEmployee.sells.size();j++) {
						if(BranchEmployee.sells.get(j).getCustomer().customerUniqueId() == this.id) {
							BranchEmployee.sells.get(j).addToSellsHistory(tempBranch.getProducts().get(i), amount);
						}
					}
					return true;
				}else {
					System.out.println("\nProduct Out of Stock !!!\n");
				}
			}
		}
		return false;
	}
	
	/**
	 * function to update customer's billing address
	 * @param address takes a string value is the address of the customer
	 * @param phoneNum takes a int value is the phone number of the customer
	 */
	public void updateInfo(String address,int phoneNum) {
		this.address = address;
		this.phoneNum = phoneNum;
		System.out.println("\nUpdated !!!\n");
	}
	

}
