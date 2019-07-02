package Section3;

/*
 * A graphics program that allows the user to draw lines on the canvas. 
 * Pressing the mouse button sets the starting point for the line. Dragging the
 * mouse moves the other end point around. Releasing the mouse fixes the line at 
 * the end point and gets ready to start a new line.
 * 
 * The lines are taut - this is called rubber-banding.
 */

import acm.program.*;
import acm.graphics.*;
//import acm.util.*;
import java.awt.event.*;
//import javafx.scene.input.MouseEvent;

public class DrawingLines extends GraphicsProgram {
	
	public void run() {
		addMouseListeners();
	}
	
//	called on mouse press to create new line
	public void mousePressed(MouseEvent e) {
		println("the mouse was clicked");
		line = new GLine(e.getX(), e.getY(), e.getX(), e.getY());
		add(line);
	}
	
//	called on mouse drag to update end point
	public void mouseDragged(MouseEvent e) {
		line.setEndPoint(e.getX(), e.getY());
	}
	
//	private instance variables
	private GLine line;
	
}
