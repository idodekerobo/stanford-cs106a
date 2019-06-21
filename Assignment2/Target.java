/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
		/* You fill this in. */
		drawOuterRedCircle();
		drawWhiteCircle();
		drawInnerRedCircle();
	}
	
	private static final int oneInch = 72;
	
//	find center of window
	
	int x = getWidth()/2;
	int y = getHeight()/2;
	
	double radiusWhiteCircle = oneInch*0.65;
	double radiusOuterRedCircle = oneInch;
	double radiusInnerRedCircle = oneInch*0.3;
	
	private void drawOuterRedCircle() {
//		want to be centered so move coordinates over the width/height of the radius and make width and height to two pixels
		GOval outerRed = new GOval(getWidth()/2 - radiusOuterRedCircle, getHeight()/2 - radiusOuterRedCircle, oneInch*2, oneInch*2);
		outerRed.setFilled(true);
		outerRed.setColor(Color.red);
		add(outerRed);
	}
	
	private void drawWhiteCircle() {
		GOval whiteCircle = new GOval(getWidth()/2 - radiusWhiteCircle, getHeight()/2 - radiusWhiteCircle, radiusWhiteCircle*2, radiusWhiteCircle*2);
		whiteCircle.setFilled(true);
		whiteCircle.setColor(Color.white);
		add(whiteCircle);
	}
	
	private void drawInnerRedCircle() {
		GOval innerRed = new GOval(getWidth()/2 - radiusInnerRedCircle, getHeight()/2 - radiusInnerRedCircle, radiusInnerRedCircle*2, radiusInnerRedCircle*2);
		innerRed.setFilled(true);
		innerRed.setColor(Color.red);
		add(innerRed);
	}

}
