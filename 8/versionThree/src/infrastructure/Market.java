/**
 * 
 */
package infrastructure;


/**
 * This class is extended from building class.
 * This class will hold necessary information to represent a market
 *
 */
public class Market extends Building {
	
	private String openingTime;
	private String closingTime;
	/**
 @param type is a enumeration for the building type
	 * @param owner is a string represents the name of the owner
	 * @param length is a integer value 
	 * @param height is a integer
	 * @param side is a enumeration to show side of the building in streets perspective
	 * @param openingTime is of string 
	 * @param closingTime is of type string
	 * @param position is a integer value for the position of a building in street
	 */
	public Market(String owner, int length, int height,Side side,int position,String openingTime,String closingTime) {
		super(BuildingType.market, owner, length, height,side,position);
		this.openingTime = openingTime;
		this.closingTime = closingTime;
	}
	
	
	/**
	 * 
	 * @return return String which is opening time of the market
	 */
	public String getOpeningTime() {
		return this.openingTime;
	}
	
	
	/**
	 * 
	 * @return returns string which is the closing time of the market
	 */
	public String getClosingTime() {
		return this.closingTime;
	}
	
	
	@Override
	public String toString() {
		return "owner       : "+this.getOwner()+"\n"+
				"length      : "+this.getLength()+"\n"+
				"height      : "+this.getHeight()+"\n"+
				"side        : "+this.getSide()+"\n"+
				"position    : "+this.getPosition()+"\n"+
				"opening time: "+this.getOpeningTime()+"\n"+
				"closing time:"+this.getClosingTime()+"\n";
	}

}
