package partOne;
import java.util.Arrays;
import java.util.Iterator;


/**
 * min heap implementation
 * 
 *
 * @param <E> generic class
 */
public class MinHeap<E> implements Iterable<E>{
	private int capacity = 10;
	private int size = 0;
	private E[] items ;
	
	
	/**
	 * constructor for min heap
	 */
	@SuppressWarnings("unchecked")
	public MinHeap() {
		items = (E[]) new Object[capacity];
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
	private boolean hasLeftChild(int index) {return getLeftChildIndex(index) < size;}
	/**
	 * 
	 * @param index takes integer 
	 * @return returns true or false depending on conditions
	 */
	private boolean hasRightChild(int index) {return getRightChildIndex(index) < size;}
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
	private E leftChild(int index) {return items[getLeftChildIndex(index)];}
	/**
	 * 
	 * @param index takes integer value
	 * @return returns generic data
	 */
	private E rightChild(int index) {return items[getRightChildIndex(index)];}
	/**
	 * 
	 * @param index takes integer value
	 * @return returns generic data
	 */
	private E parent(int index) {return items[getParentIndex(index)];}
	
	/**
	 * swaps two value
	 * @param indexOne takes int
	 * @param indexTwo takes int
	 */
	private void swap(int indexOne , int indexTwo) {
		E temp = items[indexOne];
		items[indexOne] = items[indexTwo];
		items[indexTwo] = temp;
	}
	
	
	/**
	 * extends capacity of the array base on need
	 */
	private void ensureCapacity() {
		if(capacity == size) {
			capacity*=2;
			items = Arrays.copyOf(items, capacity);
		}
	}
	
	
	/**
	 * rearrange heap
	 * @param initialPoint takes a int value
	 */
	private void heapifyDown(int initialPoint) {
		int index = initialPoint;
		while( hasLeftChild(index)) {
			int smallerChild = getLeftChildIndex(index);
			if(hasRightChild(index) && (int)rightChild(index) < (int)leftChild(index)) {
				smallerChild = getRightChildIndex(index);
			}
			if((int)items[index] < (int)items[smallerChild]) {
				break;
			}else {
				swap(index,smallerChild);
			}
			index = smallerChild;
		}
	}
	
	
	/**
	 * add a item to the heap
	 * @param item takes a generic value
	 */
	public void add(E item) {
		ensureCapacity();
		items[size++] = item;
		heapifyUp();
	}
	
	
	/**
	 * rearrange the heap 
	 */
	private void heapifyUp() {
		int index = size-1;		
		while(hasParent( index ) && (int)parent(index) > (int)items[index]) {
			swap(getParentIndex(index),index);
			index = getParentIndex(index);
		}
	}
	
	
	/**
	 * to get the size of the heap
	 * @return returns a int value
	 */
	public int size() {
		return size;
	}
	
	/**
	 * checks if the heap is empty
	 * @return returns a boolean value
	 */
	public boolean isEmpty() {
		return size!=0;
	}
	
	
	/**
	 * 
	 * @return returns the first element in the heap
	 */
	public E peek() {
		if(size == 0) throw new IllegalStateException();
		return items[0];
	}
	
	
	/**
	 * 
	 * @return returns and remove the first element in the heap
	 */
	public E poll() {
		if(size == 0 ) throw new IllegalStateException();
		E item = items[0];
		items[0] = items[size-1];
		size--;
		heapifyDown(0);
		return item;
	}
	
	
	@Override
	public String toString() {
		String temp = "[";
		for(int i = 0 ; i < size; i++) {
			if(i != size-1)
				temp+=(items[i]+", ");
			else
				temp+=items[i];
		}
		temp+="]";
		return temp;
	}
	
	
	/**
	 * search for a item
	 * @param item takes a generic value
	 * @return returns a int value
	 */
	public int search(E item) {
		for(int i = 0 ; i < size ; i++) {
			if((int)item == (int)items[i]) return i;
		}
		return -1;
	}
	
	/**
	 * merge two heap
	 * @param heap takes another heap
	 */
	public void merge(MinHeap<E> heap) {
		Iterator<E> it = heap.iterator();
		while(it.hasNext()) {
			this.add(it.next());
		}
	}
	
	
	/**
	 * removes the ith large element
	 * @param i takes a integer
	 * @return returns a generic value
	 */
	public E removeIthLargest(int i) {
		if( i > size) return null;
		else {
			MinHeap<E> temp = new MinHeap<>();
			int minHeapSize = i;
			for(int j = 0 ; j < minHeapSize ; j++) {
				temp.add(items[j]);
			}
			for(int j = minHeapSize; j < size ; j++) {
				if((int)items[j] > (int)temp.peek()) {
					temp.poll();
					temp.add(items[j]);
				}
			}
			E item = temp.peek();
			if(item != null) {
				int index = search(item);
				items[index] = items[size-1];
				size--;
				heapifyDown(index);
			}
			return item;
		}
	}
	
	

	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}
	
	
	/**
	 * 
	 * customer iterator class
	 *
	 */
	private class MyIterator implements CustomIterator<E>{
		
		private int lasItemReturned = -1;
		private int currentIndex = 0;
		
		@Override
		public boolean hasNext() {
			return currentIndex < size;
		}

		@Override
		public E next() {
			lasItemReturned = currentIndex;
			return items[currentIndex++];
		}
		
		@Override
		public void set(E item) {
			if(lasItemReturned < 0 || lasItemReturned > size) throw new IllegalStateException();
			items[lasItemReturned] = item;
			
		}
		
	}
	
	

}
