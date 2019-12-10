/**
 * The nodes which make up the Binary Search Tree.
 * @author Andrew
 *
 * @param <T>
 */
public class BSTNode<T> {
	
	private T element;
	private BSTNode<T> left;
	private BSTNode<T> right;

	public BSTNode(T element) {
		this.element = element;
		left = null;
		right = null;
	}

	public T getElement() {
		return element;
	}

	public BSTNode<T> getLeft() {
		return left;
	}

	public void setLeft(BSTNode<T> bstNode) {
		left = bstNode;		
	}

	public BSTNode<T> getRight() {
		return right;
	}

	public void setRight(BSTNode<T> bstNode) {
		right = bstNode;		
	}

	public int computeDepth() {
		
		//Compute depth of left subtree
		int leftDepth = 0;
		if(left != null) {
			leftDepth = left.computeDepth();
		}
		
		//Compute depth of right subtree
		int rightDepth = 0;
		if(right != null) {
			rightDepth = right.computeDepth();
		}
		
		int maxDepth = leftDepth;
		if(rightDepth > leftDepth) {
			maxDepth = rightDepth;
		}
		
		//Return max subtree depth + 1 for current level.
		return maxDepth + 1;
		
	}

}
