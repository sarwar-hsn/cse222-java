package systemtools;

import java.util.*;

import systemcustomer.Customer;

public class SellsHistory {
	private Customer customer;
	private List<ProductDetail> customerProducts = new ArrayList<ProductDetail>();
	
	public SellsHistory(Customer customer) {
		this.customer = customer;
		
	}
	//nested class
	private class ProductDetail{
		int productId;
		String productName;
		int purchasedAmount;
		public ProductDetail(int prductId,String productName,int purchasedAmount) {
			this.productId = prductId;
			this.productName = productName;
			this.purchasedAmount = purchasedAmount;
		}
		public String toString() {
			String detail="";
			detail+="Product id : "+this.productId;
			detail+="\tProduct : "+ this.productName+"\t purchased amount : "+this.purchasedAmount;
			return detail;
		}
		
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public List<ProductDetail> getCustomerProducts(){
		return this.customerProducts;
	}
	
	public void addToSellsHistory(Product product,int amount) {
		for(int i = 0 ; i < customerProducts.size(); i++) {
			if(customerProducts.get(i).productId == product.getProductId()) {
				customerProducts.get(i).purchasedAmount+=amount;
				return;
			}
		}
		customerProducts.add(new ProductDetail(product.getProductId(),product.getProductNameandModel(), amount));
	}
	
	
	
}
