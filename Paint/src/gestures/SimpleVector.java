package gestures;

/**
 * This is a two dimensional vector. Use these for finger coordinates and save
 * them in a list. Pass them into an instance of Gesturizer.
 * 
 * @author Ganryu
 * 
 */
public class SimpleVector {
	private double x;
	private double y;

	/**
	 * Constructor for vector
	 * @param a
	 * @param b
	 */
	public SimpleVector(double a, double b) {
		x = a;
		y = b;
	}

	/**
	 * Get x value
	 * @return
	 */
	public double getX() {
		return x;
	}

	/**
	 * Get y value
	 * @return
	 */
	public double getY() {
		return y;
	}

	/**
	 * Returns an int of the direction this vector represents.
	 * @return
	 */
	public Integer getDirection() {
		/*double angle = Math.atan2(y, x) + Math.PI;
		System.out.println("angle: " + angle * (360 / (2 * Math.PI)));
		int adj = (int) Math.floor(angle * (8 / (2 * Math.PI)));
		return adj - 1;*/
		
		double angle = Math.atan2(y, x);
		angle = Math.atan2(y, x);
		if (angle < 0)
			angle+=Math.PI*2;
		System.out.println("angle in degrees: " + angle * (360 / (2 * Math.PI)));
		System.out.println("angle in radians" + angle);
		int adj = (int) Math.floor(angle * (8 / (2 * Math.PI)));
		return adj;
	}

	/**
	 * Subtract b from a and return the resulting vector.
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	static public SimpleVector subtract(SimpleVector a, SimpleVector b) {
		return new SimpleVector(a.x - b.x, a.y - b.y);
	}

	/**
	 * Print vector contents
	 */
	public void print() {
		System.out.println(x + " " + y);
	}
	
	@Override
	public String toString(){
		return "(" + x + "," + y + ")";
		
	}
}
