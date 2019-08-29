import java.awt.event.*;
import javax.swing.* ;
import acm.graphics.*;
import acm.program.*;
import java.util.*;


public class UsingInteractors extends GraphicsProgram {
	
	public void init() {
		// setFont("Courier-24");
		addButtonsAndLabels();
		addActionListeners();
		addMouseListeners();	
	}
	
	private void addButtonsAndLabels() {
		name = new JTextField(20);
		add = new JButton("Add");
		remove = new JButton("Remove");
		clear = new JButton("Clear");
		
		add(new JLabel("Name"), SOUTH);
		add(name, SOUTH);
		add(add, SOUTH);
		add(remove, SOUTH);
		add(clear, SOUTH);
		
		name.addActionListener(this);
	}
	
	// LOWERCASE a ON actionPerformed METHOD
	// has to be a public method
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source == add) {
			println("the add button action event");
			println(source);
			// add box with text from text field inside it
			 createBox(name);
		}
		
		if (source == remove) {
			println("the remove button action event");
			println(source);
			removeBox(name);
		}
		
		if (source == clear) {
			println("the clear button action event");
			clearCanvas();
		}
	}
	
	// has to be a public event
	public void mouseDragged(MouseEvent e) {
		println("Dragged the mouse.");
		GObject currentBox = getElementAt(e.getX(), e.getY());
		
//		double moveX = getWidth() - e.getX(); 
		if (currentBox != null) {
			currentBox.setLocation(e.getX(), e.getY());
		}
	}
	
	private void createBox(JTextField name) {
		
		double boxX = (getWidth() - BOX_WIDTH)/2;
		double boxY = (getHeight() - BOX_HEIGHT)/2;
		
		GCompound box = new GCompound();
		
		GRect size = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name.getText());
		
		add(box);
		
		double labelX = (getWidth() - label.getWidth())/2;
		double labelY = (getHeight() - label.getHeight())/2;
		
		add(label, labelX, labelY);
	}
	
	private void removeBox(JTextField name) {
		println("Remove box");
		// GRect box = 
		
		//GRect boxToRemove = name.getText();
	}
	
	private void clearCanvas() {
		removeAll();
	}
	
	// constants
	JTextField name;
	JButton add;
	JButton remove;
	JButton clear;
	
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	
}
