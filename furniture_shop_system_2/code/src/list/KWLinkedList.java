package list;

import java.util.AbstractSequentialList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class KWLinkedList<E> extends AbstractSequentialList<E> implements Iterable<E> {
	
	/**
	 * inner class to hold node information
	 *
	 */
	class Node{
		E data;
		Node next = null;
		Node prev = null;
		public Node(E data) {
			this.data = data;
		}
	}
	
	/**
	 * related variables
	 */
	private Node head = null;
	private Node tail = null;
	private int size = 0 ;
	
	
	

	@Override
	public Iterator<E> iterator() {
		
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}
	
	
	
	@Override
	public boolean add(E data) {
		try {
			addLast(data);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public void add(int index , E data) {
		if(index <0 || index > size())throw new IndexOutOfBoundsException();
		if(index == size()) {
			addLast(data);
			return;
		}else if(index == 0){
			addFirst(data);
			return;
		}else {
			Node node = getNodeByIndex(index);
			if(node != null) {
				Node temp = new Node(data);
				Node prev = node.prev;
				temp.next = node;
				node.prev = temp;
				prev.next = temp;
				temp.prev = prev;
				size++;
				return;
			}
		}
	}
	
	@Override
	public void clear() {
		head = tail = null;
		size = 0 ;
	}
	
	@Override
	public boolean contains(Object item) {
		Node temp = head;
		while(temp != null) {
			if(temp.data == item) return true;
			temp = temp.next;
		}
		return false;
	}
	
	@Override
	public int indexOf(Object item) {
		Node temp = head;
		int count = 0;
		while(temp != null) {
			if(temp.data == item )return count;
			count++;
			temp = temp.next;
		}
		return -1;
	}

	@Override
	public E get(int index) {
		if(index < 0 || index >= size()) throw new IndexOutOfBoundsException();
		Node temp = head; 
		for(int i = 0 ; i < index; i++) {
			temp = temp.next;
		}
		return temp.data;
		
	}
	
	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}
	
	@Override
	public E remove(int index) {
		if(index < 0 || index >= size())throw new IndexOutOfBoundsException();
		if(index == 0) {
			E oldData = head.data;
			removeFirst();
			return oldData;
		} 
		else if(index == size()-1) {
			E oldData = tail.data;
			removeLast();
			return oldData;
		} 
		else {
			Node node = getNodeByIndex(index);
			E oldData = node.data;
			Node prev = node.prev;
			Node next = node.next;
			node = null;
			prev.next=next;
			next.prev=prev;
			size--;
			return oldData;
		}
	}
	
	
	
	/**
	 * 
	 * @param index is a integer value
	 * @return if correct index is provided then it returns the node at that index else returns null
	 */
	private Node getNodeByIndex(int index) {
		Node temp = head;
		for(int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp;
	}
	
	
	/**
	 * add a element at the head of the list
	 * @param data takes a generic element
	 */
	private void addFirst(E data ) {
		if(head == null) {
			head = tail = new Node(data);
		}else {
			Node temp = new Node(data);
			temp.next = head;
			head.prev = temp;
			head = temp;
		}
		size++;
	}
	
	
	/**
	 * add data at the end of the list
	 * @param data takes a generic element
	 */
	private void addLast(E data) {
		if(head == null) {
			head = tail = new Node(data);
		}else {
			Node temp = new Node(data);
			tail.next = temp;
			temp.prev = tail;
			tail = temp;
		}
		size++;
	}
	
	
	/**
	 * removes the tail of the list 
	 */
	private void removeLast() {
		Node node = tail.prev;
		tail = null;
		node.next = null;
		tail = node;
		size--;
	}
	
	
	/**
	 * removes the head node of the list
	 */
	private void removeFirst() {
		Node node = head.next;
		head = null;
		node.prev = null;
		head = node;
		size--;	
	}
	
	
	
	@Override
	public ListIterator<E> listIterator(){
		return new MyListIterator(0);
	}
	
	@Override
	public ListIterator<E> listIterator(int i){
		return new MyListIterator(i);
	}
	
	
	/**
	 * class to hold information about list iterator
	 *
	 */
	class MyListIterator implements ListIterator<E>{
		Node nextItem;
		Node lastItemReturned;
		int index = 0;
		
	
		/**
		 * constructor
		 * @param i is the index position of the iterator
		 */
		public MyListIterator(int i) {
			if(i < 0 || i>=size())throw new IndexOutOfBoundsException();
			lastItemReturned = null;		
			if(i == size()) {
				nextItem = null;
				this.index = size();
			}
			nextItem = head;
			for(int index = 0 ; index < i; index++) {
				this.index+=1;
				nextItem = nextItem.next;
			}
		}
		

		@Override
		public boolean hasNext() {
			return nextItem!=null;
		}

		@Override
		public E next() {
			if(!hasNext())throw new NoSuchElementException();
			lastItemReturned = nextItem;
			nextItem = nextItem.next;
			this.index++;
			return lastItemReturned.data;
		}

		@Override
		public boolean hasPrevious() {
			return ((nextItem==null && size()!=0)||nextItem.prev!=null);
		}

		@Override
		public E previous() {
			
			if(!hasPrevious())throw new NoSuchElementException();
			
			if(nextItem == null) {
				nextItem = tail;
			}else
				nextItem = nextItem.prev;
			lastItemReturned = nextItem;
			index--;
			return lastItemReturned.data;
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public int previousIndex() {
			return index-1;
		}

		@Override
		public void remove() {
			if(lastItemReturned == null) throw new IllegalStateException();
			if(lastItemReturned == head) {
				Node temp = lastItemReturned.next;
				temp.prev = null;
				lastItemReturned = null;
				head = temp;
				index--;
				return;
			}else if(lastItemReturned == tail) {
				Node temp = lastItemReturned.prev;
				lastItemReturned= null;
				temp.next=null;
				tail = temp;
				index--;
				return;
			}else {
				Node prev = lastItemReturned.prev;
				Node next = lastItemReturned.next;
				lastItemReturned = null;
				prev.next = next;
				next.prev = prev;
				size--;
				index--;
				return;
			}
			
		}

		@Override
		public void set(E e) {

		}

		@Override
		public void add(E e) {
			
			if(nextItem == head) {
				
				addFirst(e);
			}else if(nextItem == null && size!=0) {
				addLast(e);
			}else {
				Node node = new Node(e);
				node.next = lastItemReturned.next;
				node.prev = lastItemReturned;
				lastItemReturned.next = node;
				lastItemReturned.next.prev = node;
				size++;
			}
			index++;
		}
		
	}
	
	
	
	@Override
	public String toString() {
		String temp = "[ ";
		Node node = head;
		while(node!= null) {
			temp = temp+node.data+" ";
			node = node.next;
		}
		temp = temp +"]";
		return temp;
	}
	


}
