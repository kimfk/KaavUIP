package kaav.main;

/**
 * This represents a matrix for geometry transformations. 
 * @author Ganryu
 */
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
	 * Creates a new Matrix3 with the values as such:
	 * {m11,m21,m31}
	 * {m12,m22,m32}
	 * {m13,m23,m33}
	 * @param m11
	 * @param m21
	 * @param m31
	 * @param m12
	 * @param m22
	 * @param m32
	 * @param m13
	 * @param m23
	 * @param m33
	 */
	public Matrix3(float m11, float m21, float m31, float m12, float m22, float m32, float m13, float m23, float m33) {
		h[0][0] = m11;
		h[1][0] = m21;
		h[2][0] = m31;
		h[0][1] = m12;
		h[1][1] = m22;
		h[2][1] = m32;
		h[0][2] = m13;
		h[1][2] = m23;
		h[2][2] = m33;
	}

	/**
	 * Multiply two matrices to produce a new one. Form of matrix is 
	 * {1,0,0}
	 * {0,1,0} 
	 * {0,0,1}
	 */
	static public Matrix3 multiply(Matrix3 mat1, Matrix3 mat2) {
		Matrix3 newMatrix = new Matrix3();
		float sum;

		for (int n = 0; n < 3; n++) {
			for (int m = 0; m < 3; m++) {
				sum = 0;

				for (int i = 0; i < 3; i++) {
					sum += mat1.h[m][i] * mat2.h[i][n];
				}
				newMatrix.h[m][n] = sum;
			}
		}

		return newMatrix;
	}
	
	/**
	 * Apply the matrix to a Vector3.
	 * @param mat1
	 * @param vec1
	 * @return
	 */
	static public Vector3 multiply(Matrix3 mat1, Vector3 vec1){
		float value[] = new float[3];
		
		for (int v = 0; v < 3; v++){
			
			for (int i = 0; i < 3; i++){
				value[v]+= mat1.h[i][v] * vec1.getVec()[i];
			}
		}
		
		return new Vector3(value[0], value[1], value[2]);
	}
	
	/**
	 * Print the matrix.
	 */
	public void print() {
		System.out.println(h[0][0] + " " + h[1][0] + " " + h[2][0]);
		System.out.println(h[0][1] + " " + h[1][1] + " " + h[2][1]);
		System.out.println(h[0][2] + " " + h[1][2] + " " + h[2][2]);
	}
}