package bstheaptree;

import java.util.ArrayList;

public class BstHeapTree<E> {
	
	private static int maxSize = 7;
	/**
	 * node class for BstHeapTree
	 *
	 */
	class Node{
		MaxHeap<E> data;
		Node left;
		Node right;
		/**
		 * constructor
		 */
		Node(){
			data = new MaxHeap<>();
			left = right = null;
		}
	}
	
	private Node head = null;
	
	/**
	 * private in the context
	 * @param node takes a Node value
	 * @param item takes a generic item
	 * @return return a Node
	 */
	private Node add(Node node , E item) {
		if(node == null) {
			Node temp = new Node();
			temp.data.add(item);
			return temp;
		}
		int index = node.data.search(item);
		if(index != -1) {
			node.data.add(item);
		}else {
			if(node.data.size() < maxSize ) {
				node.data.add(item);
				
			}else {
				if((int)item < (int)node.data.peek()) {
					node.left = add(node.left, item);
				}else {
					node.right = add(node.right, item);
				}
			}
			
		}
		
		return node;
	}
	
	
	/**
	 * to add item in the tree
	 * @param item takes a generic item
	 */
	public void add(E item) {
		head = add(head,item);
	}
	
	
	
	/**
	 * private method to remove a items occurance
	 * @param node takes a node
	 * @param item takes a generic item to remove
	 */
	private void remove(Node node , E item) {
		if(node == null) {
			return;
		}
		int index = node.data.search(item);
		if(index!=-1) {
			node.data.remove(index);
			return;
		}
		remove(node.left, item);	
		remove(node.right, item);
	}
	
	/**
	 * 
	 * @param node takes a node value
	 * @return returns node that has max value
	 */
	Node findMax(Node node) {
		Node maxNode = node;
		E max= node.data.peek();
		while(node.left!=null) {
			maxNode = node.left;
			max = node.left.data.peek();
			node = node.left;
		}
		return maxNode;
	}
	
	
	/**
	 * 
	 * @param node takes a node value
	 * @param item takes a generic item
	 * @param value takes a arraylist
	 */
	private void find(Node node,E item,ArrayList<Integer> value) {
		if(node == null) {
			return;
		}
		int index = node.data.search(item);
		if(index!=-1) {
			value.set(0, node.data.getOccurance(index));
			return;
		}else {
			find(node.left, item,value);	
			find(node.right, item,value);
		}

		
	}
	
	/**
	 * find a items occurance
	 * @param item takes a item
	 * @return returns a integer value
	 */
	public int find(E item) {
		ArrayList<Integer> count=new ArrayList<>();
		count.add(-1);
		find(head,item,count);
		return count.get(0);
		
		
	}
	
	/**
	 * deletes empty node if max heap is empty
	 * @param node takes a Node value
	 * @return returns a Node
	 */
	private Node deleteEmptyNode(Node node) {
		if(node==null) {
			return node;
		}
		if(node.data.size() == 0) {
			if(node.left ==null) {
				return node.right;
			}else if(node.right == null) {
				return node.left;
			}else {
				Node max = findMax(node.right);
				node.data.add(max.data.poll());
				node.right = deleteEmptyNode(node.right);
			}
		}else {
			node.left = deleteEmptyNode(node.left);
			node.right = deleteEmptyNode(node.right);
		}
		
		return node;
	}
	
	
	/**
	 * removes occurance of a item
	 * @param item takes a generic item
	 */
	public void remove(E item) {
		 remove(head, item);
		 head = deleteEmptyNode(head);
	}
	
	/**
	 * prints the tree in preorder head-left-right
	 * @param node
	 */
	private void preorder(Node node) {
		if(node == null)
			return;
		System.out.println(node.data);
		preorder(node.left);
		preorder(node.right);
	}
	
	/**
	 * prints the tree
	 */
	public void print() {
		preorder(head);
	}

}
