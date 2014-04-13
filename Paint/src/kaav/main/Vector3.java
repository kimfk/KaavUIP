package kaav.main;

/**
 * This is a vector class for transformations.
 * @author Ganryu
 */
public class Vector3 {
	private float[] h = new float[3];
		
	public Vector3(float x, float y, float z){
		h[0] = x;
		h[1] = y;
		h[2] = z;
	}
	
	public float getX(){
		return h[0];
	}
	
	public float getY(){
		return h[1];
	}
	
	public float getZ(){
		return h[2];
	}	
	
	public float[] getVec(){
		return h;
	}
	
	public void print(){
		System.out.println(h[0] + " " + h[1] + " " + h[2]); 
	}
}
