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

	public void init() {
		
		canvas = new HangmanCanvas();
		add(canvas);
		canvas.reset();
		
		
	}
	
    public void run() {
		/* You fill this in */
 
    	setupGame();
    	while (wrongGuesses < guessesAllowed && ((wordState.indexOf('_') != -1))) {
    		println("Here's the amount of guesses you have remaining: " + (guessesAllowed - wrongGuesses));
    		takeGuess();
        	makeGuess();
        	if (wordState.indexOf('_') == -1) {
        		println();
        		println("Congrats! You guessed the word!");
        	} else if (wrongGuesses >= guessesAllowed) {
        		println();
        		println("Damn, maybe you'll get it next time!");
        	}
        	canvas.displayWord(wordState);
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
    	println();
    	println("Here's your first hint: " + wordState);
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
    				println();
    				println("You already guessed that letter!");
        		} else {
        			break;
        		}
    			
    		}
    		println();
    		println("Remember: you can only guess one character at a time and it has to be a letter. Try again.");
    		guessLetter = readLine("Guess your letter: ");
    	}
    	guessLetter = guessLetter.toUpperCase();
		guessedLetter = guessLetter.charAt(0);
		recordOfGuesses += guessedLetter;
		println();
		println("This is your guess: " + guessedLetter);
    }
    
    // compare letter to secret word and current word state
    private void makeGuess() {
    	char ch = guessedLetter;
    	newWordState = "";
    	
    	
    	// test if the ch is contained in the secret word, if not add 1 to wrong guesses
    	if (secretWord.indexOf(ch) == -1) {
    		println();
    		println("That guess isn't in the word!");
    		println("This is your progress: " + wordState);
    		wrongGuesses +=1;
    		
    		/* add a body part to the hangman */
    		canvas.noteIncorrectGuess(ch);
    		
    		println();
    		println("Here's how many guesses you have left: " + (guessesAllowed - wrongGuesses) );
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
    		println();
    		println("Current word: " + newWordState);
    	}
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
    
    public static String wordState = "";
    
    private static String newWordState = "";
    public static int wrongGuesses = 0;
    private static int guessesAllowed = 8;
    
    public static String recordOfGuesses = "";

    
    // instantiating Hangman Canvas instance
    private HangmanCanvas canvas;
    
}
