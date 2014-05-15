package kaav.main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class renders the objects to screen.
 * @author Ganryu
 *
 */
public class RenderingEngine extends JFrame {
	private GeometryContainer container;	
	private double width;
	private double height;

	/**
	 * Creates a new RenderingEngine with the given GeometryContainer. 
	 * @param container
	 */
	public RenderingEngine(GeometryContainer container){
		this.container = container;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screenSize.getWidth();
		height = screenSize.getHeight();
		System.out.println("Width: " + width);
		System.out.println("Height: " + height);
	}
	
	/**
	 * Draw the panel and all components.
	 */
	@Override
	public void paint(Graphics g) {
		//g.setColor(new Color(100,100,100));
                g.setColor(Color.)
		g.fillRect(0, 0, (int)width, (int)height);
		
		g.setColor(new Color(255,255,0));
		for (Drawable d : container.getDrawables()){
			d.draw(g, width, height);
		}
	}
}