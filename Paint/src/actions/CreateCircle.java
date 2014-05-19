package actions;

import kaav.gestures.DataPackage;
import kaav.main.GAction;

public class CreateCircle implements GAction{
	@Override
	/**
	 * This method is called by the gesturizer when the appropriate gesture has
	 * been input into the system.
	 */
	@Override
	public void act(DataPackage data) {
		float x = (float)(data.maximumX+data.minimumX) / 2.0f;
		float y = (float)(data.maximumY+data.minimumY) / 2.0f;
		float a = 100;
		
		cont.createCircle(x, y, a);
	}

	@Override
	public void act(gestures.DataPackage p) {
		// TODO Auto-generated method stub
		
	}
}
