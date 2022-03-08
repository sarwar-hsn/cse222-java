package infrastructure;

import customhelper.BuildingsArray;

/**
 * 
 * this class holds information about the street
 *
 */
public class Street {
	private int length;
	private BuildingsArray leftSide;
	private BuildingsArray rightSide;
	private Skyline skyline;
	
	/**
	 * 
	 * @param length is of type integer
	 * @param side is Enumeration type Side
	 */
	public Street(int length) {
		this.length = length;
		this.leftSide = new BuildingsArray();
		this.rightSide = new BuildingsArray();
		
	}
	
	/**
	 * 
	 * @return returns integer which is the length of the street
	 */
	public int getLength() {
		return this.length;
	}
	
	
	/**
	 * 
	 * @return returns a BuildingsArray which represents one side of the street
	 */
	public BuildingsArray left() {
		return this.leftSide;
	}
	

	/**
	 * 
	 * @return returns a BuildingsArray which represents one side of the street
	 */
	public BuildingsArray right() {
		return this.rightSide;
	}
	
	@Override
	public String toString() {
		return "street length\t: "+this.getLength();
	}
	
	
	/**
	 * 
	 * @param building is of type Building
	 * @return returns true if building is added else false
	 */
	public boolean add(Building building) {
		//checking if the building can be placed in terms of length of the street
		if(building.getLength() <= (this.length - building.getPosition())) {
			if(building.getSide() == Side.left) {
				return leftSide.add(building);
			}else {
				return rightSide.add(building);
			}
		}
		return false;
	}
	
	
	
	/**
	 * 
	 * @param buildingId is of type integer
	 * @param side is of type Side
	 * @return returns the building that has been removed
	 */
	public Building remove(int buildingId,Side side) {
		if(side == Side.left) {
			
			int index = left().getIndex(buildingId);
			try {
				return left().remove(index);
			} catch (Exception e) {
				return null;
			}
			
		}else {
			int index = right().getIndex(buildingId);
			try {
				return right().remove(index);
			} catch (Exception e) {
				return null;
			}
			
		}
	}
	
	
	
	/**
	 * 
	 * @return returns if the left street's buildingsArray is empty
	 */
	public boolean isLeftEmpty() {
		return left().size()==0;
	}
	
	
	/**
	 * 
	 * @return returns if the right street's buildingsArray is empty
	 */
	public boolean isRightEmpty() {
		return right().size()==0;
	}

	
	
	/**
	 * 
	 * @return returns a integer value
	 */
	private int remainingLandLeft() {
		if(left().size()!=0) {
			Building last = left().get(left().size()-1);
			return this.length - last.getLength()+last.getPosition();
		}
		return this.length;
	}
	
	
	/**
	 * 
	 * @return returns a integer value
	 */
	private int remainingLandRight() {
		if(right().size()!=0) {
			Building last = right().get(right().size()-1);
			return this.length - last.getLength()+last.getPosition();
		}
		return this.length;
	}
	
	
	
	/**
	 * prints the remaining unoccupied space
	 */
	public void remainingLength() {
		System.out.println("Remaining Length on Left Side : "+remainingLandLeft());
		System.out.println("Remaining Length on Right Side : "+ remainingLandRight());
	}
	
	
	
	/**
	 * prints the building on the right side of the street 
	 */
	public void showBuildingRight() {
		if(right().size() == 0) {
			System.out.println("No Building Found ");
			return;
		}
		for(int i =0; i < right().size(); i++) {
			Building bd = right().get(i);
			focusPoints(bd);
		}
	}
	
	
	
	/**
	 * 
	 * @param buildingId takes a integer value
	 * @param side is of type Side
	 * @return returns a Building if found else null
	 */
	public Building getBuildingById(int buildingId,Side side) {
		if(side == Side.left) {
			try {
				int index = left().getIndex(buildingId);
				return left().get(index);
			} catch (Exception e) {
				return null;
			}
		}else {
			try {
				int index = right().getIndex(buildingId);
				return right().get(index);
			} catch (Exception e) {
				return null;
			}
		}
	}
	
	
	
	/**
	 * prints the buildings on the left side of the street
	 */
	public void showBuildingLeft() {
		if(left().size()==0) {
			System.out.println("No Building Found");
			return;
		}
		for(int i =0; i < left().size(); i++) {
			Building bd = left().get(i);
			focusPoints(bd);
		}
	}
	
	
	
	
	
	/**
	 * 
	 * @param bd is of type Building 
	 */
	private void focusPoints(Building bd) {
		if(bd instanceof House) {
			System.out.println("id : "+bd.getId()+"\thouse owner : "+ bd.getOwner());
		}else if (bd instanceof Office) {
			Office office  = (Office)bd;
			System.out.println("id : "+bd.getId()+"\toffice job type : "+office.getJobType());
		}else if (bd instanceof Market) {
			Market market = (Market)bd;
			System.out.println("id : "+bd.getId()+"\tclosing time : "+market.getClosingTime());
		}else  if (bd instanceof PlayGround){
			System.out.println("id : "+bd.getId()+"\tbuilding type : playground");
		}
	}
	
	
	
	/**
	 * 
	 * @return returns a String with information about playground of left side of the street
	 */
	private String numberRatioOfPlayGroundLeft() {
		int count =0;
		double totalLength =0;
		for(int i =0 ; i < left().size(); i++) {
			if(left().get(i) instanceof PlayGround) {
				count++;
				totalLength+=left().get(i).getLength();
			}
		}
		double ratio = totalLength/(double)this.length;
		return "On Left Side - Number of PlayGround : "+count+" Ratio of Length Compared to Street : "+ ratio;
	}
	
	
	
	
	/**
	 * 
	 * @return returns a String with information about playground of right side of the street
	 */
	private String numberRatioOfPlayGroundRight() {
		int count =0;
		double totalLength =0;
		for(int i =0 ; i < right().size(); i++) {
			if(right().get(i) instanceof PlayGround) {
				count++;
				totalLength+=right().get(i).getLength();
			}
		}
		double ratio = totalLength/(double)this.length;
		return "On Right Side - Number of PlayGround : "+count+" Ratio of Length Compared to Street : "+ ratio;
	}
	
	
	
	/**
	 * 
	 * @return returns a String value
	 */
	public String ratioLengthPlayGround() {
		return numberRatioOfPlayGroundLeft()+"\n"+numberRatioOfPlayGroundRight();
	}
	
	
	
	
	/**
	 * 
	 * @return returns a Strin g value
	 */
	public String lengthOccupiedLeft() {
		int houseLength =0;
		int officeLength =0;
		int marketLength =0;
		for(int i =0; i < left().size(); i++) {
			if(left().get(i) instanceof House) {
				houseLength+= left().get(i).getLength();
			}else if (left().get(i) instanceof Office) {
				officeLength+= left().get(i).getLength();
			}else if (left().get(i) instanceof Market) {
				marketLength+= left().get(i).getLength();
			}
		}
		return "On Left Side, Length Occupied By House : "+houseLength+" Market : "+marketLength+" Office : "+officeLength+"  Total: "+ (houseLength+marketLength+officeLength);
	}

	public String lengthOccupiedRight() {
		int houseLength =0;
		int officeLength =0;
		int marketLength =0;
		for(int i =0; i < right().size(); i++) {
			Building right = right().get(i);
			if(right instanceof House) {
				houseLength+= right.getLength();
			}else if (right instanceof Office) {
				officeLength+= right.getLength();
			}else if (right instanceof Market) {
				marketLength+= right.getLength();
			}
		}
		return "On Right Side, Length Occupied By House : "+houseLength+" Market : "+marketLength+" Office : "+officeLength+"  Total: "+ (houseLength+marketLength+officeLength);
	}
	
	
	
	/**
	 * displays the silhouette for the left side of the street
	 */
	public void silhouetteLeft() {
		if(left().size()!=0) {
			skyline = new Skyline(left());
			skyline= skyline.findSkyline();
			skyline.printSkyline();
		}else
			System.out.println("No Building Found");
		
	}
	
	
	/**
	 * displays the silhouette for the right side of the street
	 */
	public void silhouetteRight() {
		if(right().size()!=0) {
			skyline = new Skyline(right());
			skyline= skyline.findSkyline();
			skyline.printSkyline();
		}else
			System.out.println("No Building Found");
		
	}
	
	

}
