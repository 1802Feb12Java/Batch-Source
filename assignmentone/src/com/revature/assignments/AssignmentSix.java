package com.revature.assignments;

public class AssignmentSix {

	// Write a program to determine if an integer is even without using the modulus
	// operator (%)
	public static boolean isEven(int i) {
		int remainder = i / 2;
		return remainder * 2 == i;
	}
}
