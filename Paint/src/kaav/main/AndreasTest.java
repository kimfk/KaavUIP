package kaav.main;

import gestures.Gesturizer;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AndreasTest implements Runnable {
	static GraphicsDevice device = GraphicsEnvironment
	        .getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new AndreasTest());
	}

	@Override
	public void run() {
		Gesturizer gesturizer = new Gesturizer(400, 110);
		GeometryContainer container = new GeometryContainer();
		RenderingEngine renderer = new RenderingEngine(container);
		
		renderer.setUndecorated(true);
		renderer.setExtendedState(JFrame.MAXIMIZED_BOTH);
        device.setFullScreenWindow(renderer);
	}
}
