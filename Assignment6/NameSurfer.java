/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
		JLabel label = new JLabel("Name");
		name = new JTextField(20);
		graph = new JButton("Graph");
		clear = new JButton("Clear");
		
		add(label, SOUTH);
		add(name, SOUTH);
		add(graph, SOUTH);
		add(clear, SOUTH);
		
		name.addActionListener(this);
		addActionListeners();
		
		
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == graph) {
			println("Graph this name: " + name.getText());
			graphName(name.getText());
		} else if (source == clear) {
			println("Clearing canvas of lines");
			clearCanvas();
		}
			
	}
	
	private void graphName(String name) {
		NameSurferEntry ob = data.findEntry(name);
		println(ob);
	}
	
	private void clearCanvas() {
		
	}
	
	// CONSTANTS
	private static final String externalFileName = "names-data.txt";
	
	// instance variables
	JTextField name;
	JButton graph;
	JButton clear;
	
	NameSurferDataBase data = new NameSurferDataBase(externalFileName);
}
