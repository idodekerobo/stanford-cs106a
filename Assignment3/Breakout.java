/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	// Dimensions of the canvas, in pixels
	// These should be used when setting up the initial size of the game,
	// but in later calculations you should use getWidth() and getHeight()
	// rather than these constants for accurate size information.
	public static final double CANVAS_WIDTH = 420;
	public static final double CANVAS_HEIGHT = 600;

	// Number of bricks in each row
	public static final int NBRICK_COLUMNS = 10;

	// Number of rows of bricks
	public static final int NBRICK_ROWS = 10;

	// Separation between neighboring bricks, in pixels
	public static final double BRICK_SEP = 4;

	// Width of each brick, in pixels
	public static final double BRICK_WIDTH = Math.floor(
			(CANVAS_WIDTH - (NBRICK_COLUMNS + 1.0) * BRICK_SEP) / NBRICK_COLUMNS);

	// Height of each brick, in pixels
	public static final double BRICK_HEIGHT = 8;

	// Offset of the top brick row from the top, in pixels
	public static final double BRICK_Y_OFFSET = 70;

	// Dimensions of the paddle
	public static final double PADDLE_WIDTH = 60;
	public static final double PADDLE_HEIGHT = 10;

	// Offset of the paddle up from the bottom 
	public static final double PADDLE_Y_OFFSET = 30;

	// Radius of the ball in pixels
	public static final double BALL_RADIUS = 10;

	// The ball's vertical velocity.
	public static final double VELOCITY_Y = 3.0;

	// The ball's minimum and maximum horizontal velocity; the bounds of the
	// initial random velocity that you should choose (randomly +/-).
	public static final double VELOCITY_X_MIN = 1.0;
	public static final double VELOCITY_X_MAX = 3.0;

	// Animation delay or pause time between ball moves (ms)
	public static final double DELAY = 1000.0 / 60.0;

	// Number of turns 
	public static final int NTURNS = 3;

	public void run() {
		// Set the window's title bar text
		setTitle("CS 106A Breakout");

		// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
		// and getHeight() to get the screen dimensions, not these constants!
		setCanvasSize(getWidth(), getHeight());
		
		println(getWidth()+","+getHeight());

		/* You fill this in, along with any subsidiary methods */
		
		createGame();
	}	

	public void createGame() {
		setUpBricks();
		setUpPaddle();
	}
	
	public void setUpBricks() {
		double initialY = BRICK_Y_OFFSET;
		for (int i = 0; i < NBRICK_ROWS; i++) {
			double initialX = (getWidth() - ((BRICK_WIDTH + BRICK_SEP)*NBRICK_COLUMNS))/2;
			for (int j = 0; j < NBRICK_COLUMNS; j++) {
				GRect brick = new GRect(initialX, initialY, BRICK_WIDTH, BRICK_HEIGHT);
				brick.setFilled(true);
				
				switch (i) {
				case 0:
					brick.setFillColor(Color.RED);
					break;
				case 1:
					brick.setFillColor(Color.RED);
					break;
				case 2:
					brick.setFillColor(Color.ORANGE);
					break;
				case 3: 
					brick.setFillColor(Color.ORANGE);
					break;
				case 4:
					brick.setFillColor(Color.YELLOW);
					break;
				case 5:
					brick.setFillColor(Color.YELLOW);
					break;
				case 6:
					brick.setFillColor(Color.GREEN);
					break;
				case 7:
					brick.setFillColor(Color.GREEN);
					break;
				case 8: 
					brick.setFillColor(Color.CYAN);
					break;
				case 9:
					brick.setFillColor(Color.CYAN);
					break;
				default: 
					break;
				}
				
				add(brick);
				initialX += BRICK_WIDTH+BRICK_SEP;
				println(initialX);
			}
			initialY += BRICK_HEIGHT+BRICK_SEP;
		}
	}
	
	public void setUpPaddle() {
		
	}
}
