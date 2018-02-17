package com.revature.homeworkone;

/*
 * Calculate N Factorial
 * 
 */

public class Question4 {

	public static int factorial(int i) {
		//the recursive bus has to stop somewheres
		if (i == 0) {
			return 1;
		} else {
			//starts the recursion
			i = i * factorial(i-1);
		}
		
		return i;
		
	}
	
}
