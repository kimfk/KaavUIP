package kaav.main;

import java.awt.Color;
import java.awt.Graphics;

public class RealCircle implements Drawable{
	int x;
	int y;
	int radius;

	public RealCircle(int x, int y, int r){
		this.x = x;
		this.y = y;
		this.radius = r;
	}
	
	@Override
	public void draw(Graphics g, double width, double height) {
                g.setColor( Color.BLUE);
		g.drawOval(x-radius/2, y-radius/2, radius, radius);
	}

	@Override
	public void scale(float factor) {
		// TODO Auto-generated method stub
	}

	@Override
	public void translate(float x, float y) {
		// TODO Auto-generated method stub
	}
}
