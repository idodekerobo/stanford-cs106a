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
 
    	setupGame();
    	while (wrongGuesses < 3 && ((wordState.indexOf('_') != -1))) {
    		takeGuess();
        	makeGuess();
        	
        	if (wordState.indexOf('_') == -1) {
        		println("Congrats! You guessed the word!");
        	} else if (wrongGuesses >= 3) {
        		println("Damn, maybe you'll get it next time!");
        	}
    	}
    	
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
    			guessLetter = guessLetter.toUpperCase();
    			if (recordOfGuesses.indexOf(guessLetter.charAt(0)) != -1) {
    				println("You already guessed that letter!");
        		} else {
        			break;
        		}
    			
    		}
    		println("Remember: you can only guess one character at a time and it has to be a letter. Try again.");
    		guessLetter = readLine("Guess your letter: ");
    	}
    	guessLetter = guessLetter.toUpperCase();
		guessedLetter = guessLetter.charAt(0);
		recordOfGuesses += guessedLetter;
		println("This is your guess: " + guessedLetter);
    }
    
    // compare BUOY to ----
    private void makeGuess() {
    	char ch = guessedLetter;
    	newWordState = "";
    	
    	// test if the ch is contained in the secret word, if not add 1 to wrong guesses
    	if (secretWord.indexOf(ch) == -1) {
    		println("That guess isn't in the word!");
    		println("This is your progress: " + wordState);
    		wrongGuesses +=1;
    		println("Here's how many guesses you have left: " + (3 - wrongGuesses) );
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
    		wordState = newWordState;
    		println("Current word: " + newWordState);
    	}
    	//return newWordState;
    }
            
    
    // instance variables
   
    // creating instance of hangman lexicon
    private HangmanLexicon hangmanLexicon = new HangmanLexicon();

    // random generator to find random number from lexicon
    private RandomGenerator rgen = RandomGenerator.getInstance();
    
    // variable that'll choose random number from lexicon index range
    private int wordNumber = rgen.nextInt(0, hangmanLexicon.getWordCount()-1);
    
    // choosing random number from hangman lexicon and saving it to secret word variable
    private String secretWord = hangmanLexicon.getWord(wordNumber);
    
    // instance variable that will hold the guess as its built out
    private static char guessedLetter;
    private static String wordState = "";
    private static String newWordState = "";
    private static int wrongGuesses = 0;
    private static String recordOfGuesses = "";
    
    
}
