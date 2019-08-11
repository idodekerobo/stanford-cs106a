import acm.program.*;
import acm.util.*;
import java.io.*;

public class Histograms extends ConsoleProgram {
	
	public void run() {
		createArray();
		readScores();
		drawHistogram();
	}
	
	private void createArray() {
		histogramArray = new int[11];
		for (int i = 0; i < 11; i++) {
			histogramArray[i] = 0;
		}
	}
	
	private void readScores() {
		try {
			BufferedReader rd = new BufferedReader(new FileReader(DATA_FILE));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;	
				int score = Integer.parseInt(line);
				
				if (score < 0 || score > 100) {
					throw new ErrorException("that score is outside of the range");
				} else {
					int range = score/10;
					histogramArray[range]++;
				}
			}
			rd.close();
			
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}
	
	private void drawHistogram() {
		
		for (int i = 0; i < 11; i++) {
			String label;
			switch (i) {
				case 0: label = "00-09: "; 
				break;
				case 10: label = "100: ";
				break;
				default: label = i*10 + "-" + (i*10+9) + ": ";
				break;
			}
			println(label + createStars(histogramArray[i]));
		}	
	}
	
	private String createStars(int numStars) {
		String stars = "";
		
		for (int i = 0; i < numStars; i++) {
			stars += "*";
		}
		
		return stars;
	}
	
	
	
	//instance variables
	private int[] histogramArray;
	private static final String DATA_FILE = "MidtermScores.txt";
}
