import java.util.Scanner;

import citymanagement.Menu;
import infrastructure.Building;
import infrastructure.BuildingType;
import infrastructure.House;
import infrastructure.Market;
import infrastructure.Office;
import infrastructure.PlayGround;
import infrastructure.Side;
import infrastructure.Skyline;
import infrastructure.Street;

class Driver{
	private static int streetLength;
	private static Street street;
	private static Scanner input;
	
	public static void main(String[] args) {
		Menu.welcomeMsg();
		
		input = new Scanner(System.in);
		streetLength = getStreetLength();
		street = new Street(streetLength);
		System.out.println("Street Length : " + street.getLength());
		boolean program = true;
		
		
		while(program) {
			int mainMenuResponse = mainMenu();
			if(mainMenuResponse ==1) {
				int editingModeResponse = editingMode();
				if(editingModeResponse == 1) {
					buildingOptions();
				}else {
					removeBuilding();
				}
			}else if (mainMenuResponse == 2) {
				viewMode();
			}
			if(mainMenuResponse ==0 ) {
				System.out.println("Exiting Program ...");
				return;
			}
		}
		input.close();
		return;
	}
	
	
	
	/**
	 * this function will assist with the view mode of the program
	 */
	private static void viewMode() {
		while(true) {
			try {
				Menu.viewOption();
				System.out.print("Enter your choice : ");
				int response = input.nextInt();
				if(response < 0 || response > 6) {
					System.out.println("Invalid Choice !!!");
				}else {
					switch (response) {
					case 0:
						return;
					case 1:
						street.remainingLength();
						break;
					case 2:		
						System.out.println("\t\t--Building on Left Side--");
						street.showBuildingLeft();		
						if(!street.isLeftEmpty() && yesNoPromt()  ) {
							try {
								System.out.print("Enter a building id : ");
								int buildingId = input.nextInt();
								Building temp = street.getBuildingById(buildingId, Side.left);
								if(temp == null) {
									System.out.println("Couldn't find building details !!!");
								}else System.out.println(temp);
							} catch (Exception e) {
								input.nextLine();
								System.out.println("Invalid Input !!!");
							}
						}
						break;
					case 3:
						System.out.println("\t\t--Building on Right Side--");
						street.showBuildingRight();	
						if( !street.isRightEmpty() && yesNoPromt() ) {
							try {
								System.out.print("Enter a building id : ");
								int buildingId = input.nextInt();
								Building temp = street.getBuildingById(buildingId, Side.right);
								if(temp == null) {
									System.out.println("Couldn't find building details !!!");
								}else System.out.println(temp);
							} catch (Exception e) {
								input.nextLine();
								System.out.println("Invalid Input !!!");
							}
						}
						break;
					case 4:
						System.out.println(street.ratioLengthPlayGround());
						break;
					case 5:
						System.out.println(street.lengthOccupiedLeft());
						System.out.println(street.lengthOccupiedRight());
						break;
					case 6:
						Side side = pickSide();
						if(side == Side.left) {
							System.out.println("\t\t---Silhouette Display of Left Side of Street---");
							street.silhouetteLeft();
						}else {
							System.out.println("\t\t---Silhouette Display of Right Side of Street---");
							street.silhouetteRight();
						}
						break;
					default:
						break;
					}
				}
			} catch (Exception e) {
				System.out.println(e.toString());
				input.nextLine();
				System.out.println("Invalid Input !!!");
			}
		}
	}
	
	
	
	
	/**
	 * this function asks user yes / no question
	 * @return returns a booleans value
	 */
	private static boolean yesNoPromt() {
		while(true) {
			try {
				System.out.print("\nDo you want to see detail of a property(y/n) : ");
				String response = input.next();
				if(response.contentEquals("y") || response.contentEquals("Y")) {
					return true;
				}else if(response.contentEquals("n") || response.contentEquals("N")) {
					return false;
				}else {
					System.out.println("Invalid Response !!!");
				}
				
			} catch (Exception e) {
				input.nextLine();
				System.out.println("Invalid Response !!!");
			}
		}
		
		
	}
	
	
	
	
	
	
	/**
	 * helps the program with removal of building
	 */
	private static void removeBuilding() {
		int buildingId ;
		Side side = pickSide();
		if(side == Side.left) {
			System.out.println("\t\t\t---Building on the left side of the street---");
			street.showBuildingLeft();
		}else {
			System.out.println("\t\t\t---Building on the right side of the street---");
			street.showBuildingRight();
		}
		try {
			System.out.println("Enter Building Id to Remove(Press 0 to GoBack ) : ");
			buildingId = input.nextInt();
			if(buildingId ==0 ) {
				return;
			}
		} catch (Exception e) {
			input.nextLine();
			System.out.println("Invalid Input !!!");
			return;
		}
		Building removed = street.remove(buildingId, side);
		if(removed == null) {
			System.out.println("Failed to Remove Building. Carefully Type Building Id !!!");
		}else {
			System.out.println("Building removed Successfully : \n"+removed.toString());
		}
	}
	
	
	
	/**
	 * shows the types of available building and allows user to choose
	 */
	private static void buildingOptions() {
		boolean validResponse = false;
		while(!validResponse) {
			try {
				Menu.typeOptions();
				System.out.print("\nChose a Building Type : ");
				int type = input.nextInt();
				if(type >=0 && type <=4) {
					if(type == 0 ) return ;
					else if (type == 1) addBuilding(BuildingType.office);
					else if (type == 2) addBuilding(BuildingType.house);
					else if (type == 3) addBuilding(BuildingType.market);
					else if (type == 4) addBuilding(BuildingType.playground);
					validResponse= true;
				}
			} catch (Exception e) {
				input.nextLine();
				System.out.println("Invalid Choice !!!");
			}
			
		}
		
	}
	
	
	
	
	/**
	 * this function adds a Building on the street based on their type
	 * @param type is of type Side
	 */
	private static void addBuilding(BuildingType type) {
		switch (type) {
		case office:
			System.out.println(addOffice());
			break;
		case house:
			System.out.println(addHouse());
			break;
		case market:
			System.out.println(addMarket());
			break;
		case playground:
			System.out.println(addPlayGround());
			break;
		default:
			break;
		}
	}
	
	
	
	/**
	 * 
	 * @return returns  a String Value
	 */
	private static String addPlayGround() {
		int length, position;
		Side side = null;
		try {
			System.out.print("Enter Length : ");
			length = input.nextInt();
			if(length < 0 || length > street.getLength()) {
				return "invalid length\n";
			}
			System.out.print("Enter position : ");
			position = input.nextInt();
			if(position < 0 || position > street.getLength()) {
				return "invalid position !!!";
			}
			side = pickSide();
			PlayGround pl = new PlayGround(length, side, position);
			if(street.add(pl)) {
				return "Playground Added Successfully (street length - "+street.getLength()+ ") : \n"+pl.toString();
			}
			
		} catch (Exception e) {
			input.nextLine();
			return "Invalid Input !!!";
		}
		
		
		return "Failed to Add PlayGround";
	}
	
	private static String addMarket() {
		int length, height , position;
		String owner,openingTime,closingTime;
		Side side = null;
		try {
			System.out.print("Enter Length : ");
			length = input.nextInt();
			if(length < 0 || length > street.getLength()) {
				return "invalid length\n";
			}
			System.out.print("Enter Height : ");
			height = input.nextInt();
			System.out.print("Enter position : ");
			position = input.nextInt();
			if(position < 0 || position > street.getLength()) {
				return "invalid position !!!";
			}
			input.nextLine();
			System.out.print("Enter Owner Name : ");
			owner = input.nextLine();
			System.out.print("Enter Opening Time : ");
			openingTime = input.nextLine();
			System.out.print("Enter Closing Time : ");
			closingTime = input.nextLine();
			side = pickSide();
			Market market = new Market(owner, length, height, side, position, openingTime, closingTime);
			if(street.add(market)) {
				return "Market Added Successfully(street length - "+street.getLength()+") : \n"+market.toString();
			}
			
		} catch (Exception e) {
			input.nextLine();
			return "Invalid Input !!!";
		}
		
		
		return "Failed to Add Market";
	}
	
	
	
	
	/**
	 * 
	 * @return returns a String value
	 */
	private static String addHouse() {
		int length, height , position, rooms;
		String owner,color;
		Side side = null;
		try {
			System.out.print("Enter Length : ");
			length = input.nextInt();
			if(length < 0 || length > street.getLength()) {
				return "invalid length\n";
			}
			System.out.print("Enter Height : ");
			height = input.nextInt();
			System.out.print("Enter position : ");
			position = input.nextInt();
			if(position < 0 || position > street.getLength()) {
				return "invalid position !!!";
			}
			System.out.print("Enter Number of Rooms : ");
			rooms = input.nextInt();
			input.nextLine();
			System.out.print("Enter Owner Name : ");
			owner = input.nextLine();
			System.out.print("Enter Color of the House : ");
			color = input.nextLine();
			side = pickSide();
			Building building = new House(owner, length, height, side, position, rooms, color);
			if(street.add(building)) {
				return "House Added Successfully (street length - "+street.getLength()+") :\n"+building.toString();
			}
			
		} catch (Exception e) {
			input.nextLine();
			return "Invalid Input !!!";
		}
		
		
		return "Failed to Add House";
	}
	
	
	
	
	/**
	 * 
	 * @return returns a Side
	 */
	private static Side pickSide() {
		while(true) {
			try {
				Menu.sideOptions();
				System.out.print("Choose side of the street : ");
				int response  = input.nextInt();
				if(response == 1) {
					return Side.left;
				}else if (response == 2) {
					return Side.right;
				}else {
					System.out.println("Invalid Choice !!!");
				}
			} catch (Exception e) {
				System.out.println("Invalid Input !!!");
				input.nextLine();
			}
		}
	}
	
	
	
	/**
	 * 
	 * @return returns a String value
	 */
	private static String addOffice() {
		int length, height , position;
		String owner,jobType;
		Side side = null;
		try {
			System.out.print("Enter Length : ");
			length = input.nextInt();
			if(length < 0 || length > street.getLength()) {
				return "invalid length\n";
			}
			System.out.print("Enter Height : ");
			height = input.nextInt();
			System.out.print("Enter position : ");
			position = input.nextInt();
			if(position < 0 || position > street.getLength()) {
				return "invalid position !!!";
			}
			input.nextLine();
			System.out.print("Enter Owner Name : ");
			owner = input.nextLine();
			System.out.print("Enter Job Type : ");
			jobType = input.nextLine();
			side = pickSide();
			Office office = new Office(owner, length, height, side, position, jobType);
			if(street.add(office)) {
				return "Office Added Successfully (street length - "+street.getLength()+"): \n"+office.toString();
			}
			
		} catch (Exception e) {
			input.nextLine();
			return "Invalid Input !!!";
		}
		
		
		return "Failed to Add Office";
	}
	
	
	
	
	
	/**
	 * 
	 * @return returns a integer value
	 */
	private static int editingMode() {
		int response = -1;
		boolean validResponse = false;
		while(!validResponse) {
			try {
				Menu.editingOption();
				System.out.print("Enter Your Choice : ");
				response = input.nextInt();
				if(response == 0 || response == 1 || response == 2 ) {
					validResponse = true;
				}
			} catch (Exception e) {
				input.nextLine();
				System.out.println("Invalid Input !!!");
			}
		}
		return response;
	}
	
	
	
	
	/**
	 * 
	 * @return returns a integer value
	 */
	private static int mainMenu() {
		
		int response = -1;
		boolean validResponse = false;
		while(!validResponse) {
			try {
				Menu.mainMenuOptions();
				System.out.print("Enter Your Choice : ");
				response = input.nextInt();
				if(response == 0 || response ==1 || response ==2) {
					validResponse = true;
				}
				
			} catch (Exception e) {
				input.nextLine();
				System.out.println("Invalid Input !!!");
			}
		}
		return response;
	}
	
	
	
	/**
	 * 
	 * @return returns a integer value
	 */
	private static int getStreetLength() {
		int streetLength = 0 ;
		boolean validInput = false;
		
		while(!validInput) {
			try {
				Menu.streetOption();
				streetLength = input.nextInt();
				if(streetLength <= 0) {
					System.out.println("Try positive integer which is greate than zero");
				}else
					validInput = true;
			} catch (Exception e) {
				System.out.println("Invalid Input !!!");
				input.nextLine();
			}
		}
		return streetLength;
		
	}
}
