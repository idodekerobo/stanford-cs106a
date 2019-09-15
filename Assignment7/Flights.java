import java.util.*;

public class Flights {

	/* Constructor */
	public Flights(String line) {
		parseLine(line);
	}
	
	private void parseLine(String line) {
		int leavingCityEnd = line.indexOf("-") - 1; // grabs the ending of the city by taking one char before the - which is a space.
		leavingCity = line.substring(0, leavingCityEnd);
//		leavingCity = trySubstring(line);
		
		int destinationCityStart = line.indexOf(">") + 2;
		destinationCity = line.substring(destinationCityStart);
		destinationCities.add(destinationCity);
		
	}
	
	private String trySubstring(String line) {
		try {
			int dash = line.length();
			String newLine = line.substring(0, dash);
			return newLine;
		} catch (NumberFormatException ex) {
			return "The leaving city substring didn't work";
		}
	}

	public String getLeavingCity() {
		return leavingCity;
	}
	
	public String getReturnFlight() {
		return destinationCity;
	}
	
	public ArrayList<String> getDestinations() {
		return destinationCities;
	}
	
	private String leavingCity = "";
	private String destinationCity;
	public ArrayList<String> destinationCities = new ArrayList<String>();
	
}
