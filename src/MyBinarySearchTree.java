/**
 * A binary search tree is a branched structure made up of nodes which have 2 subnodes.
 * The left subnode is always less than and the right subnode is always greater than the
 * parent node. These trees allow for O(log(n)) insertion time and O(log(n)) look up time.
 * The algorithms can be done recursively or iteratively using stacks and queues. 
 * Recursive is neat, but more space intensive. Here we will use iterative methods.
 * 
 * @author Andrew
 *
 * @param <T>
 */

public class MyBinarySearchTree<T extends Comparable<T>> {
	
	private BSTNode<T> root;
	
	public MyBinarySearchTree() {
		root = null;
	}

	/*
	 * Insert the given element into the tree at a leaf node.
	 */
	public void insert(T element) {
		
		if(root == null) {
			
			//Tree is currently empty
			root = new BSTNode<T>(element);
			
		} else {
			
			boolean inserted = false;
			BSTNode<T> current = root;
			
			while(!inserted) {
				
				if(element.compareTo(current.getElement()) < 0) {
					
					//Element is less than current
					if(current.getLeft() == null) {
						
						//Insert the element here
						current.setLeft(new BSTNode<T>(element));
						inserted = true;
						
					} else {
						
						//Keep searching down the tree for the right spot
						current = current.getLeft();
						
					}
					
				} else if(element.compareTo(current.getElement()) > 0) {
					
					//Element is greater than current
					if(current.getRight() == null) {
						
						//Insert the element here
						current.setRight(new BSTNode<T>(element));
						inserted = true;
						
					} else {
						
						//Keep searching down the tree for the right spot
						current = current.getRight();
						
					}
					
				} else {
					
					//Element must be equal to current therefore already in the tree
					inserted = true;
					
				}
				
			}
			
		}
		
	}

	/*
	 * Print out the tree in a 2D format.
	 */
	public void prettyPrint() {
		
		//Figure out the depth of the tree
		int depth = root.computeDepth();
		
		//Compute the spacings up to depth + 1
		//Use dynamic programming
		//Spacing(x) = y(x)
		//y(x) = (sum of y(k) for k = 1..x-1) + x - 1
		int[] spacings = new int[depth + 2];
		
		//Base cases
		spacings[0] = 0;
		spacings[1] = 1;
	
		for(int i = 2; i <= depth + 1; i++) {
			spacings[i] = i - 1;
			for(int k = 0; k < i; k++) {
				spacings[i] += spacings[k];
			}
		}
		
		//Go through the tree in BFS order
		//Print each node, on each level, with appropriate spacing
		
		//Queue to hold the nodes that need visiting
		MyQueue<BSTNode<T>> toVisit = new MyQueue<BSTNode<T>>();
		toVisit.enqueue(root);
		
		//Queue to hold the corresponding level of each node in toVisit
		MyQueue<Integer> levels = new MyQueue<Integer>();
		levels.enqueue(0);
		
		//Queue to hold the branches that need drawing following this level
		//False - no branch, true - draw branch
		MyQueue<Boolean> branches = new MyQueue<Boolean>();
		
		int prevLevel = 0;
		
		//Now we need to indent the correct amount before we start
		//printing the first line
		for(int k = 0; k < spacings[depth]; k++) {
			System.out.print(" ");
		}
		
		while(prevLevel < depth) {
			
			//Dequeue the next node
			BSTNode<T> current = toVisit.dequeue();
			int currentLevel = levels.dequeue();
			
			//Check if we finished the previous level
			if(currentLevel > prevLevel) {
				
				//We have gotten to a new level and we need to draw the branches
				//coming down from the previous level before we continue
				System.out.print("\n");
				
				//Print y(depth - prevLevel - 1) lines of slashes 
				//where number of pairs of slashes is 2^prevLevel
				for(int i = 0; i < spacings[depth - prevLevel - 1]; i++) {
					
					//Print pairs of slashes representing left and right branches
					for(int j = 0; j < Math.pow(2, prevLevel); j++) {
						
						//Print left padding
						for(int k = 0; k < spacings[depth - prevLevel] - 1 - i; k++) {
							System.out.print(" ");
						}
						
						//Print left branch slash if there
						boolean nextBranch = branches.dequeue();
						if(nextBranch) {
							System.out.print("/");
						} else {
							System.out.print(" ");
						}
						branches.enqueue(nextBranch);
						
						//Print inner padding
						for(int k = 0; k < 2*i + 1; k++) {
							System.out.print(" ");
						}
						
						//Print right branch slash if there
						nextBranch = branches.dequeue();
						if(nextBranch) {
							System.out.print("\\");
						} else {
							System.out.print(" ");
						}
						branches.enqueue(nextBranch);
						
						//Print right padding, add one extra for the padding before the next pair
						for(int k = 0; k < spacings[depth - prevLevel] - i; k++) {
							System.out.print(" ");
						}
						
					}
					
					//Print the newline
					System.out.print("\n");
					
				}
				
				//Finished drawing branches, clear the branches queue.
				while(branches.hasNext()) {
					branches.dequeue();
				}
				
				//Now we need to indent the correct amount before we start
				//printing the next level of nodes.
				//The very last line doesn't need this.
				if(depth - currentLevel != 1) {
					for(int k = 0; k < spacings[depth - currentLevel]; k++) {
						System.out.print(" ");
					}
				}
				
				//Update the level
				prevLevel = currentLevel;
				
			}
			
			//Process the current node:
			//1. Print the node
			if(current != null) {
				System.out.print(current.getElement().toString());
				if(current.getElement().toString().length() == 1) {
					System.out.print(" ");
				}
			} else {
				System.out.print("  ");
			}
			
			
			//2. Pad by spacings[depth - currentLevel + 1]
			//Take off one space because we are printing items that are 2 chars
			for(int k = 0; k < spacings[depth - currentLevel + 1] - 1; k++) {
				System.out.print(" ");
			}
			
			//3. Add it's left and rights if they exist
			if(current == null) {
				
				//This node is empty
				//Enqueue 2 empty child nodes
				toVisit.enqueue(null);
				levels.enqueue(currentLevel + 1);
				branches.enqueue(false);
				
				toVisit.enqueue(null);
				levels.enqueue(currentLevel + 1);
				branches.enqueue(false);
				
			} else {
			
				if(current.getLeft() != null) {
					toVisit.enqueue(current.getLeft());
					levels.enqueue(currentLevel + 1);
					branches.enqueue(true);
				} else {
					toVisit.enqueue(null);
					levels.enqueue(currentLevel + 1);
					branches.enqueue(false);
				}
				
				if(current.getRight() != null) {
					toVisit.enqueue(current.getRight());
					levels.enqueue(currentLevel + 1);
					branches.enqueue(true);
				} else {
					toVisit.enqueue(null);
					levels.enqueue(currentLevel + 1);
					branches.enqueue(false);
				}
				
			}			
			
		}
		
		//Finish off the output with a newline
		System.out.println();
		
	}

	/*
	 * Returns a string representation of the tree in in-order format (LNR).
	 * For a binary search tree in-order arranges the elements in ascending order.
	 */
	public String toStringInOrder() {
		
		String inOrderString = "";
		
		//Use a stack to traverse the tree
		MyStack<BSTNode<T>> toVisit = new MyStack<BSTNode<T>>();
		
		//Use a second stack to keep track of seen vs not seen
		MyStack<Boolean> seenStack = new MyStack<Boolean>();
		
		//Add the root to get started
		toVisit.push(root);
		seenStack.push(false);
		
		/* Traversal algorithm:
		 * 1. Pop next item off the stack
		 * 2. If this item is seen, print it and go to the next loop.
		 * 3. If there is no left or right, print it and go to the next loop.
		 * 4. If it has a right then put this on the stack as unseen
		 * 5. Put this item on the stack as seen.
		 * 6. If it has a left, put on left as unseen.
		 */
		
		while(toVisit.hasNext()) {
			
			BSTNode<T> current = toVisit.pop();
			boolean seen = seenStack.pop();
			
			if(seen || (current.getLeft() == null && current.getRight() == null)) {
				
				inOrderString += current.getElement() + ", ";
				
			} else {
				
				if(current.getRight() != null) {
					toVisit.push(current.getRight());
					seenStack.push(false);
				}
				
				toVisit.push(current);
				seenStack.push(true);
				
				if(current.getLeft() != null) {
					toVisit.push(current.getLeft());
					seenStack.push(false);
				}
				
			}
			
			
		}
		
		//Clipping off the trailing comma and space
		inOrderString = inOrderString.substring(0, inOrderString.length()-2);
		
		return inOrderString;
		
	}

	/*
	 * Returns a string representation of the tree in pre-order i.e. NLR
	 */
	public String toStringPreOrder() {
		
		String preOrderString = "";
		
		//Use a stack to traverse the tree
		MyStack<BSTNode<T>> toVisit = new MyStack<BSTNode<T>>();
		
		//Add the root to get started
		toVisit.push(root);
		
		/* Traversal algorithm:
		 * 1. Pop next item off the stack
		 * 2. Print this item
		 * 3. Push the right if it exists
		 * 4. Push the left if it exists
		 */
		
		while(toVisit.hasNext()) {
			
			BSTNode<T> current = toVisit.pop();
		
			preOrderString += current.getElement() + ", ";
				
			if(current.getRight() != null) {
				toVisit.push(current.getRight());
			}

			if(current.getLeft() != null) {
				toVisit.push(current.getLeft());
			}	
			
		}
		
		//Clipping off the trailing comma and space
		preOrderString = preOrderString.substring(0, preOrderString.length()-2);
		
		return preOrderString;
		
	}

	/*
	 * Returns a string representation of the tree in post-order i.e. LRN
	 */
	public String toStringPostOrder() {

		String postOrderString = "";
		
		//Use a stack to traverse the tree
		MyStack<BSTNode<T>> toVisit = new MyStack<BSTNode<T>>();
		
		//Use a second stack to keep track of seen vs not seen
		MyStack<Boolean> seenStack = new MyStack<Boolean>();
		
		//Add the root to get started
		toVisit.push(root);
		seenStack.push(false);
		
		/* Traversal algorithm:
		 * 1. Pop next item off the stack
		 * 2. If this item is seen, print it and go to the next loop.
		 * 3. If there is no left or right, print it and go to the next loop.
		 * 4. Put this item on the stack as seen.
		 * 5. If it has a right then put this on the stack as unseen
		 * 6. If it has a left, put on left as unseen.
		 */
		
		while(toVisit.hasNext()) {
			
			BSTNode<T> current = toVisit.pop();
			boolean seen = seenStack.pop();
			
			if(seen || (current.getLeft() == null && current.getRight() == null)) {
				
				postOrderString += current.getElement() + ", ";
				
			} else {
				
				toVisit.push(current);
				seenStack.push(true);
				
				if(current.getRight() != null) {
					toVisit.push(current.getRight());
					seenStack.push(false);
				}
				
				if(current.getLeft() != null) {
					toVisit.push(current.getLeft());
					seenStack.push(false);
				}
				
			}
			
			
		}
		
		//Clipping off the trailing comma and space
		postOrderString = postOrderString.substring(0, postOrderString.length()-2);
		
		return postOrderString;
		
	}

	/*
	 * Returns a string representation of the tree traversed in breadth first order.
	 */
	public String toStringBFOrder() {
		
		String bfOrderString = "";
		
		//Use a queue to traverse the tree
		MyQueue<BSTNode<T>> toVisit = new MyQueue<BSTNode<T>>();
		
		//Add the root to get started
		toVisit.enqueue(root);
		
		/* Traversal algorithm:
		 * 1. Pop next item off the stack
		 * 2. Print the item
		 * 3. Enqueue left and right if they exist
		 */
		
		while(toVisit.hasNext()) {
			
			BSTNode<T> current = toVisit.dequeue();
			
			bfOrderString += current.getElement() + ", ";
			
			if(current.getLeft() != null) {
				toVisit.enqueue(current.getLeft());
			}
			
			if(current.getRight() != null) {
				toVisit.enqueue(current.getRight());
			}
			
		}
		
		//Clipping off the trailing comma and space
		bfOrderString = bfOrderString.substring(0, bfOrderString.length()-2);
		
		return bfOrderString;
		
	}

	/*
	 * Returns whether or not the given element is in the tree
	 */
	public boolean contains(T element) {

		/* Algorithm:
		 * 1. Does the current node have element? If it does then return true
		 * 2. Is current smaller than element? Continue searching left
		 * 3. Is current larger than element? Continue searching right?
		 * 4. If we need to search left/right but there is no node then our element is not in the tree.
		 */
		
		BSTNode<T> current = root;
		
		while(current != null) {
			
			if(current.getElement().equals(element)) {
				
				return true;
				
			} else if(element.compareTo(current.getElement()) < 0) {
				
				current = current.getLeft();
				
			} else {
				
				current = current.getRight();
				
			}
			
		}
		
		//If current == null then we did not find the element
		return false;
		
	}

	/*
	 * Finds the given element in the tree, if it exists, and deletes it.
	 */
	public void remove(T element) {
		
		//Find the the element, and keep track of it's parent
		BSTNode<T> parent = null;
		BSTNode<T> current = root;
		
		while(current != null) {
			
			if(current.getElement().equals(element)) {
				
				//We have found the element, do the deletion
				//The deleted element should be replaced by the right subtree
				//The left subtree should be inserted into the right subtree
				BSTNode<T> newChild = current.getRight();
				
				//If there is no right node then just put the left as the new child
				if(current.getRight() == null) {
					
					newChild = current.getLeft();
					
				} else {
					
					//Put the left subtree into the left most node of the right subtree
					BSTNode<T> mostLeft = current.getRight();
					
					//Keep iterating until mostLeft doesn't have a left child
					while(mostLeft.getLeft() != null) {
						mostLeft = mostLeft.getLeft();
					}
					
					mostLeft.setLeft(current.getLeft());
					
				}
				
				//If parent is null then root needs to change
				if(parent == null) {
					
					root = newChild;
					
				} else {
					
					//Replace the node to be deleted with the newChild
					if(parent.getLeft().getElement() == current.getElement()) {
						
						//Replace left
						parent.setLeft(newChild);
						
					} else {
						
						//Replace right
						parent.setRight(newChild);
						
					}
					
				}
				
				return;
				
			} else if(element.compareTo(current.getElement()) < 0) {
				
				parent = current;
				current = current.getLeft();
				
			} else {
				
				parent = current;
				current = current.getRight();
				
			}
			
		}
		
	}

}
