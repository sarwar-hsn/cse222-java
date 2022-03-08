package infrastructure;


/**
 * 
 * This class is extended from building class.
 * This class will hold necessary information to represent a house
 *
 */
public class House extends Building{
	
	private int rooms;
	private String color;

	/**
	 * this is constructor for house class.
	 * @param type is a enumeration for the building type
	 * @param owner is a string represents the name of the owner
	 * @param length is a integer value 
	 * @param height is a integer
	 * @param side is a enumeration to show side of the building in streets perspective
	 * @param rooms is integer to show number of rooms in a house
	 * @param color is string which tells the color of the house
	 * @param position is a integer value for the position of a building in street
	 */
	public House(String owner, int length, int height,Side side,int position,int rooms,String color) {
		super(BuildingType.house, owner, length, height,side,position);
		
		this.rooms=rooms;
		this.color = color;
	}
	
	//getters
	
	/**
	 * 
	 * @return returns integer value which is number of rooms in the house
	 */
	public int getRooms() {
		return this.rooms;
	}
	
	/**
	 * 
	 * @return returns String value which is color of the house
	 */
	public String getColor() {
		return this.color;
	}
	
	@Override
	public String toString() {
		return "owner\t: "+this.getOwner()+"\n"+
				"length\t: "+this.getLength()+"\n"+
				"height\t: "+this.getHeight()+"\n"+
				"side\t: "+this.getSide()+"\n"+
				"position: "+this.getPosition()+"\n"+
				"rooms\t: "+this.getRooms()+"\n"+
				"color\t:"+this.getColor()+"\n";
	}
	

	
}
