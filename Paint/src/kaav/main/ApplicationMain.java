package kaav.main;

//import  paintApplication.*;

import gestures.Gesturizer;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;



public class ApplicationMain implements Runnable {
	static GraphicsDevice device = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new ApplicationMain());
	}

	@Override
	public void run() {
		Gesturizer gesturizer = new Gesturizer(400, 110);
		GeometryContainer container = new GeometryContainer();
		RenderingEngine renderer = new RenderingEngine(container);
		
		gesturizer.configureDefaultSetup(container);
		
		renderer.setUndecorated(true);
		renderer.setExtendedState(JFrame.MAXIMIZED_BOTH);
                device.setFullScreenWindow(renderer);
	}
}
