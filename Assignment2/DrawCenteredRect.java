/*
 * File: DrawCenteredRect.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the DrawCenteredRect problem.
 */

import acm.graphics.*; // to write a graphics program
import acm.program.*; // all programs written in this class needs this
import java.awt.*; // to use colors 

public class DrawCenteredRect extends GraphicsProgram {
	
	/** Size of the centered rect */
	private static final int WIDTH = 350;
	private static final int HEIGHT = 270;

	public void run() {
		/* You fill this in. */
//		println("Width Midpoint of Canvas: " + halfWidth);
//		println("Height Midpoint of Canvas: " + halfHeight);
		
		double canvasWidth = getWidth();
		println(canvasWidth);
		double canvasHeight = getHeight();
		println(canvasHeight);
		
		double xCoord = (canvasWidth - WIDTH)/2;
		double yCoord = (canvasHeight - HEIGHT)/2;
		
		GRect blueRectangle = new GRect(xCoord, yCoord, 350, 270);
		
		blueRectangle.setFilled(true);
	
		blueRectangle.setColor(Color.BLUE);
		
		println("Blue Rectangle Width: "+ blueRectangle.getWidth());
		println("Blue Rectangle Height: "+ blueRectangle.getHeight());
		
		add(blueRectangle);
		
	}
	
	double halfWidth = getWidth()/2;
	double halfHeight = getHeight()/2;
	
	
	
}

