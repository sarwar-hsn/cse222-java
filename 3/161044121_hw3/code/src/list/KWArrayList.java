package list;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class KWArrayList<E> extends AbstractList<E> implements Iterable<E>{
	
	/**
	 * related variables
	 */
	private E data[];
	private int capacity = 10;
	private int size = 0 ;
	
	
	/**
	 * default constructor with initial capacity 10
	 */
	@SuppressWarnings("unchecked")
	public KWArrayList() {
		 data =  (E[]) new Object[capacity];
	}
	
	
	/**
	 * 
	 * @param initialSize is a integer value
	 */
	@SuppressWarnings("unchecked")
	public KWArrayList(int initialSize) {
		this.capacity = initialSize;
		data =  (E[]) new Object[initialSize];
	}


	@Override
	public int size() {
		return this.size;
	}
	
	/**
	 * reallocated the array twice to it's capacity
	 */
	private void reallocate() {
		capacity = capacity * 2; 
		data = Arrays.copyOf(data, capacity);
	}
	
	@Override
	public boolean add(E data) {
		if(size <= capacity) {
			reallocate();
		}
		this.data[size++] = data;
		return true;
	}
	
	@Override
	public void add(int index, E data) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		if(size >= capacity) reallocate();
		//copy data till index 
		for(int i = size ; i > index ; i--) {
			this.data[i] = this.data[i-1];
		}
		this.data[index] = data;
		this.size++;
	}
	
	
	@Override
	public void clear() {
		for(int i = 0; i < this.size; i++) {
			this.data[i] = null;
		}
		this.size = 0 ; 
	}
	
	@Override
	public boolean contains(Object item) {
		for(int i = 0 ; i < this.size ; i++) {
			if(this.data[i] == item) return true;
		}
		return false;
	}
	
	@Override
	public int indexOf(Object item) {
		for(int i = 0 ; i < this.size ; i++) {
			if(this.data[i] == item) return i;
		}
		return -1;
	}
	
	@Override
	public E get(int index) {
		if(index < 0 || index >= size) throw new IndexOutOfBoundsException();
		return this.data[index];
	}
	
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	

	@Override
	public Iterator<E> iterator() {
		return new MyIterator();
	}
	
	
	@Override
	public E remove(int index) {
		if(index < 0 || index >= size ) throw new IndexOutOfBoundsException();
		E oldData = this.data[index];
		for(int i = index ; i < this.size(); i++) {
			this.data[i] = this.data[i+1];
		}
		size--;
		return oldData;
	}
	
	
	@Override
	public E set(int index , E newData) {
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		E oldData = this.data[index];
		this.data[index] = newData;
		return oldData;
	}
	
	
	/**
	 * 
	 * this is inner class to hold information about iterator
	 *
	 */
	class MyIterator implements Iterator<E>{
		
		int current = 0;
		
		
		/**
		 * constructor
		 */
		public MyIterator() {
			current = 0 ;
		}

		@Override
		public boolean hasNext() {
			return current < size; 
		}

		@Override
		public E next() {
		if(hasNext())
			return data[current++];
		else throw new NoSuchElementException();
		}
		
	}
	
	

}
