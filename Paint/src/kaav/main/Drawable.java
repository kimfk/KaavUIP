package kaav.main;
/**
 * This interface must be implemented by all geometric objects to be stored in
 * the container and by all geometric objects to be drawn on screen.
 * 
 * The draw() method is called from the RenderingEngine on each object stored
 * but no specification for how the objects deal with position, scale and
 * rotation is given. That is entirely up to the object itself to handle.
 * 
 * @author Ganryu
 */
public interface Drawable {
	/**
	 * Draw the object on screen.
	 */
	void draw();
	
	/**
	 * Scale by some factor
	 * @param factor
	 */
	void scale(float factor);
	
	/**
	 * Rotate by radians
	 * @param radians
	 */
	void rotate(float radians);
	
	/**
	 * Translate by some coordinates x and y.
	 * @param x
	 * @param y
	 */
	void translate(float x, float y);	
}