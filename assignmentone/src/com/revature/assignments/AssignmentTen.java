package com.revature.assignments;

public class AssignmentTen {

	// Find the minimum of two numbers using ternary operators.
	public static Integer min(Integer n1, Integer n2) {
		int min = (n1.compareTo(n2) < 0) ? n1 : n2;
		return min;

	}

	// overload to account for other number types
	public static Double min(Double n1, Double n2) {
		Double min = (n1.compareTo(n2) < 0) ? n1 : n2;
		return min;

	}

	public static Long min(Long n1, Long n2) {
		Long min = (n1.compareTo(n2) < 0) ? n1 : n2;
		return min;

	}

	public static Float min(Float n1, Float n2) {
		Float min = (n1.compareTo(n2) < 0) ? n1 : n2;
		return min;

	}

}
