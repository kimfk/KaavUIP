package leapMotion;
/******************************************************************************\
* Copyright (C) 2012-2013 Leap Motion, Inc. All rights reserved.               *
* Leap Motion proprietary and confidential. Not for distribution.              *
* Use subject to the terms of the Leap Motion SDK Agreement available at       *
* https://developer.leapmotion.com/sdk_agreement, or another agreement         *
* between Leap Motion and you, your company or other organization.             *
\******************************************************************************/
//THIS ONE WORKSSS!!!!
import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

class SampleListener extends Listener {

    public ArrayList<Vector> coordinateslist = new ArrayList<Vector>();
	public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();
        int amount_fingers = frame.fingers().count();
        int amount_hands = frame.hands().count();
        Vector position = frame.fingers().get(0).tipPosition();
    
        if (amount_fingers > 0){
        	if(position.getZ() < 0) {
        		coordinateslist.add(frame.interactionBox().normalizePoint(frame.fingers().get(0).tipPosition()));
        		System.out.println("- Z, add to the list : " + frame.fingers().get(0).tipPosition());
        		//coordinateslist.add(frame.fingers().get(0).tipPosition());
        	}
        	else if((position.getZ()>0) && !coordinateslist.isEmpty()){
        	        		
        		System.out.println("Now i will send the list to andreas: " + coordinateslist.toString());
        		coordinateslist = new ArrayList<Vector>();
        	
        	}
        		
       }
        	
   
  	}
         
}

class LeapMotionDetectsMovement {
    public static void main(String[] args) {
        // Create a sample listener and controller
        SampleListener listener = new SampleListener();
        Controller controller = new Controller();

        // Have the sample listener receive events from the controller
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
