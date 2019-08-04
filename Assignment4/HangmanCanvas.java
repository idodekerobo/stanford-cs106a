/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		/* You fill this in */
		removeAll();
		
		GLine beam = new GLine(SCAFFOLD_WIDTH, upperLeftCorner, SCAFFOLD_WIDTH, (upperLeftCorner + SCAFFOLD_HEIGHT));
		add(beam);
		
		GLine crane = new GLine(SCAFFOLD_WIDTH, upperLeftCorner, SCAFFOLD_WIDTH + BEAM_LENGTH, upperLeftCorner);
		add(crane);
		
		GLine rope = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH, upperLeftCorner, SCAFFOLD_WIDTH + BEAM_LENGTH, upperLeftCorner + ROPE_LENGTH);
		add(rope);
	}

/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
		/* You fill this in */
		
		wordProgress = new GLabel(Hangman.wordState, SCAFFOLD_WIDTH, SCAFFOLD_HEIGHT + BEAM_LENGTH);
		wordProgress.setFont("Courier-24");
		add(wordProgress);
		
		GLabel guesses = new GLabel(Hangman.recordOfGuesses, SCAFFOLD_WIDTH, SCAFFOLD_HEIGHT + BEAM_LENGTH + 50);
		guesses.setFont("Courier-24");
		add(guesses);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
		/* You fill this in */
		putBodyTogether(Hangman.wrongGuesses);
	}
	
	private void putBodyTogether(int index) {
		switch (index) {
			case 1: drawHead();
			break;
			case 2: drawTorso();
			break;
			case 3: drawLeftArm();
			break;
			case 4: drawRightArm();
			break;
			case 5: drawLeftLeg();
			break;
			case 6: drawRightLeg();
			break;
			case 7: drawLeftFoot();
			break;
			case 8: drawRightFoot();
			break;
		}
	}
	
	private void drawHead() {
		GOval head = new GOval(headX, headY, HEAD_RADIUS*2, HEAD_RADIUS*2);
		add(head);
	}
	
	private void drawTorso() {
		GLine torso = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH, headY + HEAD_RADIUS*2, SCAFFOLD_WIDTH + BEAM_LENGTH, headY + HEAD_RADIUS*2 + BODY_LENGTH);
		add(torso);
	}
	
	private void drawLeftArm() {
		GLine arm = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH, headY + HEAD_RADIUS*2 + ROPE_LENGTH*2, SCAFFOLD_WIDTH + BEAM_LENGTH - UPPER_ARM_LENGTH, headY + HEAD_RADIUS*2 + ROPE_LENGTH*2);
		GLine lowerArm = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH - UPPER_ARM_LENGTH, headY + HEAD_RADIUS*2 + ROPE_LENGTH*2, SCAFFOLD_WIDTH + BEAM_LENGTH - UPPER_ARM_LENGTH, headY + HEAD_RADIUS*2 + ROPE_LENGTH*2 + LOWER_ARM_LENGTH);
		add(arm);
		add(lowerArm);
	}
	
	private void drawRightArm() {
		GLine arm = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH, headY + HEAD_RADIUS*2 + ROPE_LENGTH*2, SCAFFOLD_WIDTH + BEAM_LENGTH + UPPER_ARM_LENGTH, headY + HEAD_RADIUS*2 + ROPE_LENGTH*2);
		GLine lowerArm = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH + UPPER_ARM_LENGTH, headY + HEAD_RADIUS*2 + ROPE_LENGTH*2, SCAFFOLD_WIDTH + BEAM_LENGTH + UPPER_ARM_LENGTH, headY + HEAD_RADIUS*2 + ROPE_LENGTH*2 + LOWER_ARM_LENGTH);
		add(arm);
		add(lowerArm);
	}
	
	private void drawLeftLeg() {
		GLine leftHip = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH, headY + HEAD_RADIUS*2 + BODY_LENGTH, SCAFFOLD_WIDTH + BEAM_LENGTH - HIP_WIDTH, headY + HEAD_RADIUS*2 + BODY_LENGTH);
		GLine leftLowerLeg = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH - HIP_WIDTH, headY + HEAD_RADIUS*2 + BODY_LENGTH, SCAFFOLD_WIDTH + BEAM_LENGTH - HIP_WIDTH, headY + HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH);
		add(leftHip);
		add(leftLowerLeg);
	}
	
	private void drawRightLeg() {
		GLine rightHip = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH, headY + HEAD_RADIUS*2 + BODY_LENGTH, SCAFFOLD_WIDTH + BEAM_LENGTH + HIP_WIDTH, headY + HEAD_RADIUS*2 + BODY_LENGTH);
		GLine rightLowerLeg = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH + HIP_WIDTH, headY + HEAD_RADIUS*2 + BODY_LENGTH, SCAFFOLD_WIDTH + BEAM_LENGTH + HIP_WIDTH, headY + HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH);
		add(rightHip);
		add(rightLowerLeg);
	}
	
	private void drawLeftFoot() {
		GLine leftFoot = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH - HIP_WIDTH, headY + HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH, SCAFFOLD_WIDTH + BEAM_LENGTH - HIP_WIDTH - FOOT_LENGTH, headY + HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH);
		add(leftFoot);
	}
	
	private void drawRightFoot() {
		GLine rightFoot = new GLine(SCAFFOLD_WIDTH + BEAM_LENGTH + HIP_WIDTH, headY + HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH, SCAFFOLD_WIDTH + BEAM_LENGTH + HIP_WIDTH + FOOT_LENGTH, headY + HEAD_RADIUS*2 + BODY_LENGTH + LEG_LENGTH);
		add(rightFoot);
	}

/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;
	
	private static final int upperLeftCorner = 50;
	private static final int SCAFFOLD_WIDTH = 100;
	
	private int headX = (SCAFFOLD_WIDTH + BEAM_LENGTH) - HEAD_RADIUS;
	private int headY = upperLeftCorner + ROPE_LENGTH;
	
	
	public static GLabel wordProgress;

}
