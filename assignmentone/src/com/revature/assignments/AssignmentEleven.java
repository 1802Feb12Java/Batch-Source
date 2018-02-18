package com.revature.assignments;

import com.revature.assignments.eleven.Floats;

public class AssignmentEleven {
	// Write a program that would access two float-variables from a class that
	// exists in another package. Note, you will need to create two packages to
	// demonstrate the solution.

	public static float getF1() {
		return Floats.getF1();
	}

	public static float getF2() {
		return Floats.getF2();
	}
}
