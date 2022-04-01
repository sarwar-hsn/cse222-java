import java.util.ArrayList;

public class Driver {
	
	public static void main(String[] args) {
		
		System.out.println("\tQuestion One");
		{
			System.out.println(Ques1.findSubstring("mdsarwarhossain","sar",1));
			System.out.println(Ques1.findSubstring("mdsarwarhossainsar","sar",2));
			System.out.println(Ques1.findSubstring("mdsarwarhossainsar","sar",3));
		}
		
		System.out.println("\tQuestion Two");
		
		{
			int[] array = {-10,2,5,11,17,20,26};
			System.out.println(Ques2.numberofItemsBetween(11, 20, array));
			System.out.println(Ques2.numberofItemsBetween(11, 100, array));
		}
		
		
		
		
		System.out.println("\tQuestion Three");
		{
			int[] array = {-10,2,5,11,-10,17,26};
			Ques3.contiguousSubarray(array, 7);
		}
		
		
		System.out.println("\tQuestion Five");
		{
			int arraylength = 7;
			int blocklength= 3;
			Ques5.totalPossibleConfig(arraylength, blocklength);
			
		}
		{
			int arraylength = 8;
			int blocklength= 3;
			Ques5.totalPossibleConfig(arraylength, blocklength);
			
			
		}
		{
			int arraylength = 8 ;
			int blocklength= 4;
			Ques5.totalPossibleConfig(arraylength, blocklength);
		}


	}

}
