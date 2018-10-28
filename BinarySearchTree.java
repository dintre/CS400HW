
import java.util.ArrayList;

public class BinarySearchTree<T> {

	// instance variables
	private Node<T> root;
	
	// constructor
	public BinarySearchTree() {
		setRoot(null);
	} // constructor()
	
	// adds node to tree
	public void insert(int key, T value) {		
		setRoot(nodeInsert(key, value, getRoot()));

	} // insert()
	
	// removes node from tree
	public Node<T> remove(int key) {
		return nodeRemove(key, root);
		
	} // remove()
	
	// gets a node (without removing)
	public Node<T> get(int key){
		return nodeGet(key, root);
	} // get()
	
	
	// gets size of BST
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
			
			if(leftHeight >= rightHeight) {
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
	// inorder traversal
	private ArrayList<T> traverse(Node<T> current, ArrayList<T> list) {
		if(current != null) {
			traverse(current.getLeft(), list);
			list.add(current.getValue());
			traverse(current.getRight(), list);
			return list;
		}
		return null;
	} // traverse()
	
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
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {

		BinarySearchTree<String> BST = new BinarySearchTree<String>();
				
		try {
			BST.insert(30, "Value of 30");
			BST.insert(20, "Value of 20");
			BST.insert(22, "Value of 22");
			BST.insert(55, "Value of 55");
			System.out.println("Size is " + BST.checkSize());
			System.out.println();
			BST.printSideways();
			System.out.println();
			System.out.println();

			System.out.println("This is the value of key 20: " + BST.get(20).getValue());
			System.out.println();
			BST.printAll();

			
			
			
			
			
			
			
			
			
			
			
			
			
			
		} // try
				
		catch (Exception e) {
			System.out.println("Caught an exception in ");
		} // catch	
		
		
		
		
		
		
	} // main()
	
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

	public Node<T> getRoot() {
		return root;
	}

	public void setRoot(Node<T> root) {
		this.root = root;
	}

} // class BinarySearchTree

