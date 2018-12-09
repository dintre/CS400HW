import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.*;

public class StreamsPractice {
		
	public static void main(String[] args) {
	List<String> words = Arrays.asList("the", "Quick", "Brown", "the", "THE",
			"fox", "jumped", "jUmped", "over", "the", "lAzy", "dog");
	
	// collector is a list
	List<String> result = words.stream() // performs all of the below modifiers on the words in result
				.map(thing -> thing.toLowerCase())
				.sorted()
				.distinct()
				.filter(word -> word.length() > 3) // this is using a lambda expression
				.limit(5)
				.collect(Collectors.toList()); // type of collector must match result type
	System.out.println(result);
	

	// collector is a for/each iterator
	words.stream()	
			.map(x -> x.toLowerCase())
			.filter(n -> n.contains("e"))
			.forEach(thing -> {
					for (int i=0; i < thing.length(); i++){ 
						if (thing.charAt(i)=='e') System.out.print("e ");
						else System.out.print("_ ");
					}// for
					System.out.println();
				});//forEach collector

	 
	 // collector is an integer
	long counter = words.stream()	
				.map(x -> x.toLowerCase())
				.filter(t -> t.contains("o"))
				.count();
	 System.out.println(counter + " items contain o");
	 
	 
	 List<Student> students = Arrays.asList(
			 new Student("Stevie", 	10, Level.K12),
			 new Student("Meghan", 	21, Level.UNDERGRAD),
			 new Student("Josh", 	18, Level.UNDERGRAD),
			 new Student("Pratham", 25, Level.GRADUATE),
			 new Student("Alice", 	28, Level.CAPSTONE),
			 new Student("Sam", 	12, Level.K12),
			 new Student("Andy", 	25, Level.GRADUATE),
			 new Student("Sam", 	12, Level.K12)	// duplicate
			 );
	 
	 // make 3 different streams, with different collectors			
	 //ages. no dups		

	 
	 List<Integer> distinctAges = students.stream()
	 		.map(s -> s.getAge())
	 		.distinct()
	 		.collect(Collectors.toList());
	 System.out.println(distinctAges);
	 
	 List<Level> studentLevels = students.stream()
			 .map(student -> student.getLevel())
			 //.distinct()
			 .collect(Collectors.toList());
	 System.out.println("Student levels at our school: " + studentLevels);
	 
	 
	 // count of students who are older than 20
	 Long oldStudents = students.stream()
			 .filter(s -> s.getAge() > 20).count();
			 System.out.println(oldStudents + " students older than 20.");
	 
	// -------------------- my practice outside of class -------------------------------
	System.out.println();
	System.out.println();
	System.out.println();
	
	List<String> bookList = Arrays.asList("Pride and Prejudice", "Lord of the Rings", "The Sun Also Rises", "Crossroads of Twilight", "Toll the Hounds", "Garden of Eden",
			"Harry Potter", "The Wild Palms", "Iliad", "Odyssey", "Lord of the Rings", "Odyssey", "Crossroads of Twilight");		 
			 
	List<String> shortTitles = bookList.stream()
			.filter(title -> title.length() < 13)
			.distinct()
			.collect(Collectors.toList());
	System.out.println("Short and distinct book titles: " + shortTitles);
	System.out.println();
	
	List<String> oBooks = bookList.stream()
			.map(a -> a.toLowerCase())
			.filter(title -> title.contains("o"))
			.distinct()
			.map(a -> a.toUpperCase())
			.collect(Collectors.toList());
	System.out.println("Books with an 'o' in their title: " + oBooks);
			 
	System.out.println();
			 
	List<Integer> yearList = Arrays.asList(1925, 2001, 2008, 2008, 2016, 1812, 1776, 1771, 2008, 2007, 1295, 1492, 1812, 950, 500, 33, 2);
	
	List<Integer> postRenaissance = yearList.stream() // source is yearList // I'm streaming it
			 .filter(year -> year > 1600)
			 .distinct()
			 .sorted()
			 .collect(Collectors.toList()); // collector "collect" and then sending it to list
	System.out.println("Years that were after the renaissance: " + postRenaissance);
	System.out.println("Number of years " + postRenaissance.size());
			
	Integer yearCount = yearList.stream()
			.distinct()
			.mapToInt(year -> year.intValue())
			.sum();
	System.out.println("Years added up " + yearCount);
	
	 
	}// main

	// inner class hidden at the bottom of the file
enum Level {K12, UNDERGRAD, CAPSTONE, GRADUATE};
	static class Student {
		private String name;
		private int age;
		private Level level;
		
		Student(String name, int age, Level level){
			this.name = name;
			this.age = age;
			this.level = level;
		}
		public String getName() { return name;}
		public int getAge() { return age;}
		public Level getLevel() { return level;}
		public String toString() { return this.name + "-" + this.age + "-" + this.level;}
	}


}

