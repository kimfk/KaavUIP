package leapMotion;
/******************************************************************************\
* Copyright (C) 2012-2013 Leap Motion, Inc. All rights reserved.               *

* Leap Motion proprietary and confidential. Not for distribution.              *
* Use subject to the terms of the Leap Motion SDK Agreement available at       *
* https://developer.leapmotion.com/sdk_agreement, or another agreement         *
* between Leap Motion and you, your company or other organization.             *
\******************************************************************************/

import gestures.Gesturizer;
import gestures.SimpleVector;

import java.io.IOException;
import java.lang.Math;
import java.util.ArrayList;

import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;
/**
 * This detects movement in the leap motion, makes list of coordinates 
 * and send this to the gesturerecognition
 * @author Kim Feenstra Kuiper
 *
 */
public class LeapMotionDetectsMovement extends Listener {

    public ArrayList<SimpleVector> coordinateslist = new ArrayList<SimpleVector>();	//Will save all the usefull coordinates
    public Gesturizer gesturizer;	
    
    //Start the Leap Motion
	public LeapMotionDetectsMovement(Gesturizer gesturizer) {
		this.gesturizer = gesturizer;
	    
	    
	}

	//Initialize the controller (Leap Motion)
	public void onInit(Controller controller) {
        System.out.println("Initialized");
    }

	//Check if the Leap Motion is connected
    public void onConnect(Controller controller) {
        System.out.println("Connected");
    }

    //When disconnected
    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    //When excited
    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        // Get the most recent frame and report some basic information
        Frame frame = controller.frame();
        
        int amount_hands = frame.hands().count(); //count the amount of hands
        int simplevectorx, simplevectory;	//temporary int's to save simplevectors.
        Vector temporary;		//Temporaty vector which will be translated and replaced
        
        //If there is 1 hand
        if (amount_hands == 1){
        	int amount_fingers = frame.fingers().count();				//Count fingers
        	Vector position1 =  frame.fingers().get(0).tipPosition();	//position fingertip 1
        	Vector position2 =  frame.fingers().get(1).tipPosition();	//position fingertip 2 (if he's not there it will ne (0,0,0)
        	//If there fingers detected
        	if (amount_fingers > 0){
		    	//Amount fingers = 1, and you are in the -x area (to the screen), the positions will be saved to the coordinates
        		//list, When you bring this finger in the +x area (from the screen) the list wil be sended to gesture. 
		    	if(amount_fingers == 1){
		    		if(position1.getZ() < 0) {
		    		temporary = frame.interactionBox().normalizePoint(frame.fingers().get(0).tipPosition());
		    		coordinateslist.add(new SimpleVector(temporary.getX(), temporary.getY()));
		    		//System.out.println("- Z, add to the list : " + frame.fingers().get(0).tipPosition());
		    		//coordinateslist.add(frame.fingers().get(0).tipPosition());
		    	}
		    	else if((position1.getZ()>0) && !coordinateslist.isEmpty()){
		    	  //  System.out.println("Now i will send the list to andreas: " + coordinateslist.toString());
		    	    gesturizer.compare(coordinateslist);
		    		coordinateslist.clear();
		    	} 		
		    }
		    	
		    	//Amount fingers = 2, and you are (with both fingers) in the -x area (to the screen), the positions will 
        		//Be sended to .... to replace the picture
	    	else if(amount_fingers == 2 && position1.getZ() < 0 && position2.getZ() <0){
	    		coordinateslist.clear();
	    		//System.out.println("Drag item to new position: " +frame.fingers().get(0).tipPosition());
	    		temporary = frame.interactionBox().normalizePoint(frame.fingers().get(0).tipPosition());
	    		// send (new SimpleVector(temporary.getX(), temporary.getY()));) to andreas
	        	//send new coordinates to andreas 		
	    	}
        	
        	}	
        }
        
        //If two hands are shown, the figure will be glued to his spot, it is now set. 
        if (amount_hands ==2){
        	//System.out.println("The figure is now placed");
        	coordinateslist.clear();
        	//Say that this figure can be printed on the spot where he now is. 
        }
        	
        

  	}
         
}


