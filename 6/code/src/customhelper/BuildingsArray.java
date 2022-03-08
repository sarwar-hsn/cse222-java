package customhelper;
import infrastructure.*;




/**
 *this class will works as a wrapper for the array of Building
 */
public class BuildingsArray {
	private Building[] array;
	private int capacity = 2;
	private int index = 0;
	public BuildingsArray() {
		array = new Building[this.capacity];
	}
	
	
	
	/**
	 * this function will try and reallocate the array if it reaches it's capacity
	 */
	private void reallocate() {		
		//increases the capacity by 2 times
		this.capacity = this.capacity *2; 
		
		//allocating new array with increase capacity
		Building[] tempArray = new Building[this.capacity];
		
		//copying the old array to new array
		for(int i = 0; i < size(); i++) {
			tempArray[i]=array[i];
		}
		
		//reference back to main array
		array = tempArray;
		
	}
	
	
	/**
	 * 
	 * @return returns a integer value which is the current size of the buildings array
	 */
	public int size() {
		return this.index;
	}
	
	
	
	/**
	 * 
	 * @param x takes a Building
	 * @param y takes a Building 
	 * @return returns true if x < y returns -1 , x==y returns 0, x > y retunrs 1
	 */
	private int compare(Building x,Building y) {
		if(x.getPosition() < y.getPosition()) {
			return -1;
		}else if(x.getPosition() == y.getPosition()){
			if(x.getHeight() > y.getHeight()) {
				return -1;
			}else if (x.getHeight() == y.getHeight()) {
				return 0;
			}
		}
		
		return 1;
	}
	
	
	
	/**
	 * this is a private helper function to sort the items in the buildings array
	 */
	private void sort(){
        for (int i = 1; i < this.size(); i++) {
        	 Building key = array[i];
             int j = i - 1;
             while (j >= 0 && (compare(key,array[j])==-1)) { //   key<array[j] 
                 array[j + 1] = array[j];
                 j = j - 1;
             }
             array[j + 1] = key;
        }
    }
	
	
	/**
	 * 
	 * @param building takes a Building as argument and adds in the array
	 */
	public boolean add(Building building) {
		if(this.size() == this.capacity) {
			try {
				reallocate();
			} catch (Exception e) {
				System.out.println("failed to add more building");
				return false;
			}
		}
		array[index++] = building;
		sort();
		return true;
	}
	
	
	
	/**
	 * 
	 * @return removes the last element in the buildings array
	 */
	public Building remove() {
		Building temp = array[this.size()-1];
		this.index--;
		return temp;
		
	}
	
	
	
	/**
	 * 
	 * @param index is an integer value
	 * @return returns Building at the particular index if index if valid, otherwise throws exception
	 */
	public Building remove(int pos) {
		if(pos < 0 || pos >= this.size()) {
			
			throw new IndexOutOfBoundsException();
		}
		
		Building temp = array[pos];
		
		for(int i = pos; i < this.size()-1; i++) {
			array[i] = array[i+1];
		}
		this.index--;
		return temp;
	}
	
	
	
	/**
	 * 
	 * @param id is a integer value refers to id of a Building
	 * @return return a integer index corresponds to the building id
	 */
	public int getIndex(int id) {
		for(int i = 0; i < this.size(); i++) {
			if(array[i].getId() == id) {
				return i;
			}
		}
		return -1;
	}
	
	
	/**
	 * 
	 * @param index takes a integer value 
	 * @return returns a Building at a given index if index is valid, else throws exception
	 */
	public Building get(int index) {
		if(index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}
	
	
	
	/**
	 * 
	 * @param id is a integer value, represents the id of a Building
	 * @return returns a Building if a Building is found by a given id
	 */
	public Building getById(int id) {
		for(int i = 0; i < this.size(); i++) {
			if(array[i].getId() == id) {
				return array[i];
			}
		}
		return null;
	}
	
	
	
	

}




