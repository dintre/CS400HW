package application;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class myLambdaTest {

	public static void main(String[] args) {
		
		// data
		List<String> bookList = Arrays.asList("Pride and Prejudice", "Lord of the Rings", "The Sun Also Rises",
				"Crossroads of Twilight", "I a A a s c y.", "Toll the Hounds", "Garden of Eden", "Harry Potter", "The Wild Palms",
				"Iliad", "Odyssey", "Lord of the Rings", "Odyssey", "Crossroads of Twilight");		 		
		// get distinct
		List<String> list = bookList.stream()
		.distinct()
		.collect(Collectors.toList());
		
		// 1. collections.sort
		Collections.sort(list);
		System.out.println(list);
		System.out.println();

		// 2. use separate class for comparing. Class has one public method
		Comparator<String> separateClassCompare = new sortClass(); // type is comparator. sortClass is the object
		if(separateClassCompare.compare("Lord of the Rings", "Sun Also Rises") < 0) {
			System.out.println("Sun Also Rises was 'greater than' when compared.");
		}
		else if(separateClassCompare.compare("Lord of the Rings", "Sun Also Rises") > 0) {
			System.out.println("Sun Also Rises was 'less than' when compared.");
		}
		else {
			System.out.println("They both have the same number of spaces.");
		}
		System.out.println("Separate class sorting ");
		Collections.sort(list, separateClassCompare);
		System.out.println(list);
		System.out.println();
		
		// 3. Use an inner class 
		Comparator<String> innerClassCompare = new innerComparing(); // type is comparator. innerComparing is object
		int compared = innerClassCompare.compare("Lord of the Rings", "Odyssey");
		if(compared > 0) {
			System.out.println("Book 1 had more 'thes'");
		}
		else if(compared < 0) {
			System.out.println("Book 2 had more 'thes'");
		}
		else {
			System.out.println("Same occurrences.");
		}
		System.out.println("Inner class sorting ");

		Collections.sort(list, innerClassCompare);
		System.out.println(list);
		
		System.out.println();

		// 4. Use an anonymous inner class. i.e. class is not named
		Comparator<String> anonCompare = new Comparator<String>() { // interface is the type. Do use () when instantiating
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			} //compare()
		}; // end of anonymous inner class
		int lengthVal = anonCompare.compare("Lord of the Rings", "The Beautiful and Damned");
		if(lengthVal > 0) {
			System.out.println("Book 1 longer");
		}
		else if(lengthVal < 0) {
			System.out.println("Book 2 longer");
		}
		else {
			System.out.println("Same length.");
		}
		System.out.println("Anonymous inner class sorting ");
		Collections.sort(list, anonCompare);
		System.out.println(list);
		
		System.out.println();

		// 5. Use Lambda function. This works because Comparable is a functional interface - it takes inputs and returns
		Comparator<String> lambdaComparing = (String s1, String s2) -> (s1.length() - s2.length());
		// end of lambda function
		System.out.println("Lamda function: ");
		System.out.println(lambdaComparing.compare("Lord of the Rings", "Iliad"));
		Collections.sort(list, lambdaComparing);
		System.out.println(list);
		
		System.out.println();
		
		// 6. Use Lambda function as a parameter
		Collections.sort(list, (string1, string2) -> string1.split(" ").length - string2.split(" ").length);
		System.out.println("Lambda as paramter");
		System.out.println(list);
		
		
		
		
	} // main
	
	// inner class
	static class innerComparing implements Comparator<String>{
		@Override
		public int compare(String string1, String string2) {
			return (comp(string1) - comp(string2));
		} // compare()
		
		private int comp(String value) {
			value.toLowerCase();
			String splitName [] = value.split(" ");
			int theCount = 0;
			for(int i = 0; i < splitName.length; i++) {
				if(splitName[i].equals("the")) {
					theCount++;
				}
			}
			return theCount;
		} // comp()
		
	} // inner class innerComparing

} // class myLamdaTest
