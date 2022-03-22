package infrastructure;


/**
 * 
 * This class is extended from building class.
 * This class will hold necessary information to represent a office
 *
 */
public class Office extends Building {
	
	private String jobType;
	
	
	/**
	 * @param type is a enumeration for the building type
	 * @param owner is a string represents the name of the owner
	 * @param length is a integer value 
	 * @param height is a integer
	 * @param jobType is a String which tells the type of job the office handles
	 * @param side is a enumeration to show side of the building in streets perspective
	 * @param position is a integer value for the position of a building in street
	 */
	public Office( String owner, int length, int height,Side side,int position,String jobType) {
		super(BuildingType.office, owner, length, height,side,position);
		
		this.jobType = jobType;
	}
	
	/**
	 * 
	 * @return returns a String which tells the type of job for the office
	 */
	public String getJobType() {
		return this.jobType;
	}
	
	
	@Override
	public String toString() {
		 return "owner\t: "+this.getOwner()+"\n"+
				"length\t: "+this.getLength()+"\n"+
				"height\t: "+this.getHeight()+"\n"+
				"side\t: "+this.getSide()+"\n"+
				"position: "+this.getPosition()+"\n"+
				"job type: "+this.getJobType()+"\n";
				
	}

}
