package com.revature.assignments;

public class AssignmentFifteenDriver {

	public final static Double n1 = 3.0;
	public final static Double n2 = 6.7;

	public static void main(String[] args) {
		/**
		 * Hard code two operands in a test class having a main method that calls the
		 * implementing class.
		 */
		AssignmentFifteenDouble myDouble = new AssignmentFifteenDouble();
		System.out.println("First Number: " + n1);
		System.out.println("Second Number: " + n2);
		System.out.println("\tAdding = " + myDouble.addition(n1, n2));
		System.out.println("\tSubrating = " + myDouble.subtraction(n1, n2));
		System.out.println("\tMultiplying = " + myDouble.multiplication(n1, n2));
		System.out.println("\tDividing = " + myDouble.division(n1, n2));

	}

}
