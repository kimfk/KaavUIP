package gestures;

import java.util.ArrayList;
import java.util.HashMap;

import actions.CreateCircle;
import actions.CreateSquare;
import kaav.main.GAction;

/**
 * Class to recognize gestures
 * 
 * @author Andreas Lindmark
 * 
 */
public class Gesturizer {
	private Drawpanel2 panel;
	private int N;
	private int sizeK;
	private double[] K; // kernel function
	private double kernelSum; // this is the sum of the kernel function
	private Levenshtein levenshtein;

	/*
	 * The following HashMap maps gesture IDs to lists of sequence where each
	 * sequence is a list of directions to represent a gesture. An ID can have
	 * multiple sequences associated with it in order that we can make sure it
	 * can recognize variations on gestures.
	 */
	private HashMap<Integer, ArrayList<ArrayList<Integer>>> sequenceMap;
	private ArrayList<Integer> usedIDs; // This is a list of all used IDs.
	/*
	 * This maps IDs to actions. Only one action per ID.
	 */
	private HashMap<Integer, GAction> actionMap;

	/**
	 * Initialize a new Gesturizer.
	 * 
	 * @param N
	 *            length of kernel function from middle
	 */
	public Gesturizer(int N, int m) {
		levenshtein = new Levenshtein();
		sequenceMap = new HashMap<Integer, ArrayList<ArrayList<Integer>>>();
		actionMap = new HashMap<Integer, GAction>();
		usedIDs = new ArrayList<Integer>();

		/**
		 * This code initializes the kernel function.
		 */
		this.N = N;
		sizeK = 1 + 2 * N;
		K = new double[sizeK]; // set size of kernel function
		double xt = m * (Math.PI / (double) N); // Build kernel function
		for (int i = 0; i < K.length; i++) {
			K[i] = (double) Math.sin((i - N) * xt) / (double) ((i - N) * xt);
		}
		K[N] = 1;

		for (int i = 0; i < K.length; i++) {
			kernelSum += K[i];
		}
	}

	/**
	 * Get a list with the contents of kernel function
	 * 
	 * @return
	 */
	public ArrayList<Double> getKernel() {
		ArrayList<Double> l = new ArrayList<Double>();
		for (int i = 0; i < K.length; i++)
			l.add(new Double(K[i]));
		return l;
	}

	/**
	 * Prints kernel function
	 */
	public void printK() {
		for (int i = 0; i < sizeK; i++) {
			System.out.println(K[i]);
		}
	}

	/**
	 * Finite Impulse Response Low-Pass Filter used to smooth the incoming
	 * coordinate vectors before we turn them into direction integers.
	 * 
	 * @param list
	 * @param N
	 * @return a list of smoothed coordinates
	 */
	public ArrayList<SimpleVector> filter(ArrayList<SimpleVector> list) {
		ArrayList<SimpleVector> nlist = new ArrayList<SimpleVector>();
		double nx;
		double ny;
		for (int i = 0; i < list.size(); i++) {
			nx = 0;
			ny = 0;

			for (int j = -N; j < N; j++) {
				nx += list.get(Math.min(Math.max(i + j, 0), list.size() - 1))
						.getX() * kernelFunction(j);
			}

			for (int j = -N; j < N; j++) {
				ny += list.get(Math.min(Math.max(i + j, 0), list.size() - 1))
						.getY() * kernelFunction(j);
			}

			nlist.add(new SimpleVector(nx / kernelSum, ny / kernelSum));
		}

		return nlist;
	}

	/**
	 * Helper for kernel function
	 * 
	 * @param i
	 * @return
	 */
	public double kernelFunction(int i) {
		return K[i + N];
	}

	/**
	 * Tries to reduce the length of any given list of integers by removing from
	 * it every integer that is identical to the integer before it.
	 * 
	 * @param list
	 *            of Integers
	 * @return a list of integers
	 */
	public ArrayList<Integer> streamlineRedundant(ArrayList<Integer> list) {
		ArrayList<Integer> newList = new ArrayList<Integer>();
		int current = -1;
		for (int i = 0; i < list.size(); i++) {
			if (current != list.get(i)) {
				current = list.get(i);
				newList.add(new Integer(current));
			}
		}
		return newList;
	}

	/**
	 * Like streamlineRedundant except it will remove any vector sequences from
	 * the main sequence unless that sequence is equal to or longer than some
	 * threshold.
	 * 
	 * @param list
	 * @param threshold
	 * @return
	 */
	public ArrayList<Integer> streamlineSafe(ArrayList<Integer> list,
			int threshold) {
		ArrayList<Integer> newList = new ArrayList<Integer>();
		int current = -1;
		int count = 0; // Threshold
		for (int i = 0; i < list.size(); i++) {
			if (current != list.get(i)) {
				if (count >= threshold && current != -1)
					newList.add(new Integer(current));
				current = list.get(i);
				count = 0;
			} else {
				count++;
			}
		}
		return newList;
	}

	/**
	 * Turns a vector array list into a list of integers representing
	 * directions.
	 * 
	 * @param list
	 * @return
	 */
	public ArrayList<Integer> vectorize(ArrayList<SimpleVector> list) {
		ArrayList<Integer> outList = new ArrayList<Integer>();
		
		for (int i = 0; i < list.size() - 1; i++) {
			outList.add(SimpleVector.subtract(list.get(i + 1), list.get(i))
					.getDirection());
		}

		return outList;
	}

	/**
	 * Associate an ID with a GAction such that the .act() method the GAction
	 * will be called if the Gesturizer matches the ID with a sequence just fed
	 * into the system.
	 * 
	 * @param ID
	 * @param action
	 */
	public void teachAction(int ID, GAction action) {
		actionMap.put(ID, action);
	}

	/**
	 * Teach the Gesturizer a new gesture and assign it an ID.
	 * 
	 * @param ID
	 * @param list
	 */
	public void teachSequence(int ID, ArrayList<Integer> list) {
		if (!sequenceMap.containsKey(ID)) {
			ArrayList<ArrayList<Integer>> nl = new ArrayList<ArrayList<Integer>>();
			nl.add(list);
			sequenceMap.put(ID, nl);
			// Remember that this ID has been used
			usedIDs.add(ID);
		} else {
			ArrayList<ArrayList<Integer>> sequences = sequenceMap.get(ID);
			sequences.add(list);
		}
	}

	public void compare(ArrayList<SimpleVector> sequence) {
		panel.setBaseList(sequence);
		
		// Filter the input sequence 
		ArrayList<SimpleVector> list = filter(sequence);
		panel.setResultList(list);
		
		ArrayList<SimpleVector> ol = new ArrayList<SimpleVector>();
		ol.add(new SimpleVector(-1,1));
		ol.add(new SimpleVector(1,0));
		ol.add(new SimpleVector(-1,0));
		ol.add(new SimpleVector(1,1));
		panel.setOutList(ol);
		
		double maximumX = Double.NEGATIVE_INFINITY; // rightmost point
		double minimumX = Double.POSITIVE_INFINITY; // leftmost point
		double maximumY = Double.NEGATIVE_INFINITY; // topmost point
		double minimumY = Double.POSITIVE_INFINITY ; // bottommost point
		
		/*
		 * Find the leftmost and rightmost extent of
		 * the filtered sequence.
		 */
		for (SimpleVector v : sequence){
			maximumX = Math.max(v.getX(), maximumX);
			minimumX = Math.min(v.getX(), minimumX);
			maximumY = Math.max(v.getY(), maximumX);
			minimumY = Math.min(v.getY(), minimumY);
		}
		
		// Vectorize the input list
		ArrayList<Integer> directions = vectorize(list);
		
		// Streamline the vector list
		directions = streamlineRedundant(directions);
		compareAndTrigger(directions);
	}

	public void compareAndTrigger(ArrayList<Integer> list) {
		
		int[] input = new int[list.size()];
		for (int n = 0; n < input.length; n++) {
			input[n] = list.get(n);
		}

		int bestID = -1; // Stores the best ID we found so far
		int bestDistance = Integer.MAX_VALUE;
		int t; // temporary int

		ArrayList<ArrayList<Integer>> sequences;
		// Look at each used ID
		for (Integer i : usedIDs) {
			sequences = sequenceMap.get(i);
			System.out.println("Checking ID: " + i);

			// Look at each list
			for (ArrayList<Integer> sequence : sequences) {
				int[] seq = new int[sequence.size()];

				for (int n = 0; n < sequence.size(); n++) {
					seq[n] = sequence.get(n);
				}
				t = levenshtein.getDistance(input, seq);

				if (t < bestDistance) {
					// We have found a shorter distance
					bestDistance = t;
					bestID = i;

					actionMap.get(bestID).act();
				}
			}
		}

		System.out.println("Best ID: " + bestID + " Shortest Distance: "
				+ bestDistance);
	}
	
	public void configureDefaultSetup(){
		ArrayList<Integer> square;
		ArrayList<Integer> circle;
		
		square = new ArrayList<Integer>();
		square.add(0);
		square.add(2);
		square.add(4);
		square.add(6);
		teachSequence(4, square);
		
		square = new ArrayList<Integer>();
		square.add(2);
		square.add(4);
		square.add(6);
		square.add(0);
		teachSequence(4, square);
		
		square = new ArrayList<Integer>();
		square.add(4);
		square.add(6);
		square.add(0);
		square.add(2);
		teachSequence(4, square);

		square = new ArrayList<Integer>();
		square.add(6);
		square.add(0);
		square.add(2);
		square.add(4);
		teachSequence(4, square);
		
		teachAction(4, new CreateSquare());
		System.out.println("Added square to ID #4");
		
		
		circle = new ArrayList<Integer>();
		square.add(0);
		square.add(1);
		square.add(2);
		square.add(3);
		square.add(4);
		square.add(5);
		square.add(6);
		square.add(7);
		teachSequence(5, square);
		
		teachAction(5, new CreateCircle());
		System.out.println("Added square to ID #5");
	}
	
	/**
	 * This is for testing only.
	 */
	public void setPanel(Drawpanel2 panel){
		this.panel = panel;
	}
}
