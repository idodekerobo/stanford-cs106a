/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

/* Constructor: NameSurferEntry(line) */
/**
 * Creates a new NameSurferEntry from a data line as it appears
 * in the data file.  Each line begins with the name, which is
 * followed by integers giving the rank of that name for each
 * decade.
 */
	public NameSurferEntry(String line) {
		// You fill this in //
		parseLine(line);
		
	}

/* Method: getName() */
/**
 * Returns the name associated with this entry.
 */
	public String getName() {
		return name;
	}

/* Method: getRank(decade) */
/**
 * Returns the rank associated with an entry for a particular
 * decade.  The decade value is an integer indicating how many
 * decades have passed since the first year in the database,
 * which is given by the constant START_DECADE.  If a name does
 * not appear in a decade, the rank value is 0.
 */
	public int getRank(int decade) {
		int rank = yearAndRank.get(decade);
		return rank;
	}

/* Method: toString() */
/**
 * Returns a string that makes it easy to see the value of a
 * NameSurferEntry.
 */
	public String toString() {
		// You need to turn this stub into a real implementation //
		String toPrint = "";
		toPrint += name + " [";
		for (Integer year: yearAndRank.keySet()) {
			Integer rank = yearAndRank.get(year);
			toPrint += rank;	
		}
		toPrint += "]";
		return toPrint;
	}
	
	
	private void parseLine(String line) {
		// int nameEnd = line.indexOf(" "); // the -1 has it do the character right before the first space
		// name = line.substring(0, nameEnd);
		
		int decade = START_DECADE;
		int space = line.indexOf(" ");
		
		/* for (int i=0; i < NDECADES; i++) {
			int nextSpace = line.indexOf( (" " + 1), space);
			
			int rank = tryParse( line.substring((space+1), nextSpace) );
			yearAndRank.put(decade, rank);
			
			space = line.indexOf(" " , nextSpace);
			decade += 10;
		} */
		name = "Andrew";
		yearAndRank.put(1900, 500);
	}
	
	private Integer tryParse(String s) {
		try {
			return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	// instance variables
	private String name;
	private HashMap<Integer, Integer> yearAndRank;
}

