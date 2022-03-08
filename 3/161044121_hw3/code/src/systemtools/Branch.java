package systemtools;

import generateId.IdGenerator;
import list.HybridList;
import list.KWArrayList;
import systemEmployee.BranchEmployee;


/**
 * 
 *Class to hold information of a branch
 *
 */
public class Branch {
	
	/**
	 * related variables
	 */
    private int id;
    private String name;
    private KWArrayList<BranchEmployee> employees;
    protected HybridList<Product> products = new HybridList<Product>();

    /**
     * constructor
     * @param name takes a string value 
     */
    public Branch(String name) {
	this.name = name;
	id = new IdGenerator().getId();
	employees = new KWArrayList<BranchEmployee>();
    }
    
    /**
     * function to add product to branch
     * @param product
     */
    public void addProduct(Product product) {
    	products.add(product);
    }
    
    
    /**
     * function to get the products
     * @return returns List of Product object
     */
    public HybridList<Product> getProducts(){
    	return this.products;
    }
    
    
    /**
     * function to get the id of a branch
     * @return returns a integer value
     */
    public int getId() {
    	return this.id;
    }
    
    
    /**
     * function to get the name of a branch 
     * @return returns a string value
     */
    public String getName() {
    	return this.name;
    }
    
    /**
     * function to get the List of Branch Employee
     * @return returns a list BranchEmployee Object
     */
    public KWArrayList<BranchEmployee> getEmployees(){
    	return this.employees;
    }
    
    

}
