package gestures;

public class LCounter {
	private int value;
	private int count;
	
	public LCounter(int val){
		value = val;
	}
	
	public void increment(){
		count++;
	}
	
	public int getCount(){
		return count;
	}
	
	public int getValue(){
		return value;
	}
}
