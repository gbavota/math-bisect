package repository;

public class MathFunctions {

	public static int factorial(int n){
		if(n<=1)
			return 1;
		else
			return n*factorial((n-1));
	}
	
	
	public static double computeCircleArea(double radius){
		return Math.PI * radius * radius;
	}
	
	
	
}
