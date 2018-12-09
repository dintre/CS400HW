import java.util.ArrayList;

// an enumerated type extends java.lang.Enum which has useful methods
public class TestEnum {	
	// this enum can be in a separate class….try it out if you want
	public enum Continent { ANTARCTICA, SOUTH_AMERICA, NORTH_AMERICA, 
			EUROPE, ASIA, AFRICA, AUSTRALIA }
	
	public enum State { WISCONSIN, MINNESOTA, MICHIGAN {
		@Override
		public String toString() {
			return "Michigan, big blue boo...";
		}
	}, ILLINOIS, OHIO, 
		PENNSYLVANIA, IOWA, NEBRASKA, VERMONT, ARIZONA, CALIFORNIA, 
		NEW_YORK, IDAHO, WASHINGTON, OREGON
	} // enum State

	public enum Courses { CS200, CS300, CS400 {
		@Override
		public String toString() {
			return "Data Structures";
		}
	}, CS354, CS760, CS540, } // enum Courses


	public static void main(String[] args) {	
		System.out.println(Courses.CS300);
		System.out.println(Courses.values());
		Courses [] listOfCourses = Courses.values();
		for(int i = 0; i < listOfCourses.length; i++) {
			System.out.println("Course: " + listOfCourses[i]);
		}
		Courses curCourse = Courses.CS400;
		System.out.println("Ordinal " + curCourse.ordinal());
		System.out.println("Name: " + curCourse.name() + " versus toString: " + curCourse.toString());
		
		Courses nextCourse = Courses.CS354;
		State myHomeState = State.WISCONSIN;
		State yuckyState = State.MICHIGAN;
		System.out.println("toString not overwritten: " + myHomeState.toString());
		System.out.println("toString overridden: " + yuckyState.toString());

		System.out.println("CompareTo: " + curCourse.compareTo(nextCourse));
		// System.out.println("CompareTo: " + curCourse.compareTo("CS400")); // does not work
		
		System.out.println("Equals: " + curCourse.equals(nextCourse));
		System.out.println("Equals: " + curCourse.equals(myHomeState));
		System.out.println("Equals: " + curCourse.equals("CS400"));
		
		// state practice ----------------------------------------------------------------------------
		/*
		System.out.println(State.MICHIGAN);
		System.out.println();
		State [] stateList = State.values();
		for(int i = 0; i < stateList.length; i++) {
			System.out.print(stateList[i] + "  ");
		}
		
		System.out.println();
		State myHomeState = State.WISCONSIN;
		System.out.println("My home state is " + myHomeState);
		System.out.println("Its ordinal value is " + myHomeState.ordinal());
		
		int randomInt = (int) (Math.random() * stateList.length);
		System.out.println();
		System.out.println("Random state is " + stateList[randomInt]);
		
		ArrayList<State> stateArray = new ArrayList<State>();
		stateArray.add(State.WISCONSIN);
		stateArray.add(State.NEBRASKA);
		stateArray.add(State.OREGON);
		
		int count = 0;
		for(State s : stateArray) {
			switch (s) {
				case WISCONSIN : count+=2; break;
				case NEBRASKA : count+=1; break;
				case OREGON : break;
				default : count = count + 0;
			} // switch
			
		} // for
		System.out.println();
		System.out.println("Wisconsin and Nebraska points are " + count);
		*/
		// state practice --------------------------------------------------------------------
		/*
		Continent c1 = Continent.ASIA;
		
		System.out.println(c1);  // if c1 is an object, why does this work?
		// try out the methods such as .ordinal(), .compareTo()
		System.out.println(c1.ordinal());
		System.out.println(c1.compareTo(Continent.SOUTH_AMERICA));


		// generate an array of values and iterate through it 
		Continent [] continents = Continent.values();
		
		for(int i = 0; i < continents.length; i++) {
			System.out.println(continents[i]);
		}
		// generate a random continent from the array
		int random = (int) (Math.random()*continents.length);
		System.out.println("Random continent " + continents[random]);
		

// make an ArrayList of Continent
		ArrayList<Continent> cList = new ArrayList<Continent>();
		cList.add(Continent.ASIA); cList.add(Continent.SOUTH_AMERICA);
		cList.add(Continent.SOUTH_AMERICA);cList.add(Continent.ANTARCTICA);
		cList.add(Continent.ASIA);

// use in a switch statement....reuse ArrayList and give points
		int points = 0;
		for (Continent c : cList) {
			switch (c) {
			case ASIA: 		points += 7; break;
			case EUROPE:
			case NORTH_AMERICA:
			case AFRICA: 	points += 5; break;
			case SOUTH_AMERICA:
			case AUSTRALIA:	points += 2; break;
			default:		points += 1;
			}// switch
		}// for
		System.out.println("you have " + points + " points");
		


		// ON YOUR OWN
		// use a different array to tally the frequency of each value in cList
		


		// print out each value and its frequency
		
	*/
		
	}// main
}// class

