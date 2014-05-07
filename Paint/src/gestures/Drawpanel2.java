package gestures;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * This panel is used for testing the output from the Gesturizer.
 * It is not necessary for the main application and shouldn't be used there at all.
 * @author Ganryu
 *
 */
public class Drawpanel2 extends JFrame implements Runnable {
	ArrayList<SimpleVector> base;
	ArrayList<SimpleVector> result;
	
	@Override
	public void run() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel emptyLabel = new JLabel("");
		emptyLabel.setPreferredSize(new Dimension(800, 800));
		this.getContentPane().add(emptyLabel, BorderLayout.CENTER);
		this.pack();
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (base == null) return;	
		
		// Draw unfiltered data
		g.setColor(new Color(0,255,0));
		for (int i = 0; i < base.size()-1; i++){
			g.drawLine((int)(300*base.get(i).getX())+400, (int)(300*base.get(i).getY())+400, 
					(int)(300*base.get(i+1).getX())+400, (int)(300*base.get(i+1).getY())+400);
		}
		
		// Draw filtered data
		g.setColor(new Color(255,0,0));
		for (int i = 0; i < result.size()-1; i++){
			g.drawLine((int)(300*result.get(i).getX())+400, (int)(300*result.get(i).getY())+400, 
					(int)(300*result.get(i+1).getX())+400, (int)(300*result.get(i+1).getY())+400);
		}
	}

	public void setBaseList(ArrayList<SimpleVector> list) {
		base = list;
	}
	
	public void setResultList(ArrayList<SimpleVector> list) {
		result = list;
	}
}
