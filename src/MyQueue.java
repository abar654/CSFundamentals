/**
 * A queue is a simple linear data structure which follows the first in first out protocol.
 * The time complexity for enqueue and dequeue are both O(1) with this implementation.
 * @author Andrew
 *
 * @param <T>
 */
public class MyQueue<T> {
	
	private MyLinkedList<T> queue;
	
	public MyQueue() {
		
		queue = new MyLinkedList<T>();
		
	}

	/*
	 * Inserts an item to the end of the queue;
	 */
	public void enqueue(T element) {
		
		queue.insertLast(element);
		
	}

	/*
	 * Removes the first item in the queue and returns it.
	 */
	public T dequeue() {

		return queue.delete();
		
	}
	
	/*
	 * Returns the string implementation of the queue.
	 */
	public String toString() {
		
		return queue.toString();
		
	}

	/*
	 * Returns true if the Queue contains an element.
	 */
	public boolean hasNext() {
		
		return queue.getLength() > 0;
		
	}
	
}
