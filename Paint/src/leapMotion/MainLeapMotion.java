
package leapMotion;

import gestures.Drawpanel2;
import gestures.Gesturizer;

import java.io.IOException;

import javax.swing.SwingUtilities;

import com.leapmotion.leap.Controller;
/**
 * This class will start the leap motion, including the pannel and gesturizer
 * @author Kim Feenstra Kuiper
 *
 */
public class MainLeapMotion {

	public static void main(String[] args) {
		// Create a listener and controller
		gestures.Gesturizer gesturizer = new Gesturizer(400, 110);
		Drawpanel2 panel = new Drawpanel2();
		gesturizer.setPanel(panel);
		SwingUtilities.invokeLater(panel);
		LeapMotionDetectsMovement listener = new LeapMotionDetectsMovement(gesturizer);
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
