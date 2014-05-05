package kaav.main;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * This class renders the objects to screen.
 * @author Ganryu
 *
 */
public class RenderingEngine extends JPanel{
	private GeometryContainer container;	
	
	/**
	 * Creates a new RenderingEngine with the given GeometryContainer. 
	 * @param container
	 */
	public RenderingEngine(GeometryContainer container){
		this.container = container;
	}
	
	/**
	 * Iterates through all objects in the container and draws them in that order.
	 */
	public void draw(){
		ArrayList<Drawable> drawables = container.getDrawables();
		for (Drawable d: drawables){
			d.draw();
		}
	}
}