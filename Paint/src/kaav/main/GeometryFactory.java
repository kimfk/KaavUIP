package kaav.main;

public class GeometryFactory {
	public Drawable makeRectangle(){
		Rectangle c = new Rectangle();
		return c;
	}
	
	public Drawable makeCircle(){
		Circle c = new Circle();
		return c;
	}
	
}
