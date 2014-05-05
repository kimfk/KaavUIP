package gestures;

public class SimpleVector {
	private double x;
	private double y;
	
	public SimpleVector(double a, double b){
		x = a;
		y = b;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public Integer getDirection(){
		double angle = Math.atan2(y, x) + Math.PI;
		System.out.println("angle: " + angle*(360/(2*Math.PI)));
		int adj = (int)Math.floor(angle*(8/(2*Math.PI)));
		return adj-1;
	}
	
	/**
	 * Subtract b from a and return the resulting vector.
	 * @param a
	 * @param b
	 * @return
	 */
	static public SimpleVector subtract(SimpleVector a, SimpleVector b){
		return new SimpleVector(a.x-b.x, a.y-b.y);
	}
	
	public void print(){
		System.out.println(x + " " + y);
	}
}
