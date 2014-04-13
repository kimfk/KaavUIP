package kaav.main;

/**
 * This class is used to create new instances of Drawables.
 * @author Ganryu
 *
 */
public class GeometryFactory {
	/**
	 * Create a new Rectangle
	 * @return
	 */
	public Drawable makeRectangle(){
		Rectangle c = new Rectangle();
		return c;
	}
	
	public Drawable makeCircle(){
		Circle c = new Circle();
		return c;
	}
	
}
