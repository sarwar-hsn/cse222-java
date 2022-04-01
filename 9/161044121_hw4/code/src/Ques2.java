
public class Ques2 {
	private static int lowerIndex(int[] array,int searchItem,int low,int high) {
		if(low > high) {
			return low;
		}
		int mid = (low + high) / 2;
		if(searchItem <= array[mid]  ) {
			return lowerIndex(array, searchItem, low, mid-1);
		}
		return lowerIndex(array, searchItem, mid+1, high);	
	}
	
	private static int upperIndex(int[] array,int searchItem,int low,int high ) {
		if(low > high) {
			return high;
		}
		int mid = (low + high) / 2;
		if(searchItem < array[mid]  ) {
			return upperIndex(array, searchItem, low, mid-1);
		}
		return upperIndex(array, searchItem, mid+1, high);	
		
	}
	
	public static int numberofItemsBetween(int x,int y,int [] array) {
		int lIndex = lowerIndex(array, x, 0, array.length-1);
		int uIndex = upperIndex(array, y, 0, array.length-1);
		return uIndex-lIndex+1;
	}

}
