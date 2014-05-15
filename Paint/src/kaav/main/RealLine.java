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
public class RealLine  implements Drawable{
     int x1;               //x1 - the first point's x coordinate.
     int y1;               //y1 - the first point's y coordinate.
     int x2;              //x2 - the second point's x coordinate.
     int y2;           //y2 - the second point's y coordinate.
      
            
                               
				
    
    public RealLine(int start_x, int start_y, int end_x, int end_y){
		this.x1 = start_x;
		this.y1 = start_y;
                this.x2 = end_x;
		this.y2 = end_y;
	}
    
    @Override
    public void draw(Graphics g, double width, double height) {
           g.setColor( Color.GREEN );
           g.drawLine(x1, y1,x2,y2);
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