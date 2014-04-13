package kaav.main;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Geometry container class for storing drawable objects
 * @author Ganryu
 *
 */
public class GeometryContainer {
	private ArrayList<Drawable> drawables;
	private Stack<Drawable> redoStack;
	
	/**
	 * Creates a new GeometryContainer
	 */
	public GeometryContainer(){
		drawables = new ArrayList<Drawable>();
	}

	/**
	 * Returns all drawables stored in the container
	 * @return
	 */
	public ArrayList<Drawable> getDrawables(){
		return drawables;
	}
	
	/**
	 * This is a trivial implementation of Undo
	 */
	public void undo(){
		redoStack.push(drawables.get(drawables.size()));
		drawables.remove(drawables.size());
	}
	
	/**
	 * This is a trivial implementation of Redo
	 */
	public void redo(){
		
	}
}
