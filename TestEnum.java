import java.util.ArrayList;

// an enumerated type extends java.lang.Enum which has useful methods
public class TestEnum {	
	// this enum can be in a separate class….try it out if you want
public enum Continent { ANTARCTICA, SOUTH_AMERICA, NORTH_AMERICA, 
		EUROPE, ASIA, AFRICA, AUSTRALIA }

	public static void main(String[] args) {	
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
		

	}// main
}// class

