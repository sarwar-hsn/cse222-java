package infrastructure;

import java.util.LinkedList;

import customhelper.Point;


/**
 * 
 * This class will handle the skyline generation process
 *
 */
public class Skyline {
	
	Point[] pointsArray; 
	int capacity; 
	int index;
	private static int rowSize=0;
	private static int colSize=0;
	private LinkedList<Building> buildings;
	char[][] display;
	
	
	/**
	 * 
	 * @param buildings is  {@link ArrayList} {@link Building}
	 */
	public Skyline(LinkedList<Building> buildings) {
		this.buildings = buildings;
	}
	
	/**
	 * 
	 * @param capacity is integer value
	 */
	private Skyline(int capacity)
    {
        this.capacity = capacity;
        pointsArray = new Point[capacity];
        index = 0;
    }
 		   
	
	/**
	 * 
	 * @return returns a integer value
	 */
	private int size() {  
		return this.index;
	}
	
	
	 /**
	  * 
	  * @param st this function will take a Point as parameter
	  */
    private void add(Point st){
        if (index > 0 && pointsArray[index - 1].height == st.height)
            return;
        if (index > 0 && pointsArray[index - 1].left == st.left) {
        	pointsArray[index - 1].height= Math.max(pointsArray[index - 1].height, st.height);
            return;
        }
        pointsArray[index] = st;
        index++;
    }
    
    
    /**
     * this function to print the critical points in skyline
     */
    public void printPoints() {
    	for(int i = 0; i < this.pointsArray.length; i++) {
    		System.out.println(pointsArray[i]);
    	}
    }
    

   
    /**
     * 
     * @return returns a new instance of this class with the points we need to draw the sky-line silhouette
     */
    public Skyline findSkyline() {
	   return findSkyline(this.buildings,0,this.buildings.size()-1);
    }
    

   
   /**
    * 
    * @param array is of BuildingsArray type
    * @param l is integer value for the low side of the array
    * @param h is is integer value, represents the high side of the array 
    * @return returns a new instance of skyline class
    */
    private  Skyline findSkyline(LinkedList<Building> arr, int l, int h){
    	
    	if (l == h) { //checking if low == high then we got the solution
    		Skyline res = new Skyline(2);
    		res.add(new Point(arr.get(l).getPosition() , arr.get(h).getHeight()));
    		res.add(new Point(arr.get(l).getPosition()+arr.get(l).getLength(), 0));
    		return res;
    	}
    	int mid = (l + h) / 2; // mid point

    	//dividing the array into two halves
    	Skyline sl = findSkyline(arr, l, mid);
    	Skyline sr = findSkyline(arr, mid + 1, h);
    		
    	//merging the two halves
    	Skyline  res = sl.merge(sr);
    	return res;
    
    }
	
    
    /**
     * 
     * @param other is of type Skyline
     * @return returns a instance of Skyline class
     */
    private  Skyline merge(Skyline other){
        Skyline res = new Skyline(this.index+ other.size());
        
        int h1 = 0, h2 = 0; //for keeping track of left and right skyline height change
        int i = 0, j = 0;
        
        while (i < this.index && j < other.size()) {
            if (this.pointsArray[i].left < other.pointsArray[j].left) { 
            	int x1 = this.pointsArray[i].left;
                h1 = this.pointsArray[i].height;
                int maxh = Math.max(h1, h2);
                res.add(new Point(x1, maxh));
                if(rowSize < maxh) {
                	rowSize = maxh;
                }
                i++;
            }
            else {
                int x2 = other.pointsArray[j].left;
                h2 = other.pointsArray[j].height;
                int maxh = Math.max(h1, h2);
                res.add(new Point(x2, maxh));
                if(rowSize < maxh) {
                	rowSize = maxh;
                }
                j++;
            }
        }
        
        while (i < this.index) {
        	if(rowSize < pointsArray[i].height) {
            	rowSize = pointsArray[i].height;
            }
            res.add(pointsArray[i]);
            i++;
        }
        while (j < other.size()) {
        	if(rowSize < other.pointsArray[j].height) {
            	rowSize = other.pointsArray[j].height;
            }
            res.add(other.pointsArray[j]);
            j++;
        }
        return res;
    }
    
    
    /**
     * initializing array with enough space to draw the skyline view
     */
    private void initDraw() {
    	rowSize +=1;
    	colSize = pointsArray[index-1].left+1;
    	display = new char[rowSize][colSize];
    	for(int i =0; i < rowSize; i++) {
    		for(int j =0; j < colSize; j++) {
    			display[i][j]=' ';
    		}
    	}
    }
    
    
    
    /**
     * filling the necessary point to display the skyline
     */
    private void draw() {
    	
    	initDraw();
    	Point currentPoint = new Point(0, 0);
    	int i =0; 
    	int x = rowSize-1;

    	if(this.size() ==2 ) {
    		int boxRad = Math.max(this.pointsArray[0].height, this.pointsArray[1].height);
    		for(i =0; i < boxRad ; i++) {
    			for(int j =0; j < boxRad; j++) {
    				if(i ==0 || j==0 || j == boxRad-1) {
    					 System.out.print("*");
    				}
    				
    				else System.out.print(" ");
    				
    			}
    			System.out.println();
    		}
    		return;
    	}
    	
    	
    	while(i!=this.index) {   		
    		Point nextPoint= pointsArray[i];
    		for(int j = currentPoint.left; j < nextPoint.left; j++) {
				display[x-currentPoint.height][j]='*';
			}
    		if(nextPoint.height > currentPoint.height) {
    			for(int j = x - nextPoint.height; j <= x- currentPoint.height; j++) {
    				display[j][nextPoint.left]='*';
    			}
    			
    		}else{
    			for(int j = x - nextPoint.height; j >= x- currentPoint.height; j--) {
    				display[j][nextPoint.left]='*';
    			}
    		}
    		
    		currentPoint = pointsArray[i];
    		i++;
    	}
    	
    }
    
    
    
    /**
     * printing the skyline
     */
    public void printSkyline() {
    	draw();
    	for(int i =0; i < rowSize; i++) {
    		for(int j =0; j < colSize; j++) {
    			System.out.print(display[i][j]);
    		}
    		System.out.println();
    	}
    }
	
	
	
}
