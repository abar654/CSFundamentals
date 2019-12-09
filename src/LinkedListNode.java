/**
 * These are the nodes that make up MyLinkedList. The class is simple and self explanatory.
 * @author Andrew
 *
 * @param <T>
 */
public class LinkedListNode<T> {
	
	private T element;
	private LinkedListNode<T> next;

	public LinkedListNode(T element) {
		this.element = element;
		next = null;
	}

	public void setNext(LinkedListNode<T> node) {
		next = node;		
	}

	public boolean hasNext() {
		return next != null;
	}

	public LinkedListNode<T> getNext() {
		return next;
	}

	public T getElement() {
		return element;
	}

}
