/**
 * This is the MyLinkedList class for TCSS 342C Assignment 1, Evolved Names.
 */

package hw3;

import java.util.Iterator;

/**
 * Represents a Singly Linked List object.
 * 
 * @author leejos5
 * @version TCSS 342C Spr2021
 */
public class MyLinkedList<Type extends Comparable<Type>> {
	
	/**
	 * Inner Node class for managing data of a Linked List.
	 * 
	 * @author leejos5
	 * @version TCSS 342C Spr2021
	 */
	private class Node {
		
		/**
		 * Item stored by the node.
		 */
		private Type item;
		
		/**
		 * Reference to the next node.
		 */
		private Node next;
		
		/**
		 * Constructor for the Node object.
		 * @param item the item to be stored by the node.
		 */
		public Node(Type item) {
			this.item = item;
			this.next = null;
		}
		
		/**
		 * Returns a String representation of the node.
		 * @return the String.
		 */
		public String toString() {
			return item.toString();
		}
	}
	
	/**
	 * The first Node of the list.
	 */
	private Node first;
	
	/**
	 * The last node of the list.
	 */
	private Node last;
	
	/**
	 * The size of the list.
	 */
	private int size;
	
	private int comparisons;
	
	/**
	 * Constructor for the MyLinkedList object.
	 */
	public MyLinkedList() {
		this.first = null;
		this.last = null;
	}
		
	/**
	 * Adds an item as a node to the end of the list.
	 * @param item the item to be added.
	 */
	public void add(Type item) { 
		addEnd(item);
	}
	
	/**
	 * Adds an item to the specified index of the list.
	 * @param item the item to be added.
	 * @param index the index to add the item.
	 */
	public void add(Type item, int index) {
		if (index == 0) {
			addFront(item);
		} else if (index == this.size()) {
			addEnd(item);
		} else {
			Node newNode = new Node(item);
			Node temp = first;
			for (int i = 0; i < index - 1; i++) {
				temp = temp.next;
			}
			newNode.next = temp.next;
			temp.next = newNode;
			size++;
		}
	}
	
	/**
	 * Helper method to add an item to the front of the list.
	 * @param item the item to be added.
	 */
	private void addFront(Type item) {
		Node newNode = new Node(item);
		newNode.next = first;
		first = newNode;
		if (last == null) {
			last = newNode;
		}
		size++;
	}
	
	/**
	 * Helper method to add an item to the end of the list.
	 * @param item the item to be added.
	 */
	private void addEnd(Type item) {
		Node newNode = new Node(item);
		if (first == null) {
			first = newNode;
			last = newNode;
		} else {
			last.next = newNode;
			last = last.next;
		}
		size++;
	}
	
	/**
	 * Removes a node from the given index.
	 * @param index the position of the node to be removed.
	 */
	public void remove(int index) {
		if (index == 0) {
			removeFront();
		} else if (index == size() - 1) {
			removeEnd();
		} else {
			Node temp = first;
			for (int i = 0; i < index - 1; i++) {
				temp = temp.next;
			}
			temp.next = temp.next.next;
			size--;
		}
	}
	
	/**
	 * Helper method to remove the node at the front of the list.
	 */
	private void removeFront() {
		first = first.next;
		if (this.size() <= 1) {
			last = first;
		}
		size--;
	}
	
	/**
	 * Helper method to remove the node at the end of the list.
	 */
	private void removeEnd() {
		Node temp = first;
		for (int i = 0; i < this.size() - 2; i++) {
			temp = temp.next;
		}
		temp.next = null;
		last = temp;
		size--;
	}

	/**
	 * Gets the item stored by the node at the given index.
	 * @param index the position of the node to get the item from.
	 * @return the item stored.
	 */
	public Type get(int index) {
		Node temp = first;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp.item;
	}
	
	/**
	 * Returns the node and all of its children.
	 * @return MyLinkedList<Type> a list of the node and its children.
	 */
	public MyLinkedList<Type> getAll() {
		MyLinkedList<Type> result = new MyLinkedList<Type>();
		Node temp = first;
		while (temp != null) {
			result.add(temp.item);
			temp = temp.next;
		}
		return result;
	}
	
	/**
	 * Sets the node at the given index to have the given item.
	 * @param index the index of the node.
	 * @param item the item to be stored at the node.
	 */
	public void set(int index, Type item) {
		Node temp = first;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		temp.item = item;	
	}
	
	/**
	 * Returns true if the list contains the item. False otherwise.
	 * @param item the item to be checked for.
	 * @return boolean
	 */
	public boolean contains(Type item) {
	    Node temp = first;
	    boolean inList = false;
	    while (!inList && temp != null) {
	        if (temp.item.equals(item)) {
	            inList = true;
	        } else {
	        temp = temp.next;
	        }
	        comparisons++;
	    }
	    return inList;
	}
	
	public int getComparisons() {
	    return comparisons;
	}
	
	/**
	 * Compares the data with the other list theList.
	 * @param theList the other list to be compared to.
	 * @return int the number of different values of the lists.
	 */
	public int compare(MyLinkedList<Type> theList) {
		int counter = 0;
		Node tempX = this.first;
		Node tempY = theList.first;
		while(tempX != null && tempY != null) {
			if(tempX.item != tempY.item) {
				counter++;
			}
			tempX = tempX.next;
			tempY = tempY.next;
		}
		return counter;
	}
	
	/**
	 * Creates an iterator object.
	 * @return the iterator.
	 */
	public Iterator<Type> iterator() {
		return new MyLinkedListIterator();
	}
	
	/**
	 * Inner class to define an Iterator object.
	 * 
	 * @author joshu
	 * @version TCSS 342C Spr2021
	 */
	private class MyLinkedListIterator implements Iterator<Type> {
		
		/**
		 * The current node of the iterator.
		 */
		private Node temp;
		
		/**
		 * Constructor for the iterator.
		 */
		private MyLinkedListIterator() {
			temp = first;
		}
		
		@Override
		/**
		 * Returns true if the next item of the iterator is not null. False otherwise.
		 * @return the boolean value.
		 */
		public boolean hasNext() {
			if (temp != null) {
				return true;	
			}
			return false;
		}
		
		/**
		 * Iterates to the next item of the iterator.
		 * @return the next item.
		 */
		public Type next() {
			Type nextNode = temp.item;
			temp = temp.next;
			return nextNode;
		}		
	}
	
	/**
	 * Returns the size of the list.
	 * @return the size.
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * Sorts the list in ascending order.
	 */
	public void sort() {
		Node outerLoop = first;
		boolean inversion = false;
		while (outerLoop.next != null) {
			Node innerLoop = first;
			while (innerLoop.next != null) {
				if (innerLoop.item.compareTo(innerLoop.next.item) > 0) {
					inversion = true;
					Type tempItem = innerLoop.item;
					innerLoop.item = innerLoop.next.item;
					innerLoop.next.item = tempItem;
				}
				innerLoop = innerLoop.next;
			}
			if (!inversion) {
				break;
			}
			outerLoop = outerLoop.next;
		}
	}	
	
	/**
	 * Returns a string representation of the list.
	 * @return String the string.
	 */
	public String toString() {
		String result = "";
		if (first != null) {
			result = first.toString();
			Node temp = first;
			while (temp.next != null) {
				temp = temp.next;
				result += temp.toString();
			}
		}
		return result;
	}	
}
