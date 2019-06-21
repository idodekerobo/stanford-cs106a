/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	// You fill in this part
	
	public void run() {
		repairQuad();
	}
	
/*
 * 
 * Repeat Until at wall
 * 1. Repair Pole
 * 2. Go to Next Pole
 * 3. 
 * 	
 */
	private void repairQuad() {
		turnLeft();
		while (rightIsClear() && frontIsClear()) {
			repairPole();
			moveToNextPole();
		}
	}
	
	
	private void repairPole() {
		while (frontIsClear()) {
			if (noBeepersPresent()) {
				putBeeper();
			}
			move();
		}
	}
	
	private void moveToNextPole() {
		if (facingNorth() && rightIsClear()) {
			turnRight();
			move();
			move();
			move();
			move();
			turnRight();
		} else if (facingSouth() && leftIsClear()) {
			turnLeft();
			move();
			move();
			move();
			move();
			turnLeft();
		}
	}

}