import java.util.ArrayList;

public class HashTable<T> {
	private double loadFactor = .75;
	private int tableSize;
	private int curSize;
	private Node<T> hTable [];
	
	// constructor
	public HashTable() {
		tableSize = 11;
		curSize = 0;
		hTable = new Node [11];
	} // HashTable Constructor
	

	// inserts a node
	// rehashes if needed
	public void insert(int key, T value) {
		
		// rehashing check
		if(curSize > tableSize * loadFactor) {
			System.out.println("We need to rehash this thing...");
			ArrayList<Node<T>> list = new ArrayList<Node<T>>();
			list = getContents();
			curSize = 0;
			tableSize = (tableSize * 3) +1;
			hTable = new Node [tableSize];
			for(int i = 0; i < list.size(); i++) {
				Node<T> copyNode = new Node<T>(list.get(i).getKey(), list.get(i).getValue());
				hInsert(copyNode.getKey(), copyNode.getValue());
			}

		} // if rehashing
		 
		hInsert(key, value);
		
		
	} // insert()
	
	// gets value from a given key
	public T get(int key){
		T value = pGet(key);
		return value;
	} // get()
	
	
	// removes a node
	public void remove(int key) {
		removeHelp(key);
	} // remove()
	
	
	// prints contents of hashtable
	public void print() {
		System.out.println(getContents());
		
	} // print()
	
	
	
	
	
	
	
	
	
	
	// gets contents of the hashTable
	private ArrayList<Node<T>> getContents(){
		ArrayList<Node<T>> list = new ArrayList<Node<T>>();
		for(int index = 0; index < tableSize; index++) {
			Node<T> current = hTable[index];
			if(current == null) {
				continue;
			}
			while(current.getNext() != null) {
				list.add(current);
				current = current.getNext();
			}
			list.add(current);
		} // master for

		return list;
	} // getContents()
	
	// helper getter
	private T pGet(int key){
		// hash
		int hashCode = hash(key);
		// index
		int index = index(hashCode);
		
		if(hTable[index] == null) {
			return null;
		}
		else {
			Node current = hTable[index];
			if(current.getKey() == key) {
				return (T) current.getValue();
			}
			while(current.getNext() != null) {
				if(current.getKey() == key) {
					return (T) current.getValue();
				}
				else {
					current = current.getNext();
				}
			}
			return null;
		}

	} // get()
	
	
	// private helper inserter
	private void hInsert(int key, T value) {
		// hash
		int hashCode = hash(key);
		// index
		int index = index(hashCode);
		
		// if spot is empty
		if(hTable [index] == null) {
			Node<T> newNode = new Node<T>(key, value);
			hTable [index] = newNode;
			setSize('+');
			return;
		}
		// otherwise array has a linked list there
		else {
			Node<T> current = hTable[index];
			while(current.getNext() != null) {
				current = current.getNext();
			}
			// next is null
			Node<T> newNode = new Node<T>(key, value);
			current.setNext(newNode);
			setSize('+');
			return;
		}

	} // hInsert()
	
	// helper remover
	private void removeHelp(int key) {
		// hash
		int hashCode = hash(key);
		// index
		int index = index(hashCode);
		
		// if spot is empty
		if(hTable [index] == null) {
			System.out.println("There is no node like that.");
		}
		// otherwise there is a list there
		else {
			Node current = hTable[index];
			Node previous = current;
			if(current.getKey() == key) {
				// set next
				hTable[index] = current.getNext();
				setSize('-');
				System.out.println("Deleted node.");
			}
			// otherwise look through list
			while(current.getNext() != null) {
				if(current.getKey() == key) {
					previous.setNext(current.getNext());
					setSize('-');
					System.out.println("Deleted node.");
				}
				else {
					previous = current;
					current = current.getNext();
				}
			}			
		} // else
	} // removeHelp()
	
	// private hashing algorithm
	private int hash(int key) {
		int hashCode = key * 23;
		return hashCode;
	} // hash()
	
	// private index algorithm
	private int index(int hashCode) {
		return hashCode % tableSize;
	} // index()
	
	// increments curSize
	private void setSize(char op) {
		if(op == '+') {
			curSize++;
		}
		else if(op == '-') {
			curSize--;
		}
		else {
			System.out.println("Not a valid operand for sizing.");
		}
	} // setSize()
	
	// returns current HashTable size
	public int getSize() {
		return curSize;
	} // getSize()
	
	
	public static void main(String[] args) {

		HashTable<String> ht = new HashTable<String>();
		ht.insert(5, "node value 5");
		System.out.println(ht.getSize());
		String value = ht.get(5);
		System.out.println(value);
		ht.insert(6, "node value 6");
		ht.insert(7, "node value 7");
		ht.insert(8, "node value 8");
		ht.insert(9, "node value 9");
		ht.print();
		
	} // Main ()

	
} // class HashTable
