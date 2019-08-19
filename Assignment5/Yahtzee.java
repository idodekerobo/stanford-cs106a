/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	public void run() {
		setupPlayers();
		initDisplay();
		playGame();
	}
	
	/**
	 * Prompts the user for information about the number of players, then sets up the
	 * players array and number of players.
	 */
	private void setupPlayers() {
		nPlayers = chooseNumberOfPlayers();	
		
		/* Set up the players array by reading names for each player. */
		playerNames = new String[nPlayers];
		for (int i = 0; i < nPlayers; i++) {
			/* IODialog is a class that allows us to prompt the user for information as a
			 * series of dialog boxes.  We will use this here to read player names.
			 */
			IODialog dialog = getDialog();
			playerNames[i] = dialog.readLine("Enter name for player " + (i + 1));
		}
	}
	
	/**
	 * Prompts the user for a number of players in this game, reprompting until the user
	 * enters a valid number.
	 * 
	 * @return The number of players in this game.
	 */
	private int chooseNumberOfPlayers() {
		/* See setupPlayers() for more details on how IODialog works. */
		IODialog dialog = getDialog();
		
		while (true) {
			/* Prompt the user for a number of players. */
			int result = dialog.readInt("Enter number of players");
			
			/* If the result is valid, return it. */
			if (result > 0 && result <= MAX_PLAYERS)
				return result;
			
			dialog.println("Please enter a valid number of players.");
		}
	}
	
	/**
	 * Sets up the YahtzeeDisplay associated with this game.
	 */
	private void initDisplay() {
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
	}

	/**
	 * Actually plays a game of Yahtzee.  This is where you should begin writing your
	 * implementation.
	 */
	private void playGame() {
		/* You fill this in! */
		createDie();
		for (int i = 1; i <= 13; i++) {
			for (int j=0; j < nPlayers; j++) {
				playerTurn(j);
			}
		}
		
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
		display.updateScorecard(
				checkScoreOfCategory(display.waitForPlayerToSelectCategory()),
				playerIndex,
				scoreForCategory);
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
	
	private int checkScoreOfCategory(int category) {
		scoreForCategory = 0;
		
		if (category >= 0 && category <= 5) {
			for (int i=0; i < N_DICE; i++) {
				if (die[i] == category+1) {
					scoreForCategory += die[i];
				}
			}
		} else if (category == 8) {
			sortDieByValue(die);
			for (int i=0; i <= N_DICE/2; i++) {
				if (die[i] == die[i+1] && die[i] == die[i+2]) {
					scoreForCategory += die[i]+die[i+1]+die[i+2];
					break;
				}
			}
		} else if (category == 9) {
			sortDieByValue(die);
			for (int i=0; i < N_DICE/2; i++) {
				if (die[i] == die[i+1] && die[i] == die[i+2] && die[i] == die[i+3]) {
					scoreForCategory += die[i]+die[i+1]+die[i+2]+die[i+3];
					break;
				}
			}
		} else if (category == 10) {
			Boolean p = YahtzeeMagicStub.checkCategory(die, category);
			if (p) {
				scoreForCategory += 25;
			}
		} else if (category == 11) {
			Boolean p = YahtzeeMagicStub.checkCategory(die, category);
			if (p) {
				scoreForCategory += 30;
			}
		} else if (category == 12) {
			Boolean p = YahtzeeMagicStub.checkCategory(die, category);
			if (p) {
				scoreForCategory += 40;
			}
		} else if (category == 13) {
			Boolean p = YahtzeeMagicStub.checkCategory(die, category);
			if (p) {
				scoreForCategory += 50;
			}
		} else if (category == 14) {
			for (int i=0; i < die.length; i++) {
				scoreForCategory += die[i];
			}
		}
		
		return category;
	}
	
	
	private int[] sortDieByValue(int[] die) {
		int temp;
		
		for (int i=0; i < die.length; i++) {
			
			for (int j= i+1 ; j < die.length; j++) {
				temp = die[i];
				if (die[j] < die[i]) {
					die[i] = die[j];
					die[j] = temp;
				}
			}
			
		}
		return die;
	}
	
	private void calculateScore() {
		
	}
	
	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] die;
	private int scoreForCategory;
}
