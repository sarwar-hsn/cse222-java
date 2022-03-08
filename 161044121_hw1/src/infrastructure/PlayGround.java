package infrastructure;



/**
 * 
 * this class is extended from building class which represents a playground
 *
 */
public class PlayGround extends Building {

	/**
	 * 
	 * @param length is of type double for the length of playground
	 * @param side is a enumeration to show side of the playground in streets perspective
	 * @param position is a integer value for the position of a building in street
	 */
	public PlayGround(int length,Side side,int position) {
		super(BuildingType.playground, length,side,position);
		
	}
	
	
	@Override
	public String toString() {
		return "length\t: "+this.getLength()+"\n"+
				"side\t: "+this.getSide()+"\n"+
				"position: "+this.getPosition()+"\n";
	}

}
