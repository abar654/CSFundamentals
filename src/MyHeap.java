/**
 * A heap is a tree like data structure which has the property that any parent node
 * is less than its children (for a min heap, or the other way round for a max heap).
 * A heap is O(1) time for getting the smallest (or largest in max heap) element,
 * O(log(n)) for deleting the that element, and O(log(n)) for inserting an element.
 * Delete and contains methods are O(n) however.
 * @author Andrew
 *
 * @param <T>
 */
public class MyHeap<T extends Comparable<T>> {
	
	private int size;
	private T[] array;
	boolean isMinHeap;

	@SuppressWarnings("unchecked")
	public MyHeap(boolean isMinHeap) {
		
		this.isMinHeap = isMinHeap;
		array = (T[]) new Object[16];
		size = 0;
		
	}

	public void push(T element) {
		// TODO Auto-generated method stub
		
		//MAKE SURE TO RESIZE WHEN THE ARRAY GETS TOO BIG!!!
		//Double the current size
		
	}

	public int getSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void delete(T element) {
		// TODO Auto-generated method stub
		
	}

	public T pop() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean contains(T element) {
		// TODO Auto-generated method stub
		return false;
	}

}
