package kaav.main;

import gestures.DataPackage;

/**
 * Action interface
 * 
 * @author Ganryu
 * 
 */
public interface GAction {
	/**
	 * Method specified below is called by the gesturizer upon recognition of
	 * the associated gesture
	 */
	public void act(DataPackage p);
}