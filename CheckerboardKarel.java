/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	// You fill in this part
	public void run() {
		putBeeper();
		firstWallCheck();
		while (frontIsClear()) {
			fillRowEast();
			fillRowWest();
		}
	}
	
	private void fillRowEast() {
		if (facingEast()) {
			while (frontIsClear()) {
				move();
				if (frontIsClear()) {
					move();
					putBeeper();
				}
			}
			upEast();
		}
	}
	
	private void fillRowWest() {
		if (beepersPresent() && frontIsClear()) {
			move();
			while (frontIsClear()) {
				move();
				putBeeper();
				if (frontIsClear()) {
//					putBeeper();
					move();
				}
			}
			
		} else if (frontIsClear()) {
			move();
			putBeeper();
			while (frontIsClear()) {
				move();
				if (frontIsClear()) {
					move();
					putBeeper();
				}
			}
		
		}
		upWest();
	}
	
	private void upEast() {
		if (beepersPresent()) {
			turnLeft();
			if (frontIsClear()) {
				move();
				turnLeft();
			}
		} else {
			turnLeft();
			if (frontIsClear()) {
				move();
				putBeeper();
				turnLeft();
			}
		}
	}
	
	private void upWest() {
		if (beepersPresent()) {
			turnRight();
			if (frontIsClear()) {
				move();
				turnRight();
			}
		} else {
			turnRight();
			if (frontIsClear()) {
				move();
				putBeeper();
				turnRight();
			}
		}
	}
	
	private void firstWallCheck() {
		if (frontIsBlocked()) {
			turnLeft();
			move();
			while (frontIsClear()) {
				move();
				if (frontIsClear()) {
					putBeeper();
					move();
				}
			}
		}
	}
	
}
