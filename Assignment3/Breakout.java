/*
 * File: Breakout.java
 * -------------------
 * Name: Idode Kerobo
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
	public static int NTURNS = 3;

	
//	RUNS THE PROGRAM
//====================================================================================
	public void run() {
		// Set the window's title bar text
		setTitle("CS 106A Breakout");

		// Set the canvas size.  In your code, remember to ALWAYS use getWidth()
		// and getHeight() to get the screen dimensions, not these constants!
		setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
//		setCanvasSize(getWidth(), getHeight());
		
		/* You fill this in, along with any subsidiary methods */
		createGame();
		while (!gameOver()) {
			moveBall();
		}
		gameOverLabel();
		
	}
//====================================================================================

	private void createGame() {
		setUpBricks();
		setUpPaddle();
		setUpBall();
		addMouseListeners();
	}
	
	private Boolean gameOver() {
		if (!(NTURNS > 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	private void checkForCollisions() {
		GRect collider = getCollidingObject();
		if (collider != null) {
			if (collider == paddle) {
					vy *= -1;	
				println("vx: " + vx + ", vy: " + vy + ", ballX: " + ball.getX() + ", ballY: " +ball.getY());
			} else {
				bounceClip.play();
				remove(collider);
				num_bricks--;
				println(num_bricks);
				vy *= -1;
			}
		}
		if (ball.getY() > getHeight() - (BALL_RADIUS*2)) {
			remove(ball);
			
		}
		else if (ball.getX() + (BALL_RADIUS*2) > getWidth()) {
			vx *= -1;
		} else if (ball.getX() < 0) {
			vx *= -1;
		} else if (ball.getY() < 0) {
			vy *= -1;
		}
	}
	
	private void moveBall() {
		addTurnsLabel();
		while (ballOnScreen()) {
			ball.move(vx, vy);
			checkForCollisions();
			pause(DELAY);
			
			if (num_bricks == 0) {
				winGame();
			}
			
		}
		
	}
	
	private void gameOverLabel() {
		GLabel gameOver = new GLabel("Game Over :(");
		gameOver.setFont("Courier-48");
		add(gameOver, (getWidth() - gameOver.getWidth())/2, (getHeight() - gameOver.getAscent())/2);
	}
	
	private Boolean winGame() {
		if (num_bricks == 0) {
			GLabel gameWon = new GLabel("You Won! :)");
			gameWon.setFont("Courier-48");
			add(gameWon, (getWidth() - gameWon.getWidth())/2, (getHeight() - gameWon.getAscent())/2); 
		}
		return null;
	}
	
	private void setUpBricks() {
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
				num_bricks++;
				initialX += BRICK_WIDTH+BRICK_SEP;
			}
			initialY += BRICK_HEIGHT+BRICK_SEP;
		}
	}
	
	private void setUpPaddle() {
	//	paddle and paddle (x,y) local variable
		double paddleX = (getWidth() - PADDLE_WIDTH)/2;
		double paddleY = (getHeight() - PADDLE_Y_OFFSET);
		paddle = new GRect(paddleX, paddleY, PADDLE_WIDTH, PADDLE_HEIGHT);
		paddle.setFilled(true);
		add(paddle);
	}
	
	public void mouseMoved(MouseEvent e) {
		double paddleY = getHeight() - PADDLE_Y_OFFSET;
		
		if (e.getX() > (getWidth() - PADDLE_WIDTH)) {
			paddle.setLocation((getWidth() - PADDLE_WIDTH), paddleY);
		} else if (e.getX() < 0) {
			paddle.setLocation(0, paddleY);
		} else {
			paddle.setLocation(e.getX(), paddleY);
		}
	}
	
	private void setUpBall() {
		double ballX = (getWidth() - (BALL_RADIUS*2))/2;
		double ballY = (getHeight() - (BALL_RADIUS*2))/2;
		ball = new GOval(ballX, ballY, BALL_RADIUS*2, BALL_RADIUS*2);
		ball.setFilled(true);
		add(ball);
	}
	
	private GRect getCollidingObject() {
		GRect obj;
		if (getElementAt(ball.getX(), ball.getY()) != null) {
			obj = getElementAt(ball.getX(), ball.getY());
			return obj;
		} else if (getElementAt(ball.getX()+ BALL_RADIUS*2, ball.getY()) != null) {
			obj = getElementAt(ball.getX()+ BALL_RADIUS*2, ball.getY());
			return obj;
		} else if (getElementAt(ball.getX(), ball.getY() + BALL_RADIUS*2) != null) {
			obj = getElementAt(ball.getX(), ball.getY() + BALL_RADIUS*2);
			return obj;
		} else if (getElementAt((ball.getX()+(BALL_RADIUS*2)),ball.getY() + BALL_RADIUS*2) != null) {
			obj = getElementAt((ball.getX()+(BALL_RADIUS*2)),ball.getY() + BALL_RADIUS*2);
			return obj;
		} else {
			return null;
		}		
	}
	
	private Boolean ballOnScreen() {
		if (ball.getY() >= getHeight()) {
			remove(ball);
			remove(turns);
			NTURNS--;
			setUpBall();
			pause(1000);
			return false;
		} else {
			return true;
		}
	}
	
	private void addTurnsLabel() {
		turns = new GLabel("Turns Left: "+ NTURNS);
		turns.setFont("Times-15");
		add(turns, getWidth() - (turns.getWidth()), turns.getAscent());
//		if (ballOnScreen() == false) {
//			remove(turns);
//		}
	}
	
	private double randomVX(double vx) {
		if (rgen.nextBoolean(0.5)) {
			vx = -(rgen.nextDouble(1.0, 3.0));
		}
		return vx;
	}
	
//	private instance variables~
//==================================================================================
	//	need instance variable of paddle so the program can track it across methods
	private GRect paddle;
	private GOval ball;
	private GLabel turns;
	private int num_bricks = 0;
	AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private double vx = randomVX(rgen.nextDouble(1.0, 3.0));
	
	private double vy = 3.0;	
}

