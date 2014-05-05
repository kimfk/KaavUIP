package gestures;

public class Levenshtein {
	/**
	 * Return the levenshtein distance between the integers in the arrays
	 * 
	 * @param a
	 * @param b
	 */
	public int getDistance(int[] a, int[] b) {
		int[][] array = new int[a.length][b.length];

		for (int i = 0; i < a.length; i++)
			array[i][0] = i;

		for (int i = 0; i < b.length; i++)
			array[0][i] = i;

		int x;
		int y;
		int z;
		int t;
		for (int j = 1; j < b.length; j++) {
			for (int i = 1; i < a.length; i++) {
				if (a[i] == b[j]) {
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

		for (int j = 0; j < b.length; j++) {
			for (int i = 0; i < a.length; i++)
				System.out.print(array[i][j] + " ");
			System.out.println();
		}

		return array[a.length - 1][b.length - 1];
	}
}