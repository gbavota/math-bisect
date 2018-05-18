package repository;

public class MathFunctions {

	/*Computes the factorial of a number*/
	public static int factorial(int n){
		if(n<=1)
			return 1;
		else
			return (n-1)*factorial((n-1));
	}

	/*Computes the area of a circle given its radius*/
	public static double computeCircleArea(double radius){
		return Math.PI * radius * radius;
	}

	/*Computes the circumference of a circle given its radius*/
	public static double computeCircleCircumference(double radius){
		return Math.PI * 2 * radius;
	}

	public static int squared(int n){
		return Math.pow(n,2);
	}

}
