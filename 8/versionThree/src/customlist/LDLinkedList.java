package customlist;

import java.util.AbstractList;
import java.util.List;



/**
 *this class is extends java collection framework abstractlist and implements list interface
 * @param this is generic type
 */
public class LDLinkedList<E> extends AbstractList<E> implements List<E>{

	
	/**
	 *this is a private class to represent the internal node of a linked list
	 */
	private class Node{
		E data;
		Node next;
		Node prev;
		boolean markDeleted;
		Node(E data){
			this.data = data;
			this.markDeleted = false;
		}
	}
	
	Node head = null;
	Node tail = null;
	int nodeCount = 0;
	int deletedNodeCount=0;
	
	@Override
	public E get(int index) {
		int count = 0; 
		Node temp = head;
		while(temp!=null) {
			if(!temp.markDeleted) {
				if(count == index) {
					return temp.data;
				}
				count++;
			}
			temp = temp.next;
		}
		return null;
	}

	@Override
	public int size() {
		return nodeCount;
	}
	
	
	/**
	 * this method will remove all the nodes which are marked for deletion
	 */
	public void deleteMarked() {
		Node temp = head;
		while(temp!=null) {
			
			if(temp.markDeleted) {
				deleteNode(temp);
			}
			temp = temp.next;
		}
		deletedNodeCount = 0;
	}
	
	
	@Override
	public E remove(int index) {
		if((nodeCount-1) == deletedNodeCount) {
			deleteMarked();
		}
		if(index < 0 || index>=size()) {
			throw new IndexOutOfBoundsException();
		}
		int count = 0; 
		Node temp = head;
		while(temp!=null) {
			if(!temp.markDeleted) {
				if(count == index) {
					temp.markDeleted = true;
					deletedNodeCount++;
					nodeCount--;
					return temp.data;
				}
				count++;
			}
			temp = temp.next;
		}
		return null;
	}
	
	@Override
	public void clear() {
		nodeCount = 0;
		deletedNodeCount = 0;
	}
	
	/**
	 * this method will take a Node and remove it
	 * @param del is of type {@link Node}
	 */
	private void deleteNode(Node del)
    {
        if (head == null || del == null) {
            return;
        }
        if (head == del) {
            head = del.next;
        }
        if (del.next != null) {
            del.next.prev = del.prev;
        }
        if (del.prev != null) {
            del.prev.next = del.next;
        }
        return;
    }
	
	
	
	/**
	 * this function will not create a new node if the node is already present. 
	 * To get benefit from this we need to override the equal method of a class to compare value
	 * @param data is generic type
	 * @return return true if added else false
	 */
	private boolean activateLazyAdd(E data) {
		Node temp = head;
		while(temp != null) {
			if(temp.data.equals(data) && temp.markDeleted) {
				temp.markDeleted = false;
				nodeCount++;
				deletedNodeCount--;
				return true;
			}
			temp = temp.next;
		}
		return false;
	}
	
	
	
	@Override
	public boolean add(E data) {
		
		if(activateLazyAdd(data)) {
			return true;
		}
		try {
			 Node newNode = new Node(data);  
		        if(head == null) {  
		            head = tail = newNode;  
		            head.prev = null;  
		            tail.next = null;  
		        }  
		        else {  
		            tail.next = newNode;  
		            newNode.prev = tail;  
		            tail = newNode;  
		            tail.next = null;  
		        }  
		    nodeCount++;
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
	
	@Override
	public String toString() {
		
		String st ="";
		st += "[ ";
		Node temp = head;
		while(temp!=null) {
			if(!temp.markDeleted)
				st+=temp.data+" ";
			temp = temp.next;
		}
		st+="]";
		return st;
	}

}
