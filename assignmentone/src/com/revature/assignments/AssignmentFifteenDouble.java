package com.revature.assignments;

public class AssignmentFifteenDouble implements Math<Double> {
	/**
	 * Create a class that implements this interface and provides appropriate
	 * functionality to carry out the required operations.
	 */
	@Override
	public Double addition(Double n1, Double n2) {
		return n1 + n2;
	}

	@Override
	public Double subtraction(Double n1, Double n2) {
		return n1 - n2;
	}

	@Override
	public Double multiplication(Double n1, Double n2) {
		return n1 * n2;
	}

	@Override
	public Double division(Double n1, Double n2) {
		return n1 / n2;
	}

}
