/**
 * A collection of fundamental CS data structures and algorithms that I have
 * built from scratch to practice.
 * - Linked Lists 
 * - Stacks and Queues
 * - Binary Trees
 * - Trees
 * - Hash Tables
 * - Heaps
 * - Graphs
 * - BFS
 * - DFS
 * - Binary Search
 * - Insertion sort
 * - Bubble sort
 * - Merge Sort
 * - Quick Sort
 * - Bucket sort
 * @author Andrew
 *
 */
public class FundamentalsTester {
	
	public static void main(String[] args) {
		
		testLinkedList();
		
	}
	
	private static void testLinkedList() {
		
		//Build and test a LinkedList with Integers
		MyLinkedList<Integer> testList = new MyLinkedList<Integer>();
		assert testList.toString().equals("empty");
		
		testList.insert(3, 0);
		testList.insert(5); //Should insert at the start of the list
		testList.insert(2, 1);
		testList.insert(6, 9); //Should insert at the end of the list
		assert testList.toString().equals("5, 2, 3, 6");
		
		assert testList.get(2) == 3;
		
		int result = testList.delete(0);
		assert result == 5;
		
		assert testList.toString().equals("2, 3, 6");
		
		testList.insert(4, 2);
		testList.delete(testList.getLength()-1);
		testList.delete(1);
		testList.insertLast(7);
		assert testList.toString().equals("2, 4, 7");
		
		//Build and do very simple test for strings
		MyLinkedList<String> stringList = new MyLinkedList<String>();
		stringList.insert("is");
		stringList.insert("This", 0);
		stringList.insertLast("a");
		stringList.insert("list!", 8);
		assert stringList.toString().equals("This, is, a, list!");
		stringList.delete(2);
		assert stringList.toString().equals("This, is, list!");
		
		//Try illegal access
		try {
			testList.delete(testList.getLength());
			assert false; //Shouldn't reach this line
		} catch (ArrayIndexOutOfBoundsException e){
		}
		
		try {
			testList.get(100);
			assert false; //Shouldn't reach this line
		} catch (ArrayIndexOutOfBoundsException e){
		}
		
		System.out.println("Linked list tests passed.");
		
	}

}
