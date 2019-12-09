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
		testList.printList(); //Should print empty
		testList.insert(3, 0);
		testList.insert(5); //Should insert at the start of the list
		testList.insert(2, 1);
		testList.insert(6, 9); //Should insert at the end of the list
		testList.printList(); //Should print: "5, 2, 3, 6"
		System.out.println("The element at index 2 is: " + testList.get(2)); //Should be 3
		System.out.println("Just deleted: " + testList.delete(0)); 
		testList.printList(); //Should print: "2, 3, 6"
		testList.insert(4, 2);
		testList.delete(testList.getLength()-1);
		testList.delete(1);
		testList.insertLast(7);
		testList.printList(); //Should print: "2, 4, 7"
		
		//Build and do very simple test for strings
		MyLinkedList<String> stringList = new MyLinkedList<String>();
		stringList.insert("is");
		stringList.insert("This", 0);
		stringList.insertLast("a");
		stringList.insert("list!", 8);
		stringList.printList();
		stringList.delete(2);
		stringList.printList();
		
		//Try illegal access
		try {
			testList.delete(testList.getLength());
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Exception found: " + e);
		}
		
		try {
			testList.get(100);
		} catch (ArrayIndexOutOfBoundsException e){
			System.out.println("Exception found: " + e);
		}		
		
	}

}
