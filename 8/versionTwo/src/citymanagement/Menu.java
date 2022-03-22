package citymanagement;


/**
 * 
 *this class will display all the functionalities the program will perform
 *
 */
public class Menu {
	
	

	public static void welcomeMsg() {
		System.out.println("\n\t\t---Welcome To City Planning---\n");
	}
	
	
	
	public static void sideOptions() {
		System.out.println("1. Left");
		System.out.println("2. Right");
	}
	
	
	public static void typeOptions() {
		System.out.println("\n1. Office");
		System.out.println("2. House");
		System.out.println("3. Market");
		System.out.println("4. PlayGround");
		System.out.println("0. Go Back");
		
	}
	
	public static void mainMenuOptions() {
		System.out.println("\n1. Enter Editing Mode");
		System.out.println("2. Enter Viewing Mode");
		System.out.println("0. Exit");
	}
	public static void streetOption() {
		System.out.print("Please Enter Street Length : ");
	}
	
	
	
	public static void editingOption() {
		System.out.println("\n1. add a building");
		System.out.println("2. remove a building");
		System.out.println("0. go back");
	}
	
	public static void viewOption() {
		System.out.println("\n1. Remaining Length of Lands on the street");
		System.out.println("2. List of Buildings on the Left Side of the  Street");
		System.out.println("3. List of Buildings on the Right Side of the  Street");
		System.out.println("4. Number and ratio of Length of Playgrounds on the street");
		System.out.println("5. Total Length Occupied By Market / House / Office");
		System.out.println("6. SkyLine Silhouette of the Street");
		System.out.println("0. Go Back");
	}
}
