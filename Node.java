
public class Node<T> {

	// instance variables
	private int key;
	private T value;
	private Node<T> next;
	private int height;
	private Node<T> right;
	private Node<T> left;
	
	//constructor
	public Node(int key, T value) {
		this.key = key;
		this.value = value;
	} // constructor()
	
	// Getters and Setters
	public void setKey(int key) {
		this.key = key;
	} // setKey()
	
	public void setValue(T value) {
		this.value = value;
	} // setValue()
	
	public int getKey() {
		return key;
	} // getKey()
	
	public T getValue() {
		return value;
	} // getValue()
	
	public void setNext(Node<T> nextNode) {
		next = nextNode;
	} // setNext()
	
	public Node<T> getNext(){
		return next;
	} // getNext()
	
	public int getHeight() {
		return height;
	} // getHeight()
	
	public void setHeight(int height) {
		this.height = height;
	} // setHeight()
	
	public Node<T> getRight(){
		return right;
	} // getRight()
	
	public Node<T> getLeft(){
		return left;
	} // getLeft()
	
	public void setRight(Node<T> right){
		this.right = right;
	} // setRight()
	
	public void setLeft(Node<T> left) {
		this.left = left;
	} // setLeft()
	
	
	
	
} // Node Class

