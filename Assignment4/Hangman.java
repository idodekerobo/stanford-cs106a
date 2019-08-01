/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {

    public void run() {
		/* You fill this in */
 
    	anonWord();
    	while (wrongGuesses < 3) {
    		takeGuess();
        	makeGuess();
    	}
    	
    	/*
    	setupGame();
    	
    	while (wrongGuesses <= 3) {
    		// take user guesses, if letter is correct show it in word and keep track of wrong guesses
    		makeGuess();
    	}
    	*/
    	// let user know if they won or lost
	}
    
    // set up game - choose word and return word in ------
    private void setupGame() {
    	println("Welcome to Hangman! We'll choose a word, and you'll try and guess the word by choosing letters!");
    	// make word be ----
    	anonWord();
    }
    
    
//    turn guess into into dashes representing ea/ letter
    private void anonWord() {
    	for (int i=0; i < secretWord.length(); i++) {
    		wordState += "_";
    	}
    	println("Here's your first hint: " + wordState);
    	//return wordState;
    }
    
    private void takeGuess() {
    	String guessLetter = readLine("Guess your letter: ");
    	while (guessLetter.length() >= 2 || 
    			( !(guessLetter.charAt(0) >= 65 && guessLetter.charAt(0) <= 90) || 
    					!(guessLetter.charAt(0) >= 97 && guessLetter.charAt(0) <= 122)) ) {
    		
    		if (guessLetter.length() == 1  && 
    				( (guessLetter.charAt(0) >= 65 && guessLetter.charAt(0) <= 90) || 
    						(guessLetter.charAt(0) >= 97 && guessLetter.charAt(0) <= 122)) ) {
    			break;
    		}
    		
    		println("You can only guess one character at a time and it has to be a letter. Try again.");
    		guessLetter = readLine("Guess your letter: ");
    	}
    	guessLetter = guessLetter.toUpperCase();
		guessedLetter = guessLetter.charAt(0);
		println("This is your guess: " + guessedLetter);
    }
    
    // compare BUOY to ----
    private void makeGuess() {
    	char ch = guessedLetter;
    	newWordState = "";
    	
    	// test if the ch is contained in the secret word, if not add 1 to wrong guesses
    	if (secretWord.indexOf(ch) == -1) {
    		wrongGuesses +=1;
    	// if it is, then do stuff
    	} else {
    		
    		// for every letter in secret word
    		for (int i = 0; i < secretWord.length(); i++) {
        		
        		// test if its been guessed first, if it has do nothing
        		if (wordState.charAt(i) == 95) {
        			
        			// if not, then test if ch equals secretword ch
        			if (ch == secretWord.charAt(i)) {
            			newWordState += ch;
            		} else {
            			newWordState += "_";
            		}
        		} else {
        			newWordState += wordState.charAt(i);
        		}
        	}
    	}
    	wordState = newWordState;
    	println("Current word: " + newWordState);
    	//return newWordState;
    }
    
    // replace dash with letter you guessed at the current position in above for loop
    /*
    private void addGuessToWord(String letter) {
    	
    	
    	
    	for (int i=0; i < secretWord.length(); i++) {
    		String wordChar = "" + secretWord.charAt(i);
    		
    		// if char equals an underscore go into the next logic block, if it equals anything else do nothing
    		if (guess.charAt(i) == 95) {
    			// if letter equals the letter at word add letter else add -
        		if (letter.equals(wordChar)) {
        			guess += letter;
        		} else {
        			guess += "_";
        		}
    		}
    		
    	}
    	println(guess);
    }
    */
    
    // instance variables
    private HangmanLexicon hangmanLexicon = new HangmanLexicon();
    // eventually will choose a random number from the lexicon
    private String secretWord = hangmanLexicon.getWord(3);
    
    // instance variable that will hold the guess as its built out
    //private static String guess = "";
    private static char guessedLetter;
    private static String wordState = "";
    private static String newWordState = "";
    private static int wrongGuesses = 0;

    
//    have to figure out how to count guesses only when answers are wrong
//    have to figure out how to not restart the progress anytime the letter is right
    
    
}
