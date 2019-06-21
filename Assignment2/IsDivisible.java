import acm.program.ConsoleProgram;

public class IsDivisible extends ConsoleProgram{

	/**
	 * Method: Is Divisible By
	 * -----------------------
	 * If a is less than b you should return false
	 * if either a or b is 0 or negative you should return false
	 * otherwise return true only if a divides by b perfectly 
	 * with no remainder.
	 * Example usage: 
	 *   isDivisibleBy(9, 3) => true
	 *   isDivisibleBy(5, 2) => false
	 */
	private boolean isDivisibleBy(int a, int b) {
		// Your code here!
		if ((a % b) == 0) {
			return true;
		} else if (a <= 0) {
			return false;
		} else if (b <= 0) {
			return false;
		} else {
		return false;
		}
	}

	/****************************************************
	 *                  STARTER CODE                    *
	 * You can read this code, but you should not edit  *
	 * It (except to add more tests, if you so desire)  *
	 ****************************************************/

	// This run method executes a barrage of tests.
	public void run() {
		println("Welcome to the IsDivisible program.");
		getLine("Press enter to start");
		println("");
		
		println("Running automatic tests...");
		runTest(10, 2, true);
		runTest(9, 3, true);
		runTest(5, 1, true);
		runTest(3, 9, false);
		runTest(10, 0, false);
		runTest(-10, 2, false);
		runTest(10, -2, false);
		
		println("\n\nRunning manual tests...");
		while(true) {
			runManualTest();
		}
	}

	/**
	 * Method: Run Test
	 * ----------------
	 * Takes in an input and an expected output, and checks
	 * if the method produces the correct output! Each
	 * call runs exactly one test.
	 */
	private void runTest(int a, int b, boolean expectedReturn) {
		// call your method!
		boolean actualReturn = isDivisibleBy(a, b);

		// print out the results
		println("Input:           " + "a = " + a + ", b = " + b);
		println("Expected return: " + expectedReturn);
		println("Actual return:   " + actualReturn);


		// don't forget to use .equals when comparing strings
		if(expectedReturn == actualReturn) {
			println("Test passed");
		} else {
			println("Test did not pass");
		}

		// this adds a blank line so each test looks like a
		// paragraph of text
		println("");
	}

	/**
	 * Method: Run Manual Test
	 * ----------------
	 * Asks the user for two integers and calls isDivisibleBy
	 * on those two numbers.
	 */
	private void runManualTest() {
		println("Enter two numbers (a and b) and isDivisibleBy will tell you");
		println("If a is divisible by b");
		int a = readInt("a: ");
		int b = readInt("b: ");
		boolean result = isDivisibleBy(a, b);
		println("isDivisibleBy returned: " + result);
		println("");

	}



}
