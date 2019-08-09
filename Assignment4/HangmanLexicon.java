/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import acm.util.*;
import java.io.*;
import java.util.ArrayList;

public class HangmanLexicon {

	// method can just return ArrayList - no need to copy to regular array
	private ArrayList<String> copyDataFileIntoArray() {
		ArrayList<String> lineList =  new ArrayList<String>();
		BufferedReader rd = null;
		//String line;
		try {	
			//1. open file
			rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				//2. read file
				String line = rd.readLine();
				if (line == null) break;
				lineList.add(line);
			}
			//3. close file
			rd.close();
		} catch (IOException ex) {
			//println("There was an error with the file.");
			//println("This was the error: " + ex);
			throw new ErrorException(ex);
		}
		// copy listList ArrayList to regular Array since the method returns an array
		/* String[] result = new String[lineList.size()];
		for (int i=0; i < result.length; i++) {
			result[i] = lineList.get(i);
		} */
		return lineList;
	}
	
/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		ArrayList<String> listOfWords = copyDataFileIntoArray();
		return (listOfWords.size());
	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
		ArrayList<String> listOfWords = copyDataFileIntoArray();
		if (index > 0 && index < listOfWords.size()-1) {
			return listOfWords.get(index);
		} else {
			throw new ErrorException("getWord: Illegal index");
		}

		/* switch (index) {
			case 0: return "BUOY";
			case 1: return "COMPUTER";
			case 2: return "CONNOISSEUR";
			case 3: return "DEHYDRATE";
			case 4: return "FUZZY";
			case 5: return "HUBBUB";
			case 6: return "KEYHOLE";
			case 7: return "QUAGMIRE";
			case 8: return "SLITHER";
			case 9: return "ZIRCON";
			default: throw new ErrorException("getWord: Illegal index");
		} */
	};
}
