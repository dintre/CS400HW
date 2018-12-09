package application;

public class City implements CityType{



	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUrbanPopulation() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	
	public static void main(String[] args) {
		City newYork = new City();
		
		System.out.println(newYork.getPopulation());
		System.out.println(CityType.getMetroPopulation());	
	} // main
	
} // class
