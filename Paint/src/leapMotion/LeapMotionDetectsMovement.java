package leapMotion;
/******************************************************************************\
* Copyright (C) 2012-2013 Leap Motion, Inc. All rights reserved.               *
* Leap Motion proprietary and confidential. Not for distribution.              *
* Use subject to the terms of the Leap Motion SDK Agreement available at       *
* https://developer.leapmotion.com/sdk_agreement, or another agreement         *
* between Leap Motion and you, your company or other organization.             *
\******************************************************************************/
//THIS ONE WORKSSS!!!!
import gestures.Gesturizer;
import gestures.SimpleVector;

import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;

class LeapMotionDetectsMovement extends Listener {

    public ArrayList<SimpleVector> coordinateslist = new ArrayList<SimpleVector>();
    public Gesturizer gesturizer;
    
	public LeapMotionDetectsMovement(Gesturizer gesturizer) {
		this.gesturizer = gesturizer;
	    gesturizer.configureDefaultSetup();
	    
	}

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
        Vector position =  frame.fingers().get(0).tipPosition();
        int simplevectorx, simplevectory;
        Vector temporary;
        
        if (amount_hands == 1){
        	if (amount_fingers > 0){
		    	//Amount fingers = 1 --> Free drawing
		    	if(amount_fingers == 1){
		    		if(position.getZ() < 0) {
		    		temporary = frame.interactionBox().normalizePoint(frame.fingers().get(0).tipPosition());
		    		coordinateslist.add(new SimpleVector(temporary.getX(), temporary.getY()));
		    		System.out.println("- Z, add to the list : " + frame.fingers().get(0).tipPosition());
		    		//coordinateslist.add(frame.fingers().get(0).tipPosition());
		    	}
		    	else if((position.getZ()>0) && !coordinateslist.isEmpty()){
		    	    System.out.println("Now i will send the list to andreas: " + coordinateslist.toString());
		    	    gesturizer.compare(coordinateslist);
		    		coordinateslist.clear();
		    	} 		
		    }
		    	
	    	//amount fingers = 2 --> replacing
	    	else if(amount_fingers == 2 && position.getZ() < 0){
	    		System.out.println("Drag item to new position: " +frame.fingers().get(0).tipPosition());
	        	//send new coordinates to andreas 
	        	//coordinateslist.clear();		
	    	}
        	
        	}	
        }
        
        if (amount_hands ==2){
        	System.out.println("The figure is now placed");
        	coordinateslist.clear();
        	//Say that this figure can be printed on the spot where he now is. 
        }
        	
        

  	}
         
}


