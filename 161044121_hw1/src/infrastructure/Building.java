/**
 * 
 */
package infrastructure;

/**
 * 
 * this class will hold all necessary information about building.
 * this is a abstract class and can't be instantiated directly
 *
 */
public abstract class Building {
	
	
	protected static int  uniqueId = 1;
	private int id =1;
	private BuildingType type;
	private String owner = null;
	private int length = 0;
	private int height = 0;
	private Side side;
	private int position;
	
	
	//getters
	
	
	/**
	 * 
	 * @return returns a random id created using java uuid
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * 
	 * @return returns type of building
	 */
	public BuildingType getType() {
		return this.type;
	}
	
	/**
	 * 
	 * @return returns the owner as string if present else null
	 */
	public String getOwner() {
		return this.owner;
	}
	
	/**
	 * 
	 * @return returns integer which is height of a building
	 */
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * 
	 * @return returns integer which is length of the building
	 */
	public int getLength() {
		return this.length;
	}
	
	
	/**
	 * 
	 * @return returns a integer value
	 */
	public int getPosition() {
		return this.position;
	}
	
	
	/**
	 * constructor for building class
	 * @param type is a enumeration for the building type
	 * @param owner is a string represents the name of the owner
	 * @param length is a integer value 
	 * @param height is a integer
	 * @param side is a enumeration to show side of the building in streets perspective
	 * @param position is a integer value for the position of a building in street
	 */
	public Building(BuildingType type,String owner,int length,int height,Side side,int position){
		this.type = type;
		this.owner = owner;
		this.height = height;
		this.length = length;
		this.side = side;
		this.position = position;
		this.id = uniqueId++;
	}
	
	
	/**
	 * constructor override for building class
	 * @param type is a enumeration for the building type
	 * @param length is a integer
	 * @param side is a enumeration to show side of the building in streets perspective
	 * @param position is a integer value for the position of a building in street
	 */
	public Building (BuildingType type,int length,Side side,int position){
		this.type = type;
		this.side = side;
		this.length = length;
		this.position = position;
		this.id = uniqueId++;
	}
	
	/**
	 * 
	 * @return returns Side enumeration which tells the side of the Street
	 */
	public Side getSide() {
		return this.side;
	}
}
