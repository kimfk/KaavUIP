package kaav.main;
import java.awt.Graphics;
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
	 * Draw the panel and all components.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ArrayList<Drawable> drawables = container.getDrawables();
		for (Drawable d: drawables){
			d.draw(g);
		}
	}
}