import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

public class AVLTree<T> {

	// instance variables
	private Node<T> root;
	
	// constructor
	public AVLTree() {
		setRoot(null);
	} // constructor()
	
	// adds node to tree
	public void insert(int key, T value) {		
		setRoot(nodeInsert(key, value, getRoot()));

	} // insert()
	
	// removes node from tree
	public void remove(int key) {
		root = nodeRemove(key, root);
		System.out.println("Deleted node.");
		
	} // remove()
	
	// gets a node (without removing)
	public Node<T> get(int key){
		return nodeGet(key, root);
	} // get()
	
	
	// gets size of AVLTree
	public int checkSize() {
		ArrayList<T> list = new ArrayList<T>();
		list = traverse(root, list);
		return list.size();

	} // checkSize()
	
	
	// gets height of a tree from a given node
	public int checkHeight(Node<T> current) {
		// if this node is empty, done
		if(current == null) {
			return 0;
		}
		// otherwise, recursive checks
		else {
			int leftHeight = checkHeight(current.getLeft());
			int rightHeight = checkHeight(current.getRight());
			
			if(leftHeight > rightHeight) {
				return leftHeight + 1;
			}
			else {
				return rightHeight + 1;
			}
		}

	} // checkHeight()
	
	// prints out all values in the BST
	public void printAll() {
		ArrayList<T> list = new ArrayList<T>();
		System.out.println("Contents of the tree: ");
		System.out.println(traverse(root, list));

	} // printAll()
	
	// checking for if tree is balanced
	public boolean checkForBalancedTree() {
		if(root == null) {
			return true;
		}
		else {
			return checkForBalance(root);
		}
		
		
	} // checkForBalancedTree()
	
	// ------------------------ private helper methods -------------------------- //
	
	// helper inserter
	private Node<T> nodeInsert(int key, T value, Node<T> current) {
		// if tree is empty
		if(current == null) {
			current = new Node<T>(key, value);
			current.setHeight(checkHeight(current));
			return current;
		}
		// recursive calls
		// if current is smaller than the new key
		else if(current.getKey() < key) {
			// descend right
			current.setRight(nodeInsert(key, value, current.getRight()));
		}
		// otherwise if current is larger than the new key
		else if(current.getKey() > key) {
			// descend left
			current.setLeft(nodeInsert(key, value, current.getLeft()));
		}
		else {
			System.out.println("Duplicate key. Node will not be added to the tree.");
			return null;
		}
		
		// ------------ AVL Balancing ------------
		// if current is not balanced, need to do some rotations
		if(!checkForBalance(current)) {
			int leftChildHeight = checkHeight(current.getLeft());
			int rightChildHeight = checkHeight(current.getRight());
			int balanceNum = rightChildHeight - leftChildHeight;
			// ---- rotations ----
			// if right-right case
			if(balanceNum > 1 && key > current.getRight().getKey()) {
				return rotateLeft(current);
			}
			
			// if left - left case
			if(balanceNum < -1 && key < current.getLeft().getKey()) {
				return rotateRight(current);
			}
			
			// if right-left case
			if(balanceNum > 1 && key < current.getRight().getKey()) {
				current.setRight(rotateRight(current.getRight()));
				return rotateLeft(current);
			}
			
			// if left-right case
			if(balanceNum < -1 && key > current.getLeft().getKey()) {
				current.setLeft(rotateLeft(current.getLeft()));
				return rotateRight(current);
			}
			
		} // if not balanced

		return current;
	} // nodeInsert()
	
	// private node remove helper
	private Node<T> nodeRemove(int key, Node<T> current) {
		if(current == null) {
			System.out.println("That key is not in the tree.");
			return current;
		}

		// if current is smaller than the new key
		else if(current.getKey() < key) {
			// descend right
			current.setRight(nodeRemove(key, current.getRight()));
		}
		
		// otherwise if current is larger than the new key
		else if(current.getKey() > key) {
			// descend left
			current.setLeft(nodeRemove(key, current.getLeft()));
		}
		
		// otherwise the keys are equal
		else {
			// handle fixing children, if they exist
			if(current.getLeft() == null) {
				return current.getRight();
			}
			else if(current.getRight() == null) {
				return current.getLeft();
			}
			// otherwise there are two children because neither get was null
			else {
				current = getMinimumNode(current.getRight());
				current.setRight(nodeRemove(current.getKey(), current.getRight()));
			}
			
		}
		// ------------ AVL Balancing ------------
		// if current is not balanced, need to do some rotations
		if(!checkForBalance(current)) {
			int leftChildHeight = checkHeight(current.getLeft());
			int rightChildHeight = checkHeight(current.getRight());
			int balanceNum = rightChildHeight - leftChildHeight;
			// ---- rotations ----
			// if right-right case
			if(balanceNum > 1 && key > current.getRight().getKey()) {
				return rotateLeft(current);
			}
			
			// if left - left case
			if(balanceNum < -1 && key < current.getLeft().getKey()) {
				return rotateRight(current);
			}
			
			// if right-left case
			if(balanceNum > 1 && key < current.getRight().getKey()) {
				current.setRight(rotateRight(current.getRight()));
				return rotateLeft(current);
			}
			
			// if left-right case
			if(balanceNum < -1 && key > current.getLeft().getKey()) {
				current.setLeft(rotateLeft(current.getLeft()));
				return rotateRight(current);
			}
			
			} // if not balanced			
		return current;
		
	} // nodeRemove()
	
	// private getter helper method
	private Node<T> nodeGet(int key, Node<T> current){
		// if this is the right node, return it
		while(current != null) {
			if(current.getKey() == key) {
				return current;
			}
			// otherwise search deeper
			else {
				if(current.getKey() > key) {
					current = current.getLeft();
				}
				else {
					current = current.getRight();
				}
			}
		} // while
		System.out.println("That is not a node in the tree.");
		return null;
	} // nodeGet()
	
	// helper printer/traversing method
	private ArrayList<T> traverse(Node<T> current, ArrayList<T> list) {
		if(current != null) {
			traverse(current.getLeft(), list);
			list.add(current.getValue());
			traverse(current.getRight(), list);
			return list;
		}
		return null;
	} // traverse()	
	
	// rotate node right
	private Node<T> rotateRight(Node<T> current){
		Node<T> temp = current.getLeft();
		current.setLeft(temp.getRight());
		temp.setRight(current);
		
		current.setHeight(current.getHeight());
		
		return temp;
		
	} // rotateRight()
	
	// rotate node left
	private Node<T> rotateLeft(Node<T> current){
		Node<T> temp = current.getRight();
		current.setRight(temp.getLeft());
		temp.setLeft(current);
		
		current.setHeight(current.getHeight());
		
		return temp;
	} // rotateLeft()
	
	// checking for if tree is balanced
	private boolean checkForBalance(Node<T> current) {
		if(current == null) {
			return true;
		}
		int leftHeight = checkHeight(current.getLeft());
		int rightHeight = checkHeight(current.getRight());
		
		if( (Math.abs(rightHeight - leftHeight) < 2) &&
				checkForBalance(current.getLeft() ) &&
				checkForBalance(current.getRight() ) ) {
			return true;
		}
		else {
			return false;
		}

	} // checkForBalance()
	
	// finds the minimum node under and including this node in
	// order to bubble it up
	private Node<T> getMinimumNode(Node<T> current){
		Node<T> minimumNode = new Node<T>(current.getKey(), current.getValue());
		while(current.getLeft() != null) {
			if(current.getKey() < minimumNode.getKey()) {
				minimumNode = current;
			}
			current=current.getLeft();
		}
		
		return minimumNode; 
	} // getMinimumNode()
	
	// helper to get max integer
	private int getMax(int a, int b) {
		if(a > b) {
			return a;
		}
		else {
			return b;
		}
		
	} // getMax()
	
	// helper check size
	private int checkTheSize(Node<T> current) {
		if(current == null) {
			return 0;
		}
		else {
			return 1 + checkTheSize(current.getLeft()) + checkTheSize(current.getRight());
		}
		
	} // checkTheSize()
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    public void printSideways() {
		recursivePrintSideways(root, "");
	}

	private void recursivePrintSideways(Node<T> current, String indent) {
		if (current != null) {
			recursivePrintSideways(current.getRight(), indent + "    ");
			System.out.println(indent + current.getKey());
			recursivePrintSideways(current.getLeft(), indent + "    ");
		}

	}
	
	
	
	public static void main(String[] args) {

		String testName = "test06_AVLInsertAndDelete";
		AVLTree<String> AVL = new AVLTree<String>();
		
		//try {
			AVL.insert(40, "Value for key 40");
			AVL.insert(50, "Value for key 50");
			AVL.insert(30, "Value for key 30");
			AVL.insert(25, "Value for key 25");
			AVL.insert(32, "Value for key 32");
			AVL.insert(12, "Value for key 12");

			AVL.remove(25);
		 // catch	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	} // Main()

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}
	
	
} // class AVLTree

