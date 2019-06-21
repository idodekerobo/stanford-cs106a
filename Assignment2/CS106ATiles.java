/*
 * File: CS106ATiles.java
 * Name: 
 * Section Leader: 
 * ----------------------
 * This file is the starter file for the CS106ATiles problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class CS106ATiles extends GraphicsProgram {
	
	/** Amount of space (in pixels) between tiles */
	private static final int TILE_SPACE = 20;
	private static final int TILE_WIDTH = 150;
	private static final int TILE_HEIGHT = 75;

	public void run() {
		/* You fill this in. */
		println(getWidth());
		println(getHeight());
		
		double x1 = getWidth()/2 - (TILE_WIDTH+TILE_SPACE);
		double y1 = getHeight()/2 - (TILE_HEIGHT + TILE_SPACE);
		
//		double x2 = getWidth()/2 + TILE_SPACE;		
		double x2 = x1 + TILE_WIDTH + TILE_SPACE;
		
//		double y2 = getHeight()/2 + TILE_SPACE;
		double y2 = y1 + TILE_HEIGHT + TILE_SPACE;

		
		GRect topLeft = new GRect(x1, y1, TILE_WIDTH, TILE_HEIGHT);
		add(topLeft);
		
		GRect topRight = new GRect(x2, y1, TILE_WIDTH, TILE_HEIGHT);
		add(topRight);
		
		GRect bottomLeft = new GRect(x1, y2, TILE_WIDTH, TILE_HEIGHT);
		add(bottomLeft);
		
		GRect bottomRight = new GRect(x2, y2, TILE_WIDTH, TILE_HEIGHT);
		add(bottomRight);
		
//		label width = 46
//		label height = 15
		
		double x1Label = x1 + (TILE_WIDTH - 46)/2;
//		x1 + (TILE_WIDTH - 46)/2
		double y1Label = y1 + (TILE_HEIGHT + 15)/2;
		
		GLabel topLeftLabel = new GLabel("CS106A", x1Label, y1Label);
		add(topLeftLabel);
		println(topLeftLabel.getWidth());
		println(topLeftLabel.getHeight());
		
		double x2Label = x1Label + TILE_WIDTH + TILE_SPACE;
		double y2Label = y1Label + TILE_HEIGHT + TILE_SPACE;
		
		GLabel topRightLabel = new GLabel("CS106A", x2Label, y1Label);
		add(topRightLabel);
		
		GLabel bottomLeftLabel = new GLabel("CS106A", x1Label, y2Label);
		add(bottomLeftLabel);
		
		GLabel bottomRightLabel = new GLabel("CS106A", x2Label, y2Label);
		add(bottomRightLabel);
	}
	
	
}

