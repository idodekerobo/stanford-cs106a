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
		
		/* init scoreboard based on how many players and categories
		 *  also init placeholder multi-dim array of same size to keep track of categories already chosen
		 *  */
		scoreboard = new int[nPlayers][N_CATEGORIES];
		selectedCategories = new int[nPlayers][N_CATEGORIES];
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
		createDie();
		// for 13 turns
		for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
			// let ea/ player have one turn
			for (int j=0; j < nPlayers; j++) {
				playerTurn(j);
			}
		}
		
		// check if either player has gotten the upper bonus 
		checkForUpperBonus();
		
		// calculate total
		calculateTotalScore();
		
		// check who has won
		checkWinner();
	}
	
	/* initializing the 5 different die */
	private void createDie() {
		die = new int[N_DICE];
		for (int i = 0; i < YahtzeeConstants.N_DICE; i++) {
			die[i] = 0;
		}
	}
	
	private void playerTurn(int playerIndex) {
		
		display.printMessage("Hey "+ playerNames[playerIndex]+ ", roll the dice to get your turn started!");
		display.waitForPlayerToClickRoll(playerIndex);
		initialRoll();
		
		for (int i=0; i<2; i++) {
			display.printMessage("Select the dice that you'd like to reroll. If none, just click roll again.");
			display.waitForPlayerToSelectDice();
			rollSelectedDie();
			
		}
		
		chooseCategoryAndCalculateScore(playerIndex);
		
		/* updating the total of upper score */
		upperScore = 0;
		for (int i=0; i < UPPER_SCORE; i++) {
			upperScore += scoreboard[playerIndex][i];
		}
		scoreboard[playerIndex][UPPER_SCORE] = upperScore;
		display.updateScorecard(UPPER_SCORE, playerIndex, upperScore);
		
		/* updating the total of lower score */
		lowerScore = 0;
		for (int i=THREE_OF_A_KIND; i < LOWER_SCORE; i++) {
			lowerScore += scoreboard[playerIndex][i];
		}
		scoreboard[playerIndex][LOWER_SCORE] = lowerScore;
		display.updateScorecard(LOWER_SCORE, playerIndex, lowerScore);
		
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
	
	private void chooseCategoryAndCalculateScore(int playerIndex) {
		display.printMessage("Choose a category that you'd like to enter the dice for.");
		int category = display.waitForPlayerToSelectCategory();
		
		while (selectedCategories[playerIndex][category] !=  0) {
			display.printMessage("You can only choose a category once. Please select another category");
			category = display.waitForPlayerToSelectCategory();
		}
		
		checkScoreOfCategory(category, playerIndex);
	}
	
	private void checkScoreOfCategory(int category, int playerIndex) {
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
			// full house
			sortDieByValue(die);
			Boolean parents = false;
			Boolean children = false;
			
			// counts the frequency of ea/ value possible on die. goes to 6 because its a six sided die
			int[] counts = new int[6];
			
			//count occurences in array
			for (int i=0; i < die.length; i++) {
				
				/* int j = die[i]-1;
				counts[j]++; */
				
				/* shortened version */
				counts[die[i]-1]++;
			}
			
			// check for a 2 (parents) and a 3 (children) for it to be a full house
			for (int i: counts) {
				if (i==2) {
					parents = true;
				}
				if (i==3) {
					children = true;
				}
			}
			
			if (parents && children) {
				scoreForCategory += 25;
			}
		} else if (category == 11 || category == 12) {
			// small straight
			sortDieByValue(die);
			int numberOfMatches = 0;
			
			for (int i=0; i < die.length - 1; i++) {
				int firstElement = die[i] + 1;
				int secondElement = die[i+1];
				
				if (firstElement == secondElement) {
					numberOfMatches++;
				}
			}
			
			//small straight
			if (numberOfMatches >= 4 && category == 11) {
				scoreForCategory += 30;
			}
			
			//large straight
			if (numberOfMatches >= 5 && category == 12) {
				scoreForCategory += 40;
			}
		} else if (category == 13) {
			Boolean yahtzee = false;
			
			for (int i=0; i < die.length-1; i++) {
				if (die[i] == die[i + 1]) {
					yahtzee = true;
				}
			}
			
			if (yahtzee) {
				scoreForCategory += 50;
			}
		} else if (category == 14) {
			for (int i=0; i < die.length; i++) {
				scoreForCategory += die[i];
			}
		}
		
		println("This was your score for that, Player " + playerIndex + " " + scoreForCategory);
		display.printMessage("Your score for that category was "+scoreForCategory);
		
		scoreboard[playerIndex][category]= scoreForCategory;
		display.updateScorecard(category, playerIndex, scoreForCategory);
		
		selectedCategories[playerIndex][category] = 1;
		
		/* keep track of total */
		scoreboard[playerIndex][TOTAL] += scoreForCategory;
		display.updateScorecard(TOTAL, playerIndex, scoreboard[playerIndex][TOTAL]);
		
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
	
	private void checkForUpperBonus() {
		/* check if either player earned upper score bonus */
		for (int i=0; i < nPlayers; i++) {
			if (scoreboard[i][UPPER_SCORE] > 63) {
				
				println(playerNames[i] + " earned the upper bonus!");
				display.printMessage(playerNames[i] + " earned the upper bonus!");
				println(playerNames[i] + " earned the upper bonus!");
				
				scoreboard[i][UPPER_BONUS] = 35;
				display.updateScorecard(UPPER_SCORE, i, 35);
				
			} else {
				scoreboard[i][UPPER_BONUS] = 0;
				display.updateScorecard(UPPER_SCORE, i, 0);
				println(playerNames[i] + " did not earn the upper bonus :(");
				display.printMessage(playerNames[i] + " did not earn the upper bonus :(");
				
			}
		}
	}
	
	private void calculateTotalScore() {
		for (int i=0; i < nPlayers; i++) {
			int total = 
					scoreboard[i][UPPER_SCORE]
							+ scoreboard[i][UPPER_BONUS]
									+ scoreboard[i][LOWER_SCORE];
			
			println("==================");
			println(playerNames[i]);
			println("Upper score: " + scoreboard[i][UPPER_SCORE]);
			println("Upper bonus: " + scoreboard[i][UPPER_BONUS]);
			println("Lower Score: " + scoreboard[i][LOWER_SCORE]);
			println("Total: " + total);
			println("==================");
			
		}
	}
	
	private void checkWinner() {
		int max = 0;
		String winner = "";
		
		
		for (int i=0; i < nPlayers; i++) {
			if (scoreboard[i][TOTAL] > max) {
				max = scoreboard[i][TOTAL];
				winner = playerNames[i];
			}
		}
		
		display.printMessage("With a score of " + max + ", " + winner + " has won!!");
		println("With a score of " + max + ", " + winner + " has won!!");
	}
	
	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] die;
	private int[][] scoreboard;
	private int[][] selectedCategories;
	private int scoreForCategory;
	
	private int upperScore;
	private int lowerScore;

}

/*
 * another way of checking for yahtzee
 */
/*
sortDieByValue(die);
int numberOfMatches = 0;
for (int i=1; i < die.length; i++) {
	if (die[i] == die[i-1]) {
		numberOfMatches++;
	}
}
			
if (numberOfMatches == 5) {
	scoreForCategory += 50;
}
*/
