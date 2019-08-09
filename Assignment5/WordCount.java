import acm.program.*;
import acm.util.*;
import java.io.*;

public class WordCount extends ConsoleProgram {
	
	public void run() {
		wordCount();
	}
	
private BufferedReader wordCount() {
		
		BufferedReader rd = null;
		String fileName = readLine("What is the file name (w/ .ext at the end)? ");
		try {
			//open file
			rd = new BufferedReader(new FileReader(fileName));
			
			// do work
			
			fileName(rd);
			totalLines(rd);
			totalWords(rd);
			totalChars(rd);
			
			//close file
			rd.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
		return rd; 
	}
	
	
	private void fileName(BufferedReader rd) {
		println("File: ");
	}
	
	private void totalLines(BufferedReader rd) {
		int numLines = 0;
		try {
			while (true) {
				if (rd.readLine() == null) break;
				numLines++;
			}
			println("Lines: " + numLines);
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	private void totalWords(BufferedReader rd) {
		int numWords = 0;
		try {
			while (true) {
				if (rd.readLine() == null) { break; }
				String line = rd.readLine();
				for (int i = 0; i < line.length(); i++) {
					if (line.charAt(i) == ' ') {
						numWords++;
					}
				}
			}
			println("Words: " + numWords);
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	private void totalChars(BufferedReader rd) {
		int numChars = 0;
		/* try {
			
			println("Characters: " + numChars);
		} catch (IOException ex) {
			throw new ErrorException(ex);
		} */
	}
	
	
	
	
}
