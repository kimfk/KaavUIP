package gestures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.swing.AbstractAction;

/**
 * Class to recognize gestures
 * @author Andreas Lindmark
 *
 */
public class Gesturizer {
	private int N;
	private int sizeK;
	private double[] K; // kernel function
	private double kernelSum;
	private Levenshtein levenshtein;
	private HashMap<Integer, ArrayList<ArrayList<Integer>>> sequenceMap;
	private ArrayList<Integer> usedIDs;

	/**
	 * Initialize a new Gesturizer.
	 * 
	 * @param N
	 *            length of kernel function from middle
	 */
	public Gesturizer(int N, int m) {
		levenshtein = new Levenshtein();
		sequenceMap = new HashMap<Integer, ArrayList<ArrayList<Integer>>>();
		usedIDs = new ArrayList<Integer>();

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
	 * Finite Impulse Response Low-Pass Filter
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

	public ArrayList<Integer> vectorize(ArrayList<SimpleVector> list) {
		ArrayList<Integer> outList = new ArrayList<Integer>();

		for (int i = 0; i < list.size() - 1; i++) {
			outList.add(SimpleVector.subtract(list.get(i + 1), list.get(i))
					.getDirection());
		}

		return streamlineRedundant(outList);
	}

	/**
	 * Teach the Gesturizer a new gesture and assign it an ID.
	 * 
	 * @param ID
	 * @param list
	 */
	public void teach(int ID, ArrayList<Integer> list) {
		if (!sequenceMap.containsKey(ID)) {
			ArrayList<ArrayList<Integer>> nl = new ArrayList<ArrayList<Integer>>();
			nl.add(list);
			sequenceMap.put(ID, nl);
			usedIDs.add(ID); // Remember that this ID ha been used
		} else {
			ArrayList<ArrayList<Integer>> sequences = sequenceMap.get(ID);
			sequences.add(list);
		}
	}

	public void compare(ArrayList<SimpleVector> sequence){
		ArrayList<SimpleVector> list = filter(sequence);
		ArrayList<Integer> directions = vectorize(list);
		directions = streamlineRedundant(directions);
		compareAndTrigger(directions);
	}
	
	public void compareAndTrigger(ArrayList<Integer> list) {
		int[] input = new int[list.size()];
		for (int n = 0; n < input.length; n++) {
			input[n] = list.get(n);
		}

		int bestID = -1;
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
				
				if (t < bestDistance){
					// We have found a shorter distance
					bestDistance = t;
					bestID = i;
				}
			}
		}
		
		System.out.println("Best ID: " + bestID + " Shortest Distance: " + bestDistance);
	}
}
