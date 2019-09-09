import java.io.*;
import java.util.*;
import acm.util.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		// You fill this in //
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				NameSurferEntry entry = new NameSurferEntry(line);
				nameData.put(entry.getName(), entry);
				//nameList.add(entry);
			}
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		// You need to turn this stub  into a real implementation //
		char firstLetter = name.charAt(0);
		if (Character.isLowerCase(firstLetter)) {
			firstLetter = Character.toUpperCase(firstLetter);
		}
		String restOfLetters = name.substring(1); 
		
		name = firstLetter + restOfLetters;
		
		if (nameData.containsKey(name)) {
			NameSurferEntry entry = nameData.get(name);
			return entry;
		} else {
			return null;
		}
		
		/* if (nameList.contains(name)) {
			int index = nameList.indexOf(name);
			NameSurferEntry entry = nameList.get(index);
			
			println(entry.getName());
			println(entry.getRank(START_DECADE));
			
			return entry;
			
		} else {
			return null;
		} */
	}
	
	// instance variables
	private ArrayList<NameSurferEntry> nameList = new ArrayList<NameSurferEntry>();
	private HashMap<String, NameSurferEntry> nameData = new HashMap<String, NameSurferEntry>();
}

