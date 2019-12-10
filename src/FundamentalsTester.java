/**
 * A collection of fundamental CS data structures and algorithms that I have
 * built from scratch to practice.
 * - Linked Lists -- COMPLETE
 * - Stacks and Queues -- COMPLETE
 * - Binary Trees
 * - Hash Tables
 * - Heaps
 * - Graphs
 * - BFS
 * - DFS
 * - A*
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
		testStacksAndQueues();
		testBinaryTree();
		
	}
	
	private static void testBinaryTree() {
		
		//Build a binary search tree of Integers
		MyBinarySearchTree<Integer> testTree = new MyBinarySearchTree<Integer>();
		testTree.insert(8);
		testTree.insert(3);
		testTree.insert(10);
		testTree.insert(1);
		testTree.insert(6);
		testTree.insert(14);
		testTree.insert(4);
		testTree.insert(7);
		testTree.insert(13);
		
		//Pretty print the tree
		testTree.prettyPrint();
		
		testTree.insert(-3);
		testTree.insert(9);
		testTree.insert(17);
		
		//Pretty print the tree
		testTree.prettyPrint();
		
		//Create string in pre-, in-, post-, bf- order
		assert testTree.toStringInOrder().equals("1, 3, 4, 6, 7, 8, 10, 13, 14"); //LNR, stack
		assert testTree.toStringPreOrder().equals("8, 3, 1, 6, 4, 7, 10, 14, 13"); //NLR, stack
		assert testTree.toStringPostOrder().equals("1, 4, 7, 6, 3, 13, 14, 10, 8"); //LRN, stack
		assert testTree.toStringBFOrder().equals("8, 3, 10, 1, 6, 14, 4, 7, 13"); //Queue
		
		//Check if an item is in the tree
		assert testTree.contains(10);
		assert !testTree.contains(100);
		
		//Remove an item from the tree
		testTree.remove(10);
		assert !testTree.contains(10);
		
		//Pretty print the tree again
		testTree.prettyPrint();		
		
	}

	private static void testStacksAndQueues() {
		
		//Build and test a Stack with Integers
		MyStack<Integer> testStack = new MyStack<Integer>();
		testStack.push(1);
		testStack.push(2);
		testStack.push(3);
		assert testStack.toString().equals("3, 2, 1");
		
		assert testStack.pop() == 3;
		assert testStack.toString().equals("2, 1");
		
		assert testStack.pop() == 2;
		assert testStack.pop() == 1;
		assert testStack.toString().equals("empty");
		assert !testStack.hasNext();
		
		//Try illegal access
		try {
			testStack.pop();
			assert false; //Shouldn't reach this line
		} catch (ArrayIndexOutOfBoundsException e){
		}
		
		//Build and test a Queue with integers
		MyQueue<Integer> testQueue = new MyQueue<Integer>();
		testQueue.enqueue(1);
		testQueue.enqueue(2);
		testQueue.enqueue(3);
		assert testQueue.toString().equals("1, 2, 3");
		
		assert testQueue.dequeue() == 1;
		assert testQueue.toString().equals("2, 3");
		
		assert testQueue.dequeue() == 2;
		assert testQueue.dequeue() == 3;
		assert testQueue.toString().equals("empty");
		assert !testQueue.hasNext();
		
		//Try illegal access
		try {
			testQueue.dequeue();
			assert false; //Shouldn't reach this line
		} catch (ArrayIndexOutOfBoundsException e){
		}
		
		System.out.println("Stack and Queue tests passed.");
		
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
