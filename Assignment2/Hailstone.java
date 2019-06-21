/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		welcomeMessage();
		hailStone();
		
	}
	
	private void welcomeMessage() {
		println("This program runs the Hailstone Sequence!");
	}
	
	private void hailStone() {
		int startNumber = readInt("Enter a number: ");
		int counter = 0;
		
		while (startNumber != 1) {
			if (startNumber  % 2 != 0) {
				print(startNumber+ " is odd, so I do 3n+1: ");
				startNumber = (3*startNumber) + 1;
				println(startNumber);
			} else {
				print(startNumber+ " is even, so take half: ");
				startNumber = startNumber/2;
				println(startNumber);
			}
			counter++;
		}
		println("The process took "+counter+" steps to reach 1.");
	}
}

