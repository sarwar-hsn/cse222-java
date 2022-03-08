package bstheaptree;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * max heap implementation
 * 
 *
 * @param <E> generic class
 */
public class MaxHeap<E>{
	
	/**
	 * nodes for the max heap
	 *
	 */
	class Node{
		E item;
		int itemCount=0;
		/**
		 * constructo of node
		 * @param item
		 */
		Node(E item){
			this.item = item;
		}
	}
	
	/**
	 * related variables
	 */
	private ArrayList<Node> items;
	
	/**
	 * constructor for maxHeap
	 */
	public MaxHeap() {
		items = new ArrayList<>();
	}
	
	
	/**
	 * 
	 * @param parentIndex takes parent index
	 * @return returns left child index
	 */
	private int getLeftChildIndex(int parentIndex) {return (parentIndex*2+1);}
	/**
	 * 
	 * @param parentIndex takes parent index
	 * @return returns right child index
	 */
	private int getRightChildIndex(int parentIndex) {return (parentIndex*2+2);}
	/**
	 * 
	 * @param childIndex takes int value
	 * @return returns int value
	 */
	private int getParentIndex(int childIndex) {return ((childIndex-1)/2);}
	
	
	/**
	 * 
	 * @param index takes integer 
	 * @return returns true or false depending on conditions
	 */
	private boolean hasLeftChild(int index) {return getLeftChildIndex(index) < items.size();}
	/**
	 * 
	 * @param index takes integer 
	 * @return returns true or false depending on conditions
	 */
	private boolean hasRightChild(int index) {return getRightChildIndex(index) < items.size();}
	/**
	 * 
	 * @param index takes integer 
	 * @return returns true or false depending on conditions
	 */
	private boolean hasParent(int index) {return getParentIndex(index) >= 0;}
	
	
	/**
	 * 
	 * @param index takes integer value
	 * @return returns generic data
	 */
	private E leftChild(int index) {return items.get(getLeftChildIndex(index)).item;}
	/**
	 * 
	 * @param index takes integer value
	 * @return returns generic data
	 */
	private E rightChild(int index) {return items.get(getRightChildIndex(index)).item;}
	/**
	 * 
	 * @param index takes integer value
	 * @return returns generic data
	 */
	private E parent(int index) {return items.get(getParentIndex(index)).item;}
	
	/**
	 * swaps two value
	 * @param indexOne takes int
	 * @param indexTwo takes int
	 */
	private void swap(int indexOne , int indexTwo) {
		Node temp = items.get(indexOne);
		items.set(indexOne, items.get(indexTwo));
		items.set(indexTwo, temp);
	}
	
	
	/**
	 * 
	 * @param index takes a int value
	 * @return returns occurance of a item in a particular index
	 */
	public int getOccurance(int index) {
		if(index <0 || index >= items.size())throw new IndexOutOfBoundsException();
		return items.get(index).itemCount;
	}
	
	
	@Override
	public String toString() {
		String temp = "[";
		for(int i = 0 ; i < items.size(); i++) {
			if(i != items.size()-1)
				temp+=(items.get(i).item+"-"+items.get(i).itemCount+", ");
			else
				temp+=(items.get(i).item+"-"+items.get(i).itemCount);
		}
		temp+="]";
		return temp;
	}
	
	
	/**
	 * 
	 * @return returns a generic value
	 */
	public E peek() {
		if(items.size() == 0) throw new IllegalStateException();
		return items.get(0).item;
	}
	

	
	/**
	 * 
	 * @param item takes a generic item
	 * @return returns a int value
	 */
	public int search(E item) {
		for(int i = 0 ; i < items.size() ; i++) {
			if((int)item == (int)items.get(i).item) return i;
		}
		return -1;
	}
	
	
	/**
	 * 
	 * @param item takes a generic item
	 */
	public void add(E item) {
		
		int index = search(item);
		if(index!=-1) {
			items.get(index).itemCount+=1;
		}else {
			Node temp = new Node(item);
			temp.itemCount+=1;
			items.add(temp);
			heapifyUp();
		}
		
		
	}
	
	/**
	 * private in context
	 * to arrange the heap bottom to top
	 */
	private void heapifyUp() {
		int index = items.size()-1;		
		while(hasParent( index ) && (int)parent(index) < (int)items.get(index).item) {
			swap(getParentIndex(index),index);
			index = getParentIndex(index);
		}
	}
	
	
	/**
	 * 
	 * @return returns a generic item
	 */
	public E poll() {
		if(items.size() == 0 ) throw new IllegalStateException();
		if(items.get(0).itemCount > 1) {
			items.get(0).itemCount-=1;
			return items.get(0).item;
		}else {
			E item = items.get(0).item;
			items.set(0, items.get(items.size()-1));
			items.remove(items.size()-1);
			heapifyDown(0);
			return item;
		}
	}
	
	
	/**
	 * 
	 * @param index takes a int value
	 */
	public void remove(int index) {
		Node node = items.get(index);
		node.itemCount-=1;
		if(node.itemCount<=0) {
			items.set(index, items.get(items.size()-1));
			items.remove(items.size()-1);
			heapifyDown(index);
		}
	}
	
	
	/**
	 * to rearrange the heap
	 * @param initialPoint takes a int value
	 */
	private void heapifyDown(int initialPoint) {
		int index = initialPoint;
		while( hasLeftChild(index)) {
			int largeChildIndex = getLeftChildIndex(index);
			if(hasRightChild(index) && (int)rightChild(index) > (int)leftChild(index)) {
				largeChildIndex = getRightChildIndex(index);
			}
			if((int)items.get(index).itemCount > (int)items.get(largeChildIndex).item) {
				break;
			}else {
				swap(index,largeChildIndex);
			}
			index = largeChildIndex;
		}
	}
	
	/**
	 * 
	 * @return returns a int value
	 */
	public int size() {
		return items.size();
	}
	
	/**
	 * 
	 * @return returns a boolean value
	 */
	public boolean isEmpty() {
		return items.isEmpty();
	}
	
}
