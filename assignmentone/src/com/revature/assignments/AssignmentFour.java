package com.revature.assignments;

public class AssignmentFour {

	public static int computeFactorial(int x) {
		if (x == 0)
			return 1;
		else
			return x * computeFactorial(x - 1);
	}
}
