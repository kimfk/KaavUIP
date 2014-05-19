package gestures;

public class DataPackage {
	public double maximumX = Double.NEGATIVE_INFINITY; // rightmost point
	public double minimumX = Double.POSITIVE_INFINITY; // leftmost point
	public double maximumY = Double.NEGATIVE_INFINITY; // topmost point
	public double minimumY = Double.POSITIVE_INFINITY; // bottommost point
	
	public DataPackage(double a, double b, double c, double d){
		maximumX = a;
		minimumX = b;
		maximumY = c;
		minimumY = d;
	}
}
