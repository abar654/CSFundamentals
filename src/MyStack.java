/**
 * A stack is a linear data structure that follows the last in first out principle.
 * It has a very simply interface. This implementation uses linked lists but the same
 * can be achieved with arrays with a little more effort. Push and pop are both O(1)
 * time complexity.
 * @author Andrew
 *
 * @param <T>
 */

public class MyStack<T> {
	
	private MyLinkedList<T> stack;
	
	public MyStack() {
		stack = new MyLinkedList<T>();
	}

	/*
	 * Add the element to the top of the stack.
	 */
	public void push(T element) {
		
		stack.insert(element);
		
	}
	
	/*
	 * Remove an element from the top of the stack.
	 * If the stack is empty this will throw ArrayIndexOutOfBoundsException
	 */
	public T pop() {

		return stack.delete();
		
	}
	
	/*
	 * Returns the string implementation of the stack.
	 */
	public String toString() {
		
		return stack.toString();
		
	}

	/*
	 * Returns true if there is an item on the stack
	 */
	public boolean hasNext() {

		return stack.getLength() > 0;
		
	}

}
