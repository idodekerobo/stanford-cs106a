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
		
		double height = (getHeight() - GRAPH_MARGIN_SIZE) - GRAPH_MARGIN_SIZE;
		
		for (int i=0; i < NDECADES-1; i++) {
			GPoint point1;
			GPoint point2;
			
			for (int j=0; j<2; j++) {
				int rank = entry.getRank(decade);
				double pointY1 = height/1000 * rank;
				decade += 10;
			}
			
			
			
			/*
			
			rank = entry.getRank(decade);
			double pointY2 = height/1000 * rank;
			
			GLine line = new GLine(lineX, pointY1, lineX+startX, pointY2);
			add(line);
			decade += 10;
			
			lineX += startX;
			
			*/
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
	
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) {
		 update(); 
	}
	public void componentShown(ComponentEvent e) { }
	
	
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
}
