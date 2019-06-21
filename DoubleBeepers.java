/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class DoubleBeepers extends SuperKarel {
	public void run() {
		move();
// write in plain english at highest level, then go about defining those methods
		doubleBeepersInPile();
		moveBackwards();
	}
	
	
	private void moveBackwards() {
		turnAround();
		move();
		turnAround();
	}
	
	private void doubleBeepersInPile() {
		while (beepersPresent()) {
			pickBeeper();
			putTwoBeepersNextDoor();
		}
		movePileNextDoorBack();
	}
	
	private void putTwoBeepersNextDoor() {
		move();
		putBeeper();
		putBeeper();
		moveBackwards();
	}
	
	private void movePileNextDoorBack() {
		move();
		while (beepersPresent()) {
			moveOneBeeperBack();
		}
		moveBackwards();
	}
	
	private void moveOneBeeperBack() {
		pickBeeper();
		moveBackwards();
		putBeeper();
		move();
	}
	
	
	
}
