package list;

import java.util.AbstractList;

public class HybridList<E> extends AbstractList<E> {
	
	/**
	 * Related variables
	 */
	private int max_number = 5;
	private int size = 0 ;
	private KWLinkedList<KWArrayList<E>> list = new KWLinkedList<KWArrayList<E>>();
	
	
	/**
	 * default constructor
	 */
	public HybridList() {
		list.add(new KWArrayList<E>(max_number));
	}
	
	
	/**
	 * 
	 * @param maxNum is the threshold limit
	 */
	public HybridList(int maxNum) {
		this.max_number = maxNum;
		list.add(new KWArrayList<E>(max_number));
	}
	
	
	/**
	 * deletes if a arraylist has size zero
	 */
	public void deleteEmptyList() {
		for(int i = 0 ; i < list.size(); i++) {
			if(list.get(i).isEmpty()) {
				System.out.println("mark to delete : "+ list.get(i));
			}
		}
	}
	
	
	/**
	 * 
	 * @return returns index of a arraylist if it has some space left else returns -1
	 */
	private int isFull() {
		for(int i = 0 ; i < list.size() ; i++) {
			if(list.get(i).size() < max_number) return i;
		}
		return -1;
	}
	


	@Override
	public E get(int index) {
		if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		return list.get(index / max_number).get(index % max_number);
	}

	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public boolean add(E data) {
		
		int rowIndex = isFull();
		if(rowIndex != -1) {
			list.get(rowIndex).add(data);
			size++;
			return true;
		}else {
			list.add(new KWArrayList<E>(max_number));
			list.get(list.size()-1).add(data);
			size++;
			return true;
		}
	}
	
	
	@Override
	public boolean contains(Object item) {
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0 ; j < list.get(i).size(); j++) {
				if(list.get(i).get(j) == item) return true;
			}
		}
		return false;
	}
	
	
	@Override
	public E remove(int index) {
		if(index < 0 || index >= this.size) throw new IndexOutOfBoundsException();
		E oldData = list.get(index / max_number).get(index % max_number);
		list.get(index / max_number).remove(index % max_number);
		size--;
		if(list.get(index / max_number).isEmpty()) {
			list.remove(index / max_number);
		}

		return oldData;
	}
	
	
	@Override
	public String toString() {
		String temp = "[";
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0 ; j < list.get(i).size(); j++) {
				temp+=(list.get(i).get(j)+" ");
			}
		}
		temp+="]";
		return temp;
	}

	
	
	

}
