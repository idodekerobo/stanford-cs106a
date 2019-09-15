import java.io.*;
import java.util.*;

import acm.util.*;
/*
 * File: FlightDataBase.java
 * This file keeps track of all of the flights that this airline offers.
 */

public class FlightDataBase {
	
	/* Constructor */
	public FlightDataBase(String filename) {
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = rd.readLine();
				if (line == null) {
					break;
				} else if (line.length() == 0) {
					
				} else {
					Flights flight = new Flights(line);
					
					if (flightList.containsKey(flight.getLeavingCity())) {
						
//						Flights existingFlightObject = flightList.get(flight);
//						String newDestination = flight.getReturnFlight();		
						
						flightList.get(flight.getLeavingCity()).destinationCities.add(flight.getReturnFlight());
						
					} else {
						flightList.put(flight.getLeavingCity(), flight);					
					}
				}
				
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	public HashMap<String, Flights> flightList = new HashMap<String, Flights>();

}
