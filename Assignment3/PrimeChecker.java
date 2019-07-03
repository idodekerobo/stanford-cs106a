/*
 * File: PrimeChecker.java
 * -----------------------------
 * Checks whether a series of numbers are prime
 */

import acm.program.*;

public class PrimeChecker extends ConsoleProgram {
	
	int[] testCases = {2, 3, 8, 37,  42, 87, 361, 382, 729, 1019};
	boolean[] answers = {true, true, false, true, false, false, false, false, false, true};
	
	/**
	 * Don't modify this method! You only need to edit the 
	 * isPrime method so that it works correctly. 
	 * 
	 * The run method just tests your method against a variety
	 * of numbers to make sure that it's behaving as expected.
	 */
	public void run() {	
		for (int i = 0; i < testCases.length; i++) {
			int testCase = testCases[i];
			boolean solution = answers[i];
			boolean returned = isPrime(testCase);
			
			if (solution == returned) {
				println("Your solution worked for n = " + testCase + ".");
				
			} else {
				println("Your method returned " + returned + " for n = " + 
						testCase + ", but it should have returned " + solution + ".");
			}
		}
	}
	
	/**
	 * 
	 * @param n a positive integer greater than 1
	 * @return true if n is prime and false otherwise
	 */
	public boolean isPrime(int n) {		
/* prime numbers only have factors of 1 and itself.
 * i = 5
 * 5 % 5 == 0 true
 * 5 % 4 == 0 false
 * 5 % 3 == 0 false
 * 5 % 2 == 0 false
 * 5 % 1 == 0 true
 */
		for (int i = n - 1; i > 1; i--) {
			if ((n % i) == 0) {
				return false;
			}
		}
		return true; // this is only here so the program compiles
	}

}
