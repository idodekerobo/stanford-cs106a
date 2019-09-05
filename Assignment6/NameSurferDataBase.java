import java.io.*;
import java.util.*;

import acm.program.ConsoleProgram;
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

public class NameSurferDataBase extends ConsoleProgram implements NameSurferConstants {
	
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
			println(filename);
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
		NameSurferEntry entry = nameData.get(name);
		return entry;
		
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

