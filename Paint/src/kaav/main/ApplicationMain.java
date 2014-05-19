package kaav.main;

//import  paintApplication.*;

import gestures.Drawpanel2;
import gestures.Gesturizer;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import leapMotion.LeapMotionDetectsMovement;

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

		LeapMotionDetectsMovement listener = new LeapMotionDetectsMovement(
				gesturizer);
		Controller controller = new Controller();

		// listener receives events from the controller
		controller.addListener(listener);

		// Keep this process running until Enter is pressed
		System.out.println("Press Enter to quit...");
		try {
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Remove the sample listener when done
		controller.removeListener(listener);

	}
}
