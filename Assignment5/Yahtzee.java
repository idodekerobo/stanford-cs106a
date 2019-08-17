/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
		/* You fill this in */
		createDie();
		//for (int i = 1; i <= 13; i++) {
		//	for (int j=0; j < nPlayers; j++) {
				playerTurn(1);
		//	}
		//}
		
	}
	
	/* initializing the 5 different die */
	private void createDie() {
		die = new int[N_DICE];
		for (int i = 0; i < YahtzeeConstants.N_DICE; i++) {
			die[i] = 0;
		}
	}
	
	private void playerTurn(int playerIndex) {
		
		display.waitForPlayerToClickRoll(playerIndex);
		initialRoll();
		
		
		for (int i=0; i<2; i++) {
			display.waitForPlayerToSelectDice();
			rollSelectedDie();
			
		} 
		
	}
	
	/* rolls die at beginning of turn */
	private void initialRoll() {
		for (int i = 0; i < N_DICE; i++) {
			die[i] = rgen.nextInt(1, 6);
		}
		/*passing in array of die values to display on the screen */
		display.displayDice(die);
	}
	
	/*rolls the selected die using a for loop and random generator */
	private void rollSelectedDie() {
		for (int i = 0; i < N_DICE; i++) {
			if (display.isDieSelected(i)) {
				die[i] = rgen.nextInt(1, 6);
			}
		}
		
		display.displayDice(die);
	}
	
	

/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	
	private int[] die;

}
