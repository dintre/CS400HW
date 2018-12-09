package application;

// classic interface
public interface CityType {
	
	// abstract method. usual method. must be implemented
	public String getName();
	
	// default method. Doesn't force implementing classes to implement it because it has a default.
	public default int getPopulation() {
		return 100000;
	};
	
	// usual abstract method. must be implemented
	public int getUrbanPopulation(); 
	
	public static int getMetroPopulation() {
		return 20000;
	}
	
} // interface
