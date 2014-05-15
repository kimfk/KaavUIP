/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kaav.main;

import java.awt.Graphics;

/**
 *
 * @author user
 */
public class RealTriangle implements Drawable{
     int x;               //x - the x coordinate of the rectangle to be drawn.
     int y;                //y - the y coordinate of the rectangle to be drawn.
     int tWidth;            //width - the width of the rectangle to be drawn.
     int tHeight;           //height - the height of the rectangle to be drawn.
      
     int[] xPoints = {x , tWidth, tHeight}; //refelct x dragged and y
     int[] yPoints = {y, tHeight, tWidth}; //refelct x dragged and y
            
                               
				
    
    public RealTriangle(int x, int y, int w, int h){
		this.x = x;
		this.y = y;
                this.tWidth = w;
		this.tHeight = h;
	}
    
    @Override
    public void draw(Graphics g, double width, double height) {
           g.drawPolygon(xPoints,yPoints,3);
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
