package application;

public class Stack<T> {
	
	private class StackNode<T> {
		private T value;
		private StackNode<T> next;
		
		// constructor
		public StackNode(T value){
			this.value = value;
			next = null;
		} // constuctor
		
		// sets next node
		public void setNext(StackNode<T> next) {
			this.next = next;
		} // setNext()
		
		// gets the next node
		public StackNode<T> getNext(){
			return next;
		} // getNext()
		
		public boolean hasNext() {
			if(next == null) {
				return false;
			}
			else {
				return true;
			}
		} //hasNext()
		
		public T getValue() {
			return value;
		} // getValue()
		
	} // private inner stackNode class
	
	// private variables of the stack class
	private StackNode<T> top;
	
	// constructor
	public Stack() {
		top = null;
	} // constructor
	
	// sets the top node
	public void setTop(StackNode<T> node) {
		top = node;
	} // setTop()
	
	// check if empty
	public boolean isEmpty() {
		if(top == null) {
			return true;
		}
		else {
			return false;
		}
	} // isEmpty()
	
	public void push(T value) {
		if(isEmpty()) {
			top = new StackNode<T>(value);
			return;
		}

		StackNode<T> current = top;
		while(current.hasNext()) {
			current = current.getNext();
		}
		// current doesn't have a next, now
		StackNode<T> newNode = new StackNode<T>(value);
		newNode.setNext(top);
		top = newNode;
	} // push()
	
	public T peek() {
		if(isEmpty()) {
			return null;
		}
		return top.getValue();
	} // peak()
	
	public T pop() {
		if(!isEmpty()) {
			T value = top.getValue();
			if(top.getNext() != null) {
				top = top.getNext();
			}
			else {
				top = null;
			}
			return value;
		}		
		else {
			return null;
		}
	} //pop()
	
} // class Stack
