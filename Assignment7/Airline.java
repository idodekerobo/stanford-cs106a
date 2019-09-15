import java.util.*;
import acm.program.*;

public class Airline extends ConsoleProgram {

	public void run() {
		showCitiesInDataBase();
		startRoute();
		int numStops = roundTrip.size() - 1;
		
		while (true) {
			planRoute(roundTrip.get(numStops));
			
			int lastStop = roundTrip.size() - 1;
			if (roundTrip.get(0).equals(roundTrip.get(lastStop))) break;
		}
		printTrip();
	}
	
	private void showCitiesInDataBase() {
		println("Welcome to Flight Planner!");
		
		HashMap<String, Flights> allHubs = flightData.flightList;
		Iterator<String> it = allHubs.keySet().iterator();
		
		println("Here's a list of all the cities in our database:");
		while (it.hasNext()) {
			String leavingCity = it.next();
			println(leavingCity);
		}
		println();
	}
	
	private String startRoute() {
		println("Let's plan a round trip route!");
		String startingCity = readLine("Enter the city you'd like to leave from: ");
		
		roundTrip.add(startingCity);
		
		Flights hub = flightData.flightList.get(startingCity);
		
		ArrayList<String> destinations = hub.getDestinations();
		
		println("From " + startingCity + " you can fly directly to the following places:");
		for (int i=0; i < destinations.size(); i++) {
			println(destinations.get(i));
		}
		println();
		return startingCity;
	}
	
	private String planRoute(String startingCity) {
		String firstDestination = readLine("Where do you want to go from " + roundTrip.get((roundTrip.size()-1)) + "? ");
		roundTrip.add(firstDestination);
		
		Flights hub = flightData.flightList.get(firstDestination);
		ArrayList<String> possibleDestinations = hub.getDestinations();
		
		if (firstDestination.equals(roundTrip.get(0))) {
			//println("Done");
			return firstDestination;
		} else {
			println("From " + firstDestination + " you can fly directly to the following places: ");
			for (int i=0; i < possibleDestinations.size(); i++ ) {
				println(possibleDestinations.get(i));
			}
			println();
			return firstDestination;
		}
	}
	
	private void printTrip() {
		String route = "";
		println();
		println("The route you've chosen is: ");
		for (int i=0; i < roundTrip.size(); i++) {
			route += roundTrip.get(i);
			
			if (i != roundTrip.size() - 1) {
				route += " -> ";
			}
		}
		println(route);
	}
	
	/* CONSTANTS */
	public static final String FILENAME = "flights.txt";
	
	/* instance variables */
	FlightDataBase flightData = new FlightDataBase(FILENAME);
	ArrayList<String> roundTrip = new ArrayList<String>();
	
}
