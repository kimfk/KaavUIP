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
		
		drawables.add(new RealCircle(0, 0, 50));
		drawables.add(new RealCircle((int)width, 0, 50));
		drawables.add(new RealCircle(0, (int)height, 50));
		drawables.add(new RealCircle((int)width, (int)height, 50));
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
		drawables.add(redoStack.get(redoStack.size()));

	}
	
	public void createCircle(float x, float y, float radius){
		drawables.add(new RealCircle((int)x,(int)y,(int)radius));
	}
        
	public void CreateSquare(float x, float y, float width,float hight ){
		drawables.add(new RealRectangle((int)x,(int)y,(int)width,(int)hight));
	}

    public void CreateTriangle(float x, float y, float width, float hight) {
         drawables.add(new RealTriangle((int)x,(int)y,(int)width,(int)hight));
    }
}