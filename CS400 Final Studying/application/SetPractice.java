package application;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetPractice {


	
	public static void main(String[] args) {
		
		// HashSet
		// unordered. Uses a HashMap. Very fast
		HashSet<String> hSet = new HashSet<String>();
		hSet.add("Lord of the Rings");
		hSet.add("Harry Potter");
		hSet.add("Count of Monte Cristo");
		hSet.add("Crossroads of Twilight");
		System.out.println("HashSet: " + hSet);
		
		// TreeSet
		// Uses a Red-Black Tree. Ordered. Not so fast
		TreeSet<String> tSet = new TreeSet<String>();
		tSet.add("Lord of the Rings");
		tSet.add("Harry Potter");
		tSet.add("Count of Monte Cristo");
		tSet.add("Crossroads of Twilight");
		System.out.println("TreeSet: " + tSet);
		
		// Linked HashSet
		// Uses a hashtable and a linked list. ordered by insertion order
		LinkedHashSet<String> lHSet = new LinkedHashSet<String>();
		lHSet.add("Lord of the Rings");
		lHSet.add("Harry Potter");
		lHSet.add("Count of Monte Cristo");
		lHSet.add("Crossroads of Twilight");
		System.out.println("LinkedHashSet: " + lHSet);

	} // main()

} // class setpractice
