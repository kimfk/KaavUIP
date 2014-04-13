package kaav.main;


/*public class Triangle {
=======
public class Triangle implements Drawable{
>>>>>>> 955721cda743c3097b6e958afa423a5e4da8e368

}*/


/*public class Triangle {
}*/

//package ;

//imported libraries
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
 
/**
 * This tutorial from  
 * http://java-buddy.blogspot.se/2014/01/detect-mousepressed-and-mousedragged.html
 */

//circle class 
public class Triangle extends JComponent {
     
    //varibales to save the intitial and end positions of mouse
    int mouseX, mouseY;
    int mouseX_dragged, mouseY_dragged;
    
    boolean mouseDragged; //to be sure its dragged or not
 
    //the main function which call the GUI
    public static void main(String[] args) {
        //SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        //});
        
    }
 
    //circle construction with mouse listener & Motion Listner 
    public Triangle() {
        addMouseListener(myMouseAdapter);
        addMouseMotionListener(myMouseAdapter);
    }
     
    MouseAdapter myMouseAdapter = new MouseAdapter(){
 
        @Override
        public void mousePressed(MouseEvent e) {
            mouseX = e.getX(); //get x start point
            mouseY = e.getY(); //get y start point
            mouseDragged = false; // set dragged to false to not paint at start
            //repaint();
        }
 
        @Override
        public void mouseDragged(MouseEvent e) {
            mouseX_dragged = e.getX(); //get end x point
            mouseY_dragged = e.getY(); //get end y point
            mouseDragged = true; //set dragged to true to paint at end
            repaint();
           
            
        }
 
    };
 
    @Override
    public void paint(Graphics g) {
         
        Graphics2D graphics2d = (Graphics2D)g;
        graphics2d.setColor(Color.GREEN);
        if(mouseDragged){
            int x, y;
            int width, hight;
             
            if(mouseX > mouseX_dragged){
                x = mouseX_dragged;
                width = mouseX - mouseX_dragged;
            }else{
                x = mouseX;
                width = mouseX_dragged - mouseX;
            }
             
            if(mouseY > mouseY_dragged){
                y = mouseY_dragged;
                hight = mouseY - mouseY_dragged;
            }else{
                y = mouseY;
                hight = mouseY_dragged - mouseY;
            }
             
            //graphics2d.drawRect(x, y, width, hight);
            
            int[] xPoints = {mouseX , mouseX_dragged, mouseY_dragged}; //refelct x dragged and y
            int[] yPoints = {mouseY, mouseY_dragged, mouseX_dragged}; //refelct x dragged and y
            graphics2d.drawPolygon(xPoints, yPoints, 3);
            
            
        }else{
            //graphics2d.fillOval(mouseX-5, mouseY-5, 10, 10);
        }
        //repaint();
    }
 
    private static void createAndShowGUI() {
        JFrame myFrame = new JFrame();
        myFrame.setTitle("Kaav");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setSize(new Dimension(400, 300));
        myFrame.setLayout(new BorderLayout());
        myFrame.add(new Triangle(), BorderLayout.CENTER);
        myFrame.setVisible(true);
    }
}
