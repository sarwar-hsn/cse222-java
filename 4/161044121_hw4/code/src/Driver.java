import java.util.*;

import bstheaptree.BstHeapTree;

import bstheaptree.MaxHeap;
import partOne.CustomIterator;
import partOne.MinHeap;




public class Driver {
	
	public static void main(String[] args) {
		//testing min heap
//		{
//			MinHeap<Integer> x = new MinHeap<>();
//			x.add(37);
//			x.add(23);
//			x.add(10);
//			x.add(16);
//			x.add(19);
//			x.add(9);
//			x.add(3);
//			
//			MinHeap<Integer> y = new MinHeap<>();
//			y.add(500);
//			y.add(300);
//			y.add(50);
//			x.merge(y);
//			System.out.println(x);
//			System.out.println("index of 23 : "+x.search(23));
//			System.out.println();
//			
//			CustomIterator<Integer> it = (CustomIterator<Integer>) x.iterator();
//			while(it.hasNext()) {
//				System.out.println(it.next());
//			}
//						
//		}
//		
		
		//max heap
		{
			BstHeapTree<Integer> x = new BstHeapTree<>();
			x.add(37);
			x.add(23);
			x.add(10);
			x.add(16);
			x.add(19);
			x.add(9);
			x.add(3);
			x.add(10);
			x.add(10);
			x.add(10);
			x.print();
			System.out.println("occurance of 10 : "+x.find(10));
			
			
		}
		
	}
		


}

