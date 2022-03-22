package customhelper;


/**
 * 
 * This class will handle the critical points of skyline 
 *
 */
public class Point {
	public int left;
	public int height;
	

	/**
	 * default constructor
	 * @param x is a integer value
	 * @param y is a integer value
	 */
	public Point(int x ,int y) {
		this.left = x;
		this.height = y; 
	}
	
	
	@Override
	public String toString() {
		return "x:"+left+" y: "+height;
	}
}
