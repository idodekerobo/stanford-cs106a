import java.util.*;
import acm.program.*;

public class NameCounts extends ConsoleProgram {
	
	public void run() {
		collectName();
	}
	
	private void collectName() {
		HashMap<String, Integer> pocketbook = new HashMap<String, Integer>();
		
		while (true) {
			String name = readLine("Enter name: ");
			if (name.equals("")) break;
			
			if (pocketbook.containsKey(name)) {
				int count = (pocketbook.get(name)) + 1;
				pocketbook.put(name, count);
			} else {
				pocketbook.put(name, 1);
			}
		}
		
		printEntries(pocketbook);
		
	}
	
	private void printEntries(HashMap map) {
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String name = it.next();
			//println(name);
			//println("Entry ["+it.next()+"] has count " + pocketbook.get(it.next()));
			int count = (int) map.get(name);
			println("Entry ["+name+"] has count " + count);
			
		}
	}
	
}
