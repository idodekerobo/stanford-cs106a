/*
 * File: MouseReporter.java
 * -----------------------------
 * Output the location of the mouse to a label on the
 * screen. Change the color of the label to red when
 * the mouse touches it.
 */

import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.*;
import acm.program.*;

public class MouseReporter extends GraphicsProgram {

	// A constant for the x value of the label
	private static final int INDENT = 20;
	
	// This variable is visible to the entire program
	// It is called an "instance" variable
	private GLabel label = new GLabel("");
	
	public void run() {	
		// this code already adds the label to the screen!
		// run it to see what it does.
		label.setFont("Courier-24");
		label.setColor(Color.BLUE);
		
		// this setLabel method takes in a "String" 
		// you can concatenate integers and commas as such:
		label.setLabel(0 + "," + 0);
		
		// add the label to the screen!
		add(label, INDENT, getHeight()/2);
		
		addMouseListeners();
	}
	
	public void mouseMoved(MouseEvent e) {
		// changing label to the location of the mouse
		label.setLabel(e.getX()+","+e.getY());
		
		// creating a new variable, red label, that returns the object if the mouse hovers over one
		GLabel redLabel = getElementAt(e.getX(), e.getY());
		
		// if red label is not null change to red, else change original label variable to blue
		if (redLabel != null) {
			redLabel.setColor(Color.RED);
		} else {
			label.setColor(Color.BLUE);
		}
	}

}
