import acm.program.*;

public class AddCommas extends ConsoleProgram {
	
	
	public void run() {
		while (true) {
			String digits = readLine("Enter a numeric string: ");
			if (digits.length() == 0) { break; }
			println(addCommasToString(digits));
		}
	}
	
	private String addCommasToString(String digits) {
		int length = digits.length() - 1;
		int counter = 1;
		String newString = "";
		for (int i = length; i >= 0; i--) {
			if (counter % 3 == 0 && i > 0) {
				 newString = "," + digits.charAt(i) + newString;
			} else {
				newString = digits.charAt(i) + newString;
			}
			counter++;
		}
		return newString;
	}
}
