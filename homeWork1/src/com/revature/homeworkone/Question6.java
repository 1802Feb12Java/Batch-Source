package com.revature.homeworkone;

public class Question6 {

	public static boolean isEven(int i) {
		
		int x = i / 2;
		
		// the integer division will round down
		// doubling the rounded number gives you a number
		// that is one less than what you started with if odd
		// so if i - 2*x is 1 then it was an odd number
		
		if (i - 2*x > 0) {
			return false;
		} else {
			return true;
		}
				
				
				
	}
	
	
	
	
	
	
	
	
	
}
