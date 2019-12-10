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
		
	}

	public String toStringInOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toStringPreOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toStringPostOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toStringBFOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean contains(T element) {
		// TODO Auto-generated method stub
		return false;
	}

	public void remove(T element) {
		// TODO Auto-generated method stub
		
	}

}
