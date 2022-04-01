import java.util.ArrayList;

public class Ques5 {
	
	
	/**
	 *  records the bottom part of the result in the result
	 * @param n is of type integer
	 * @param size is of type integer
	 * @param blockSize is of type integer
	 * @param result is 2d arraylist
	 * @param width is the width of the arraylist to fit all the result
	 */
	 	private static void bottom(int n, int size, int blockSize, ArrayList<ArrayList<Integer>> result, int width) {
	        if (n <= size) {
	            return;
	        }
	        steps(n, size, blockSize, result, width);
	        bottom(n, size + 1, blockSize, result, width);
	    }

	    private static ArrayList<Integer> upper(int index, int blockSize, int width) {
	        ArrayList<Integer> list = init(width);
	        for (int i = 0; i < blockSize; i++) {
	            list.set(index - i, 1);
	        }
	        return list;
	    }

	    /**
	     * to initialize arraylist with all zero
	     * @param n is of type integer
	     * @return returns arraylist
	     */
	    private static ArrayList<Integer> init(int n) {
	        ArrayList<Integer> list = new ArrayList<Integer>();
	        for (int i = 0; i < n; i++) {
	            list.add(0);
	        }
	        return list;
	    }
	    
	    
	    
	    
	    /**
	     * 
	     * @param n is of integer type
	     * @param size is of integer type
	     * @param blockSize is of integer type
	     * @param result is 2d Arraylist
	     * @param listLength is length of the arraylist
	     */
	    private static void steps(int n, int size, int blockSize, ArrayList<ArrayList<Integer>> result, int listLength) {
	        if (n <= size) {
	            return;
	        }
	        int fIndex = size;
	        int lIndex = n - 1;
	        ArrayList<Integer> list = init(listLength);
	        for (int i = 1; i <= blockSize; i++) {
	            list.set(fIndex - i, 1);
	            list.set(lIndex + i, 1);
	        }
	        result.add(list);
	        steps(n - 1, size, blockSize, result, listLength);

	    }

	    
	    /**
	     * 
	     * @param n is of type integer
	     * @param blockSize is of type integer
	     * @return returns integer
	     */
	    private static int fact(int n, int blockSize) {
	        if (n <= blockSize) {
	            return 0;
	        }
	        return 1 + fact(n - 1, blockSize);
	    }

	    /**
	     * 
	     * @param n is of type integer
	     * @param blockSize is of type integer
	     * @param result is of type 2d arraylist
	     * @param width is length of the arrylist
	     * @return return integer value
	     */
	    private static int totalPossibleConfig(int n, int blockSize, ArrayList<ArrayList<Integer>> result, int width) {
	        if (n < blockSize) {
	            bottom(width - blockSize, blockSize, blockSize, result, width);
	            return 0;
	        }
	        result.add(upper(n - 1, blockSize, width));
	        return 1 + totalPossibleConfig(n - 1, blockSize, result, width) + fact(n - blockSize, blockSize);
	    }
	    
	    public static int totalPossibleConfig(int arrayLength,int blockSize) {
	    	ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
	    	int res =  totalPossibleConfig(arrayLength, blockSize, result, arrayLength);
	    	System.out.println("For block Size: " + blockSize + " and Array Length: " + arrayLength
	                + " total possible combination: " +res);
	    	for(int i = 0; i < result.size(); i++) {
				System.out.println(result.get(i));
			}
	    	return res;
	    }
	    

}
