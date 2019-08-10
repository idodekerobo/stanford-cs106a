import acm.program.*;
import acm.util.*;
import java.io.*;

public class WordCount extends ConsoleProgram {
	
	public void run() {
		wordCount();
	}
	
private void wordCount() {

		String fileName = readLine("What is the file name (w/ .ext at the end)? ");
		fileName(fileName);
		println("Lines: " + totalLines(fileName));
		println("Words: " + totalWords(fileName));
		totalChars(fileName);

	}
	
	
	private void fileName(String fileName) {
		println("File: " + fileName);
	}
	
	// you have to open and close the file ea/ time you want to read from it
	private int totalLines(String fileName) {
		int numLines = 0;
		try {
			// open file
			BufferedReader rd = new BufferedReader(new FileReader(fileName));

			// do work
			while (true) {
				if (rd.readLine() == null) break;
				numLines++;
			}
			
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		
		return numLines;
	}

	// you have to open and close the file ea/ time you want to read from it
	private int totalWords(String fileName) {
		int numWords = 0;
		try {
			// open file
			BufferedReader rd = new BufferedReader(new FileReader(fileName));
			
			//do work w/ file
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == ' ') {
						numWords++;
					}
				}
			}
			int addingBackLines = totalLines(fileName);
			numWords += addingBackLines;
			
			//close file
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		
		return numWords;
	}
	
	private int totalChars(String fileName) {
		int numChars = 0;
		try {
			//open file via passed in filename
			BufferedReader rd = new BufferedReader(new FileReader(fileName));
			
			// do work
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				for (int i = 0; i < line.length(); i++) {
					numChars++;
				}
				if (line.charAt(line.length()-1) == ',') {
					numChars--;
				}
			}
			println("Characters: " + numChars);
			
			//close file
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		
		return numChars;
	}
		
}
