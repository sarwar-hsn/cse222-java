package systemtools;

import generateId.IdGenerator;

/**
 * this class holds information of the customer product
 *
 */
public class Product {
	private int id;
	private String productName;
	private String model;
	private double price;
	private int amountAvailable;
	private String color;
	
	/**
	 * this is the constructor of the product class
	 * @param productName takes a string value is the name of the product
	 * @param model takes a string value is the model of the product
	 * @param price takes a string value is the unit price of the product
	 * @param availableAmount takes int value is the available amount of the product
	 * @param color takes a string value is the color of the model
	 */
	public Product(String productName,String model,double price,int availableAmount,String color) {
		this.id = new IdGenerator().getId();
		this.productName = productName;
		this.model = model;
		this.price = price;
		this.amountAvailable = availableAmount;
		this.color = color;
	}
	
	
	/**
	 * overriding string value
	 */
	public String toString() {
		String product="";
		product+="product id : "+this.id+"\tProduct Name : "+this.productName +"\t Model : "+this.model+"\t Price : "+this.price+"\t Available Amount : "+this.amountAvailable;
		product+="\t Color : "+this.color;
		return product;
	}
	
	/**
	 * @return returns a int value is the available amount
	 */
	public int getAmount() {
		return this.amountAvailable;
	}
	
	/**
	 * function to set new amount of product
	 * @param amount is a integer value 
	 */
	public void increaseAmount(int amount) {
		this.amountAvailable+=amount;
	}
	
	/**
	 * function to decrease available amount
	 * @param amount is a int value
	 */
	public void decreaseAmount(int amount) {
		this.amountAvailable-=amount;
	}
	
	
	/**
	 * 
	 * @return returns a integer value is the product id
	 */
	public int getProductId() {
		return this.id;
	}
	
	/**
	 * @return returns a string value is the name of the product
	 */
	public String getProductName() {
		return this.productName;
	}
	
	
	/**
	 * 
	 * @return returns a string value by concatenating product name and model
	 */
	public String getProductNameandModel() {
		return this.productName+"\t model : "+this.model;
	}

}
