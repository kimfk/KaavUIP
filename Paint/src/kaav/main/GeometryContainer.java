package kaav.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Geometry container class for storing drawable objects
 * 
 * @author Ganryu
 * 
 */
public class GeometryContainer {
	private ArrayList<Drawable> drawables;
	private Stack<Drawable> redoStack;

	/**
	 * Creates a new GeometryContainer
	 */
	public GeometryContainer() {
		drawables = new ArrayList<Drawable>();
		drawables.add(new RealCircle(0,0,30));
		drawables.add(new RealCircle(1920,0,30));
		drawables.add(new RealCircle(0,1200,30));
		drawables.add(new RealCircle(1920,1200,30));
	}

	/**
	 * Returns all drawables stored in the container
	 * 
	 * @return
	 */
	public ArrayList<Drawable> getDrawables() {
		return drawables;
	}

	/**
	 * This is a trivial implementation of Undo
	 */
	public void undo() {
		redoStack.push(drawables.get(drawables.size()));
		drawables.remove(drawables.size());
	}

	/**
	 * This is a trivial implementation of Redo
	 */
	public void redo() {

	}
}
