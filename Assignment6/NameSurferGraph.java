/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		//	 You fill in the rest //
		
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		//	 You fill this in //
		update();
	}
	
	/* Method: addEntry(entry) */
	/** 
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		// You fill this in //
		
		int decade = 1900;
		
		double numLines = 11;
		double startX = (getWidth() / numLines);
		double lineX = 0;
		
		double floor = getHeight() - GRAPH_MARGIN_SIZE;
		double cieling = GRAPH_MARGIN_SIZE;
		
		double height = getHeight() - (GRAPH_MARGIN_SIZE * 2);
		
		for (int i=0; i < NDECADES-1; i++) {
			GPoint point1;
			
			double rank = entry.getRank(decade);

			double point1Y;
			if (rank == 0) {
				point1Y = floor;
			} else {
				point1Y = (height/1000 * rank) + GRAPH_MARGIN_SIZE;
			}
			point1 = new GPoint(lineX, point1Y);
			GLabel label = new GLabel(entry.getName() + " " + (int)rank);
			
			GPoint point2;
			double rank2 = entry.getRank(decade+10);
			
			double point2Y;
			if (rank2 == 0) {
				point2Y = floor;
			} else {
				point2Y = (height/1000 * rank2) + GRAPH_MARGIN_SIZE;
			}
			point2 = new GPoint(lineX + startX, point2Y);
			GLabel label2 = new GLabel(entry.getName() + " " + (int)rank2);
			
			GLine line = new GLine(point1.getX(), point1.getY(), point2.getX(), point2.getY());
			
			switch (colors) {
			case 1:
				line.setColor(Color.BLACK);
				label.setColor(Color.BLACK);
				label2.setColor(Color.BLACK);
				break;
			case 2:
				line.setColor(Color.RED);
				label.setColor(Color.RED);
				label2.setColor(Color.RED);
				break;
			case 3: 
				line.setColor(Color.BLUE);
				label.setColor(Color.BLUE);
				label2.setColor(Color.BLUE);
				break;
			case 4:
				line.setColor(Color.GREEN);
				label.setColor(Color.GREEN);
				label2.setColor(Color.GREEN);
				break;
			case 5:
				line.setColor(Color.MAGENTA);
				label.setColor(Color.MAGENTA);
				label2.setColor(Color.MAGENTA);
				break;
			}
			add(line);
			add(label, point1.getX(), point1.getY());
			add(label2, point2.getX(), point2.getY());
			
			lineX += startX;
			decade += 10;
		}
		
		if (colors >= 5) {
			colors = 1;
		} else {
			colors++;
		}
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		//	 You fill this in //
		removeAll();
		drawGrid();
		drawOtherLines();
	}
	
	private void drawGrid() {
		double numLines = 11;
		double startX = (getWidth() / numLines);
		double lineY = getHeight();
		double lineX = startX;
		
		int decade = 1900;
		GLabel decadeLabel;
		double labelX = 0;
		
		for (int i=0; i < numLines; i++) {
			GLine line = new GLine(lineX, 0, lineX, lineY);
			add(line);
			lineX += startX;
			
			decadeLabel = new GLabel("" + decade);
			add(decadeLabel, labelX, (lineY - (GRAPH_MARGIN_SIZE - decadeLabel.getAscent() )/2 ) );
			labelX += startX;
			decade += 10;
		}
	}
	
	private void drawOtherLines() {
		GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		add(topLine);
		
		GLine bottomLine = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE);
		add(bottomLine);
	}
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) {
		 update(); 
	}
	public void componentShown(ComponentEvent e) { }
	
	int colors = 1;
	
}
