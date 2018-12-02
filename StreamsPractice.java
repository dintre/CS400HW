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
				.limit(2)
				.collect(Collectors.toList()); // type of collector must match result type
	System.out.println(result);
	

	// collector is a for/each iterator
	words.stream()	
			.map(x ->x.toLowerCase())
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
				.map(x ->x.toLowerCase())
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
	 
	 
	 // count of students who are older than 20
	 Long oldStudents = students.stream()
			 .filter(s -> s.getAge() > 20).count();
			 System.out.println(oldStudents + " students older than 20.");
	 
	 
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

