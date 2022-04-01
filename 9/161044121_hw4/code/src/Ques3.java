import java.util.ArrayList;
import java.util.Arrays;

public class Ques3 {
	
	
	/**
	 * 
	 * @param array is of integer array
	 * @param index is of type integer
	 * @param sum is of type integer
	 * @param targetSum is of type integer
	 * @return is of type integer
	 */
	private static int isSumPresent(int[] array,int index,int sum,int targetSum) {
		if(index == array.length) {
			return -1;
		}
		sum+=array[index];
		if(sum > targetSum) {
			return -1;
		}else if(sum == targetSum) {
			return index;
		}
		return isSumPresent(array, index+1, sum,targetSum);
		
	}
	
	
	/**
	 * 
	 * @param array is integer array
	 * @param currentIndex is integer value
	 * @param sum is of integer type to accumulate sum value
	 * @param result is {@link ArrayList} of integer
	 */
	private static void contiguousSubarray(int[] array,int currentIndex,int sum,ArrayList<int[]> result) {
		if(currentIndex == array.length) {
			return;
		}
		if(array[currentIndex] <= sum) {
			int isPresent = isSumPresent(array, currentIndex, 0, sum);
			if(isPresent!=-1) {
				int [] subArray = Arrays.copyOfRange(array, currentIndex, isPresent +1);
				result.add(subArray);
			}
		}
		contiguousSubarray(array,currentIndex+1, sum,result);
	}
	
	/**
	 * 
	 * @param array is integer array
	 * @param sum is of type integer
	 */
	public static void contiguousSubarray(int[] array,int sum) {
		ArrayList<int[]> result = new ArrayList<int[]>();
		contiguousSubarray(array, 0, sum,result);
		System.out.println("Contiguous SubArray for sum "+sum+" : ");
		if(result.isEmpty()) {
			System.out.println("[ ]");
		}else {
			for(int i = 0; i < result.size(); i++) {
				System.out.print("[ ");
				for(int j = 0; j < result.get(i).length; j++) {
					System.out.print(result.get(i)[j]+" ");
				}
				System.out.println("]");
			}
		}
		
	}

}
