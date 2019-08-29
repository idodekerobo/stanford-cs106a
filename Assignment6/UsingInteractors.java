import java.awt.event.*;
import javax.swing.* ;
import acm.graphics.*;
import acm.program.*;
import java.util.*;


public class UsingInteractors extends GraphicsProgram {
	
	public void init() {
		// setFont("Courier-24");
		objects = new HashMap<String, GObject>();
		pt = new GPoint();
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
		} else if (source == remove) {
			println("the remove button action event");
			println(source);
			removeBox(name);
		} else if (source == clear) {
			println("the clear button action event");
			clearCanvas();
		}
	}
	
	public void mousePressed(MouseEvent e) {
		if (pt != null) {
			
			GObject currentBox = getElementAt(e.getX(), e.getY());
			currentBox.sendToFront();
			
			println("new point");
			pt.setLocation(e.getX(), e.getY());
		}
	}
	
	// has to be a public event
	public void mouseDragged(MouseEvent e) {
		println("Dragged the mouse.");
		GObject currentBox = getElementAt(e.getX(), e.getY());
		 
		if (currentBox != null) {
			double moveX = e.getX() - pt.getX();
			double moveY = e.getY() - pt.getY();
			currentBox.move(moveX, moveY);
		}
	}
	
	private void createBox(JTextField name) {
		
		double boxX = (getWidth() - BOX_WIDTH)/2;
		double boxY = (getHeight() - BOX_HEIGHT)/2;
		
		GCompound box = new GCompound();
		
		GRect sq = new GRect(BOX_WIDTH, BOX_HEIGHT);
		GLabel label = new GLabel(name.getText());
		
		double labelX = (getWidth() - label.getWidth())/2;
		double labelY = (getHeight() - label.getAscent())/2;
		
		box.add(sq, boxX, boxY);
		box.add(label, labelX, labelY);
		
		//add GCompound to hashmap for tracking across the program
		objects.put(name.getText(), box);
		
		// add GCompound to the canvas
		add(box);
	}
	
	private void removeBox(JTextField name) {
		println("Remove box");
		GObject box = objects.get(name.getText());
		
		if (box != null) {
			remove(box);
		}
	}
	
	private void clearCanvas() {
		//removeAll();
		
		// only grab the keys of the objects hashmap
		Iterator<String> it = objects.keySet().iterator();
		
		// only removing the objects from hashmap so other shapes on canvas aren't effected
		while (it.hasNext()) {
			String name = it.next();
			GObject box = objects.get(name);
			remove(box);
			println("Removed the " + name);
		}
		
		
	}
	
	// constants
	JTextField name;
	JButton add;
	JButton remove;
	JButton clear;
	
	//hashmap to store keys of canvas objects
	HashMap<String, GObject> objects;
	
	//point to keep track of where to move boxes
	GPoint pt;
	
	// box height/width constants
	private static final double BOX_WIDTH = 120;
	private static final double BOX_HEIGHT = 50;
	
}
