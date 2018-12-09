import java.util.ArrayList;

// an enumerated type extends java.lang.Enum which has useful methods
public class TestEnum {    
    
	
	// -------------------- this enum can be in a separate class….try it out if you want ----------------
public enum Continent { ANTARCTICA, SOUTH_AMERICA, NORTH_AMERICA, 
        EUROPE, ASIA, AFRICA, AUSTRALIA }

    public static void main(String[] args) {    
        Continent c1 = Continent.ASIA;
        System.out.println(c1);  // if c1 is an object, why does this work?
        c1.toString();
        // try out the methods such as .ordinal(), .compareTo()
        System.out.println(c1.ordinal());
        System.out.println(c1.compareTo(Continent.SOUTH_AMERICA)); // 4 - 1 = 3

        // generate an array of values and iterate through it 
        Continent [] continents = Continent.values();
        // print its values
        for(int i = 0; i < continents.length; i++) {
        	System.out.println(continents[i]);
        	
        } // for
        System.out.println();
        // for each loop
        for(Continent cont : continents) {
        	System.out.println(cont);
        }
        
        // generate a random continent from the array
        int r = (int) (Math.random() * continents.length); // generates random number
        System.out.println(r + " - " + continents[r]);
        

// make an ArrayList of Continent
        ArrayList<Continent> cList = new ArrayList<Continent>();
        cList.add(Continent.ASIA);
        cList.add(Continent.SOUTH_AMERICA);
        cList.add(Continent.SOUTH_AMERICA);
        cList.add(Continent.ANTARCTICA);
        cList.add(Continent.ASIA);
        cList.add(Continent.AFRICA);

// use in a switch statement....reuse ArrayList and give points
        int points = 0;
        for (Continent c : cList) {
            switch (c) {
            case ASIA:         points += 7; break;
            case EUROPE:
            case NORTH_AMERICA:
            case AFRICA:     points += 5; break;
            case SOUTH_AMERICA:
            case AUSTRALIA:    points += 2; break;
            default:        points += 1;
            }// switch
        }// for
        System.out.println("you have " + points + " points");


        // ON YOUR OWN
        // use a different array to tally the frequency of each value in cList
        


        // print out each value and its frequency
        

    }// main
    
}// class

