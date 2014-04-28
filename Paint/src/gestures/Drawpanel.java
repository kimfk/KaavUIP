package gestures;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;


public class Drawpanel extends JFrame implements Runnable {
	ArrayList<SimpleVector> base;
	ArrayList<SimpleVector> result;
	ArrayList<Double> kernel;
	
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
		
		g.setColor(new Color(0,255,0));
		for (int i = 0; i < base.size()-1; i++){
			g.drawLine((int)base.get(i).getX()+400, (int)base.get(i).getY()+400, 
					(int)base.get(i+1).getX()+400, (int)base.get(i+1).getY()+400);
		}
		
		g.setColor(new Color(255,0,0));
		for (int i = 0; i < result.size()-1; i++){
			g.drawLine((int)result.get(i).getX()+400, (int)result.get(i).getY()+400, 
					(int)result.get(i+1).getX()+400, (int)result.get(i+1).getY()+400);
		}
		
		g.setColor(new Color(0,0,0));
		for (int i = 0; i < kernel.size()-1; i++){
			g.drawLine(400-kernel.size()/2 + i, 600 - (int)(kernel.get(i)*150.0), 
					400-kernel.size()/2 + i+1, 600 - (int)(kernel.get(i+1)*150.0));
		}
	}

	public void setListA(ArrayList<SimpleVector> list) {
		base = list;
	}
	
	public void setListB(ArrayList<SimpleVector> list) {
		result = list;
	}
	
	public void setKernel(ArrayList<Double> list) {
		kernel = list;
	}
}
