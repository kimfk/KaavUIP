package kaav.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Geometry container class for storing drawable objects
 * @author Ganryu
 */
public class GeometryContainer {
	private ArrayList<Drawable> drawables;
	private Stack<Drawable> redoStack;

	/**
	 * Creates a new GeometryContainer
	 */
	public GeometryContainer() {
		drawables = new ArrayList<Drawable>();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		drawables.add(new RealCircle(0,0,30));
		drawables.add(new RealCircle((int)width,0,30));
		drawables.add(new RealCircle(0,(int)height,30));
		drawables.add(new RealCircle((int)width,(int)height,30));
                //drawables.add(new RealRectangle(54,82,318,267));
                drawables.add(new RealLine(50,200,100,400));
                drawables.add(new RealCircle(1050,400,300));
                drawables.add(new RealRectangle(550,250,318,267));
                drawables.add(new RealTriangle(500,520,400,200));
                
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
