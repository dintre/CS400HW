package application;

import java.util.Comparator;

public class sortClass implements Comparator<String> {
	@Override
	public int compare(String string1, String string2) {
		return (makeInOrder(string1) - makeInOrder(string2));
		
	} // compare()
	
	
	private int makeInOrder(String value) {
		String spaceValue [] = value.split(" ");
		return spaceValue.length;

	} // makeInOrder()
	
	
} // sortClass
