package kaav.main;
public class Matrix3 {
	private float[][] h = new float[3][3];

	/**
	 * Create a new identity matrix
	 */
	public Matrix3() {
		h[0][0] = 1;
		h[1][1] = 1;
		h[2][2] = 1;
	}

	/**
	 * Multiply two matrices to produce a new one. Form of matrix is {1,0,0}
	 * {0,1,0} {0,0,1}
	 */
	static public Matrix3 multiplyFromLeft(Matrix3 mat1, Matrix3 mat2) {
		Matrix3 newMatrix = new Matrix3();
		float sum;

		for (int n = 0; n < 3; n++) {
			for (int m = 0; m < 3; m++) {
				sum = 0;

				for (int i = 0; i < 3; i++) {
					sum += mat1.h[m][0] * mat2.h[0][0];
				}
				newMatrix.h[m][n] = sum;
			}
		}

		return newMatrix;
	}

	/**
	 * 
	 */
	public void print() {
		System.out.println(h[0][0] + " " + h[1][0] + " " + h[2][0]);
		System.out.println(h[0][1] + " " + h[1][1] + " " + h[2][1]);
		System.out.println(h[0][2] + " " + h[1][2] + " " + h[2][2]);
	}
}