import java.util.ArrayList;

// hash table using open addressing

public class BasicHashTable<T> {
	private double loadFactor = .75;
	private int tableSize;
	private int curSize;
	private Node<T> hTable [];
	
	// constructor
	public BasicHashTable() {
		tableSize = 11;
		curSize = 0;
		hTable = new Node [11];
	} // HashTable Constructor
	
	// inserts
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
				nodeInsert(copyNode.getKey(), copyNode.getValue());
			}

		} // if rehashing
		nodeInsert(key, value);
	} //insert()
	
	// searches for node of given key
	public T search(int key){
		// hash
		int hashCode = hash(key);
		// index
		int index = index(hashCode);
		
		// check where it hopefully is
		if(hTable[index].getKey() == key) {
			Node<T> node = hTable[index];
			return node.getValue();
		}
		else {
			while(hTable[index].getKey() != key) {
				if(index < tableSize) {
					index = index + 1;
				}
				else {
					index = 0;
				}
				if(index == hash(key)) {
					System.out.println("That key is not in the hashtable.");
					return null;
				}
			} // while
			return (T) hTable[index].getValue();
			
		} // else complicated

	} // search()
	
	// removes
	public void remove(int key) {
		nodeRemove(key);		
	} // remove()
	
	
	//-------------- private helper methods ----------------------------------
	
	// private getContents
	private ArrayList<Node<T>> getContents(){
		ArrayList<Node<T>> list = new ArrayList<Node<T>>();
		for(int index = 0; index < tableSize; index++) {
			if(hTable[index] == null) {
				continue;
			}
			else if(hTable[index].getKey() == -99) {
				continue;
			}
			else {
				Node<T> current = hTable[index];
				list.add(current);
			}	
		} // master for

		return list;
	} // getContents()
	
	// private node remove helper
	private void nodeRemove(int key) {
		// hash
		int hashCode = hash(key);
		// index
		int index = index(hashCode);
		
		// if at its proper spot
		if(hTable[index].getKey() == key) {
			Node<T> deletedNode = new Node<T>(-99, (T) "deleted");
			hTable[index] = deletedNode;
			setSize('-');
			return;
		} // if simple case
		
		// otherwise it's more complicated
		else {
			while(hTable[index].getKey() != key) {
				if(index < tableSize) {
					index = index + 1;
				}
				else {
					index = 0;
				}
				if(index == hash(key)) {
					System.out.println("That key is not in the hashtable.");
					return;
				}
			} // while
			Node<T> deletedNode = new Node<T>(-99, (T) "deleted");
			hTable[index] = deletedNode;
			setSize('-');
			return;
		}
		
		
	} // nodeRemove()
	
	// private inserting algorithm
	private void nodeInsert(int key, T value) {
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
		else if(hTable[index].getKey() == -99) {
			Node<T> newNode = new Node<T>(key, value);
			hTable [index] = newNode;
			setSize('+');
			return;
		}
		// otherwise array has a value there
		else {
			// use open addressing
			while(hTable[index] != null && hTable[index].getKey() != -99) {
				if(index < tableSize) {
					index = index + 1;
				}
				else {
					index = 0;
				}
			} // while
			// table is not null here
			Node<T> newNode = new Node<T>(key, value);
			hTable [index] = newNode;
			setSize('+');
			return;
		} // else
		
	} // nodeInsert()
	
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
		BasicHashTable ht = new BasicHashTable();
		ht.insert(10, "Value for 10");
		ht.insert(15, "Value for 15");
		ht.insert(100, "Value for 100");
		ht.insert(40, "Value for 40");
		ht.insert(33, "Value for 33");

		System.out.println(ht.getSize());
		
		System.out.println(ht.search(100));
		
		ht.remove(15);
		System.out.println(ht.getSize());
		
		ht.insert(22, "Value for 22");
		ht.insert(24, "Value for 24");
		ht.insert(81, "Value for 81");
		ht.insert(71, "Value for 71");
		ht.insert(36, "Value for 36");
		System.out.println(ht.getSize());
		System.out.println(ht.search(100));
		System.out.println(ht.search(71));

		
		
	} // main()

	
	
} // class BasicHashTable

