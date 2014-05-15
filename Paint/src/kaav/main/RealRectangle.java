/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaav.main;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author user
 */
public class RealRectangle implements Drawable{
     int x;               //x - the x coordinate of the rectangle to be drawn.
     int y;                //y - the y coordinate of the rectangle to be drawn.
     int rWidth;            //width - the width of the rectangle to be drawn.
     int rHeight;           //height - the height of the rectangle to be drawn.

    
    public RealRectangle(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
                this.rWidth = w;
		this.rHeight = h;
	}
    
    @Override
    public void draw(Graphics g, double width, double height) {
          g.setColor( Color.BLACK );
           g.drawRect(x, y, rWidth, rHeight);
    }

    @Override
    public void scale(float factor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void translate(float x, float y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
