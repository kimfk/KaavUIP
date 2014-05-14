package kaav.main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This tutorial from
 * http://stackoverflow.com/questions/20177596/freehand-drawing-java
* 
*/

public class FreeLine extends JPanel implements MouseListener, MouseMotionListener, Drawable {
    private int index = 0;
    private Point[] arr = new Point[100000];
    

    public FreeLine() {
        super();
        index = 0;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        //JFrame fr = new JFrame();
        //fr.add(this);
        //fr.setSize(500, 500);
        //setBackground(Color.green);
        //fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //fr.setVisible(true);

    }
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        for (int i = 0; i < index - 1; i++)
            g.drawLine(arr[i].x, arr[i].y, arr[i + 1].x, arr[i + 1].y);
    }

    public void mouseDragged(MouseEvent e) {
        arr[index] = new Point(e.getX(), e.getY());
        index++;
        //System.out.println(index);
        repaint();
    }
    public void mousePressed(MouseEvent e) {
        arr[index] = new Point(e.getX(), e.getY());
        index++;
        //System.out.println(index);
        repaint();
    }

    public void mouseExited(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {
        arr = new Point[100000];
        index = 0;}
    public void mouseMoved(MouseEvent e) {}

    public static void main(String[] args) {
        //FreeLine draw = new FreeLine("FreeLine");
        createAndShowGUI();
    }
    
    private static void createAndShowGUI() {
                JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Kaav");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
                frame.setLayout(new BorderLayout());
                frame.add(new FreeLine(), BorderLayout.CENTER);
		frame.setSize(400, 400);
		frame.setLocationRelativeTo( null );
		frame.setVisible(true);
    }
    
    /*@Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/

    @Override
    public void draw() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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