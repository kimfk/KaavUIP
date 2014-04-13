package kaav.main;
/**
 * This class is for testing matrix multiplcations
 * @author Ganryu
 */

public class AndreasTest {
	public static void main(String[] args) {
		Matrix3 mat1 = new Matrix3();
		Matrix3 mat2 = new Matrix3();
		Matrix3 mat3 = Matrix3.multiplyFromLeft(mat1, mat2);
		mat3.print();
	}
}
