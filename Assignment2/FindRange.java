/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		println("This program finds the range from a list of inputted values.");
		findRange();
	}
	
	private void findRange() {
		int number;
		int sentinel = readInt("What's the sentinel value?");
		
		number = readInt("Give me some numbers!");
		int small = number;
		int large = number;
		
		while (true) {
			int value = readInt("Give me some numbers: ");
			
			if (value == sentinel) {
				break;
			}
			if (value < small) {
				small = value;
			}
			if (value > large) {
				large = value;
			}
		}
		println("Smallest value: " + small);
		println("Largest value: "+ large);
	}
}

