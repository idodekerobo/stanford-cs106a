import acm.program.*;
import java.util.ArrayList;

public class UniqueNames extends ConsoleProgram {
	
	public void run() {
		
		while (true) {
			String name = readLine("Enter name: ");
			if (name.equals("")) break;
			
				if (!nameList.contains(name)) {
					nameList.add(name);
				}
		}
		
		for (int i = 0; i < nameList.size(); i++) {
			println(nameList.get(i));
		}
		
	}
	
	ArrayList<String> nameList = new ArrayList<String>();
}
