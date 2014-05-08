package gestures;

public class Levenshtein {
	/**
	 * Return the levenshtein distance between the integers in the arrays
	 * 
	 * @param arrayA
	 * @param arrayB
	 */
	public int getDistance(int[] arrayA, int[] arrayB) {
		int[][] array = new int[arrayA.length][arrayB.length];

		for (int i = 0; i < arrayA.length; i++)
			array[i][0] = i;

		for (int i = 0; i < arrayB.length; i++)
			array[0][i] = i;

		int x;
		int y;
		int z;
		int t;

		/**
		 * Build an array to represent the problem as a matrix rather than
		 * having to use recursion.
		 */
		for (int j = 1; j < arrayB.length; j++) {
			for (int i = 1; i < arrayA.length; i++) {
				if (arrayA[i] == arrayB[j]) {
					array[i][j] = array[i - 1][j - 1];
				} else {
					x = array[i - 1][j] + 1;
					y = array[i][j - 1] + 1;
					z = array[i - 1][j - 1] + 1; // ((a[i]==b[j]) ? 0 : 1)
					t = Math.min(x, y);
					t = Math.min(z, t);
					array[i][j] = t;
				}
			}
		}

		for (int j = 0; j < arrayB.length; j++) {
			for (int i = 0; i < arrayA.length; i++)
				System.out.print(array[i][j] + " ");
			System.out.println();
		}

		return array[arrayA.length - 1][arrayB.length - 1];
	}
}