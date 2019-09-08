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
		graphButt = new JButton("Graph");
		clear = new JButton("Clear");
		graph = new NameSurferGraph();
		
		add(label, SOUTH);
		add(name, SOUTH);
		add(graphButt, SOUTH);
		add(clear, SOUTH);
		add(graph);
		
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
		
		if (source == graphButt) {
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
		graph.addEntry(ob);
		
		// println(ob.getName());
		//println(ob.toString());
		/* int decade = 1900;
		for (int i=0; i < 11; i++) {
			println(ob.getRank(decade));
			decade += 10;
		} */
	}
	
	private void clearCanvas() {
		
	}
	
	// CONSTANTS
	private static final String externalFileName = "names-data.txt";
	
	// instance variables
	private static JTextField name;
	private static JButton graphButt;
	private static JButton clear;
	
	private NameSurferDataBase data = new NameSurferDataBase(externalFileName);
	private NameSurferGraph graph;
	
}
