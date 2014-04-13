package kaav.main;
/**
 * This class is for testing matrix code
 * @author Ganryu
 */

public class AndreasTest {
	public static void main(String[] args) {
		Matrix3 mat1 = new Matrix3();
		Matrix3 mat2 = new Matrix3();
		Matrix3 mat3 = new Matrix3(1,2,3,4,5,6,7,8,9);
				
		Matrix3 mat4 = Matrix3.multiplyFromLeft(mat3, mat1);
		mat4.print();
	}
}
