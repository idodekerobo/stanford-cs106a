/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		/* You fill this in */
		
		println("Enter Values to compute Pythagorean Theorem.");
		
		
		double a = readDouble("a: ");
		double aSquared = a*a;
		double b = readDouble("b: ");
		double bSquared = b*b;
		
		double c = Math.sqrt((aSquared + bSquared));
		
		println("c = " + c);
		
	}
}
