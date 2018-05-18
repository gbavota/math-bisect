package repository.test;

import static org.junit.Assert.*;

import org.junit.Test;

import repository.MathFunctions;

public class TestMathFunctions {

	@Test
	public void testFactorial() {
		assertEquals(362880, MathFunctions.factorial(9));
	}
	
	@Test
	public void testCircleArea() {
		assertEquals(78.5, MathFunctions.computeCircleArea(5.0), 0.5);
	}

}
