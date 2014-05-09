package kaav.main;


import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *  Note: Normally the ButtonPanel and DrawingArea would not be static classes.
 *  This was done for the convenience of posting the code in one class and to
 *  highlight the differences between the two approaches. All the differences
 *  are found in the DrawingArea class.
 */
public class Triangle extends JComponent implements Drawable
{
      private ArrayList<ColoredTriangle> coloredTriangles = new ArrayList<ColoredTriangle>();
      private Point startPoint = null;
      private Point endPoint = null;

	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI()
	{
		

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Kaav");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                frame.setLayout(new BorderLayout());
                frame.add(new Triangle(), BorderLayout.CENTER);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
	}

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void scale(float factor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotate(float radians) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void translate(float x, float y) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	
	//static class DrawingArea extends JPanel
        public Triangle()
	{
		
			setBackground(Color.WHITE);

			MyMouseListener ml = new MyMouseListener();
			addMouseListener(ml);
			addMouseMotionListener(ml);
		}

		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);

			//  Custom code to paint all the Rectangles from the List

			Color foreground = g.getColor();

			g.setColor( Color.BLACK );
			g.drawString("Add a Triangle by doing mouse press, drag and release!", 40, 15);
                       
			
            /* for (Triangle.ColoredTriangle cr : coloredTriangles)
			{       
				g.setColor( cr.getForeground() );
				Triangle t = cr.getTriangle();
                                 //int x = t.startPoint.x;
				 //int y = t.endPoint.y;
                                
                            
                                //int mouseX_dragged = t.endPoint.x;
                                //int mouseY_dragged = t.endPoint.y;
				//System.out.println("i am in loop " + startPoint.x);
                              int[] xPoints = {startPoint.x , endPoint.x, endPoint.y}; //refelct x dragged and y
                              int[] yPoints = {endPoint.y, endPoint.y, endPoint.x}; //refelct x dragged and y
            
				g.drawPolygon(xPoints,yPoints,3);
                                //System.out.println("i am in loop ");
                                
				//g.drawPolygon(t.getX(), t.getY(), t.getWidth(), t.getHeight());
				//g.drawPolygon(t.startPoint, t.endPoint, 3);
                                
			} */

			g.setColor( foreground );

			//  Paint the Rectangle as the mouse is being dragged

			if (startPoint != null && endPoint != null)
			{
				//int x = Math.min(startPoint.x, endPoint.x);
				//int y = Math.min(startPoint.y, endPoint.y);
                                int x = startPoint.x; 
                                int y = startPoint.y;
				//int width = Math.abs(startPoint.x - endPoint.x);
                                int mouseX_dragged = endPoint.x;
                                int mouseY_dragged = endPoint.y;
				//int height = Math.abs(startPoint.y - endPoint.y);
				//g.drawRect(x, y, width, height);
                            int[] xPoints = {x , mouseX_dragged, mouseY_dragged}; //refelct x dragged and y
            int[] yPoints = {y, mouseY_dragged, mouseX_dragged}; //refelct x dragged and y
            //graphics2d.drawPolygon(xPoints, yPoints, 3);
                               
				g.drawPolygon(xPoints,yPoints,3);
                                
                                //System.out.println("i am iside mouse pint loop");
			}
		}

		public void addTriangle(ColoredTriangle triangle)
		{
			coloredTriangles.add( triangle );
			repaint();
		}

		public void clear()
		{
			coloredTriangles.clear();
			repaint();
		}

		class MyMouseListener extends MouseInputAdapter
		{
			private int xMin;
			private int xMax;
			private int yMin;
			private int yMax;

			public void mousePressed(MouseEvent e)
			{
				startPoint = e.getPoint();
				endPoint = startPoint;
				xMin = startPoint.x;
				xMax = startPoint.x;
				yMin = startPoint.y;
				yMax = startPoint.y;
			}

			public void mouseDragged(MouseEvent e)
			{
				//  Repaint only the area affected by the mouse dragging

				endPoint = e.getPoint();
                                int x = startPoint.x; 
                                int y = startPoint.y;
				//int width = Math.abs(startPoint.x - endPoint.x);
                                int mouseX_dragged = endPoint.x;
                                int mouseY_dragged = endPoint.y;
				//int height = Math.abs(startPoint.y - endPoint.y);
				//g.drawRect(x, y, width, height);
                            int[] xPoints = {x , mouseX_dragged, mouseY_dragged}; //refelct x dragged and y
                            int[] yPoints = {y, mouseY_dragged, mouseX_dragged}; //refelct x dragged and y
				
				//repaint(xPoints, yPoints);
                                //repaint(x,y,3 );
                                repaint();
                                
                                
                                
				
			}

			public void mouseReleased(MouseEvent e)
			{
				//  Custom code to save the drawing information to the List
                               
				//int x = Math.min(startPoint.x, endPoint.x);
				//int y = Math.min(startPoint.y, endPoint.y);
				//int width = Math.abs(startPoint.x - endPoint.x);
				//int height = Math.abs(startPoint.y - endPoint.y);
				//Rectangle r = new Rectangle(x, y, width, height);
                                Triangle t  = new Triangle();
                                
                                //System.out.println(t.endPoint);
				//if (t.startPoint != null || t.endPoint != null)
				//{
					//ColoredTriangle cr = new ColoredTriangle(e.getComponent().getForeground(), t);
					//addTriangle( cr );
                                       // System.out.println("i am iside mouse released");
				//}

				startPoint = null;
                            //triangles.add( new Polygon(xs, ys, 3); );
                             
                                
			}
		}

		class ColoredTriangle
		{
			private Color foreground;
			private Triangle triangle;

			public ColoredTriangle(Color foreground, Triangle triangle)
			{
				this.foreground = foreground;
				this.triangle = triangle;
			}

			public Color getForeground()
			{
				return foreground;
			}

			public void setForeground(Color foreground)
			{
				this.foreground = foreground;
			}

			public Triangle getTriangle()
			{
				return triangle;
			}
		}
	}
