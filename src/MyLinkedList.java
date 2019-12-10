/**
 * A linked list is a linear data structure which is time efficient for adding and deleting elements.
 * Inserting at the head takes O(1) constant time, and the same can be said for the tail if the reference
 * to the tail is tracked. Accessing data takes time O(n) however and there is more space required than for an array as
 * each node requires its own object.
 * @author Andrew
 *
 * @param <T> The type of the elements to be stored in the LinkedList
 */

public class MyLinkedList<T> {
	
	private LinkedListNode<T> head;
	private int length;
	private LinkedListNode<T> tail;
	
	public MyLinkedList() {
		
		head = null;
		length = 0;
		
	}
	
	/*
	 * Insert element at index. If index doesn't exist then insert at the end.
	 */

	public void insert(T element, int index) {
	
		if(head == null || index == 0) {
			
			//Need to insert at the start
			insert(element);
			
		} else if(index >= length || index < 0) {
			
			//Need to insert at the end of the list
			insertLast(element);
			
		} else {	
				
			//Need to insert at index which is not the end
			LinkedListNode<T> currentNode = head;
			int i = 0;

			//Find the node at index - 1 (so we can change what it points to)
			while(i < index - 1) {
				currentNode = currentNode.getNext();
				i++;
			}
			
			//Save the old next list
			LinkedListNode<T> oldNext = currentNode.getNext();
			//Create the new node to be added
			LinkedListNode<T> newNode = new LinkedListNode<T>(element);
			//Put the old next list as the next list of the new node
			newNode.setNext(oldNext);
			//Add the new node
			currentNode.setNext(newNode);
			length++;
				
		}

	}

	/*
	 * Insert the element given at the start of the list
	 */
	public void insert(T element) {

		LinkedListNode<T> oldHead = head;
		head = new LinkedListNode<T>(element);
		head.setNext(oldHead);
		length++;
		
		//If there is no tail then set this to tail as this must be the only node
		if(tail == null) {
			tail = head;
		}
		
	}
	
	/*
	 * Insert the element given at the end of the list
	 */
	public void insertLast(T element) {

		if(head == null) {
			
			head = new LinkedListNode<T>(element);
			tail = head;
			length++;
			
		} else {
				
			//Add the new element at the end
			LinkedListNode<T> newNode = new LinkedListNode<T>(element);
			tail.setNext(newNode);
			tail = newNode;
			length++;
			
		}
		
	}

	/*
	 * Prints out the list using the object's toString method separated by commas
	 */
	public void printList() {
		
		if(head == null) {
			
			//Nothing in the list
			System.out.print("empty\n");
			
		} else {
		
			LinkedListNode<T> currentNode = head;
			
			//Loop through the list printing
			while(currentNode.hasNext()) {
				System.out.print(currentNode.getElement().toString() + ", ");
				currentNode = currentNode.getNext();
			}
			
			//Print the last item in the list
			System.out.print(currentNode.getElement().toString() + "\n");
			
		}
		
	}
	
	/*
	 * Represents the list as a string using the T object's toString method separated by commas
	 */
	public String toString() {
		
		if(head == null) {
			
			//Nothing in the list
			return("empty");
			
		} else {
		
			LinkedListNode<T> currentNode = head;
			String returnString = "";
			
			//Loop through the list printing
			while(currentNode.hasNext()) {
				returnString += currentNode.getElement().toString() + ", ";
				currentNode = currentNode.getNext();
			}
			
			//Print the last item in the list
			returnString += currentNode.getElement().toString();
			
			return returnString;
			
		}
		
	}

	/*
	 * Returns the number of elements in the list
	 */
	public int getLength() {
		return length;
	}

	/*
	 * Returns the element at the index given. 
	 * If element doesn't exist, throws ArrayIndexOutOfBoundsException
	 */
	public T get(int index) {

		if(head == null || index >= length || index < 0 ) {
			
			//Element does not exist
			throw new ArrayIndexOutOfBoundsException("Element at that index does not exist");
			
		} else {
			
			LinkedListNode<T> currentNode = head;
			int i = 0;
			
			//Find the node at index, or the last node
			while(i < index) {
				currentNode = currentNode.getNext();
				i++;
			}
			
			return currentNode.getElement();
			
		}
				
	}

	/*
	 * Removes the element at the index given and returns it. 
	 * If element doesn't exist, throws ArrayIndexOutOfBoundsException
	 */
	public T delete(int index) {

		if(head == null || index >= length || index < 0 ) {
			
			//Element does not exist
			throw new ArrayIndexOutOfBoundsException("Element at that index does not exist");
			
		} else {
			
			if(index == length-1) {
				
				//Delete the last element
				return deleteLast();
				
			} else if(index == 0) {
				
				//Delete the first element
				return delete();
				
			} else {
				
				//Delete an element in the middle of the list
				//Get the node before the delete node
				LinkedListNode<T> currentNode = head;
				int i = 0;
				
				while(i < index - 1) {
					currentNode = currentNode.getNext();
					i++;
				}
				
				T returnElement = currentNode.getNext().getElement();
				currentNode.setNext(currentNode.getNext().getNext());
				length--;
				
				return returnElement;
				
			}

		}

		
	}

	/*
	 * Deletes the first element in the list and returns the element.
	 * If the list is empty, throws ArrayIndexOutOfBoundsException
	 */
	public T delete() {
		
		if(head == null) {
			
			//Element does not exist
			throw new ArrayIndexOutOfBoundsException("Element at that index does not exist");
			
		} else {
			
			//Remove the head
			T returnElement = head.getElement();
			head = head.getNext();
			length--;
			
			//Just incase that was the only element
			if(head == null) {
				tail = null;
			}

			return returnElement;
			
		}
		
	}

	/*
	 * Deletes the last element in the list, and returns the element.
	 * If the list is empty, throws ArrayIndexOutOfBoundsException
	 */
	public T deleteLast() {

		if(head == null) {
			
			//Element does not exist
			throw new ArrayIndexOutOfBoundsException("Element at that index does not exist");
			
		} else if (!head.hasNext()) {
			
			//The last is also the head
			T returnElement = head.getElement();
			head = null;
			tail = null;
			length = 0;
			return returnElement;
			
		} else {
			
			//Get the second last node
			LinkedListNode<T> currentNode = head;
			int i = 0;
			
			while(i < length - 2) {
				currentNode = currentNode.getNext();
				i++;
			}
			
			T returnElement = currentNode.getNext().getElement();
			currentNode.setNext(null);
			length--;
			tail = currentNode;
			
			return returnElement;
			
		}
		
	}

}
