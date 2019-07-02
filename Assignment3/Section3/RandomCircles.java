package Section3;

/*
 * File: RandomCircles.java
 * ---------------------------
 * This program draws a set of ten circles with different sizes,
 * positions, and colors.
 * 
 * Ea/ circle should have a randomly chosen color, random radius
 * between 5 and 50px, and random position on canvas - subject
 * to condition that entire circle must fit on canvas w/o
 * extending pass the edge.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

public class RandomCircles extends GraphicsProgram {	
	
//	number of circles
	private final int NCIRCLES = 20;
	
//	minimum diameter of circle
	private final int MIN_DIAMETER = 10;
	
//	max diameter of circle
	private final int MAX_DIAMETER = 100;

	
	public void run() {
		drawTenCircles();
	}
	
	private void drawTenCircles() {
		for (int i=0; i < NCIRCLES; i++) {
			drawCircle();
		}
	}
	
//	when you use getWidth() or getHeight() in x, y coordinates they have to be doubles or it won't work
	
	private void drawCircle() {
//		RANDOM GENERATORS GETTING DIAMETER, X, AND Y
		int randomDiameter = rgen.nextInt(MIN_DIAMETER, MAX_DIAMETER);
		int randomX = rgen.nextInt(0, (754 - randomDiameter));
		int randomY = rgen.nextInt(0, (492 - randomDiameter));
		
//		ADD CODE DRAWING ONE RANDOM CIRCLE
		GOval circle = new GOval(randomX, randomY, randomDiameter, randomDiameter);
		circle.setFilled(true);
		circle.setColor(rgen.nextColor());
		add(circle);
	}
	
//	RANDOM GENERATOR
	private static RandomGenerator rgen = RandomGenerator.getInstance();


}
