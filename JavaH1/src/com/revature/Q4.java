package com.revature;

public class Q4 {
	
	public Q4(int i) {
		
		// set a int result variable to be the value of the factorial
		int result = i;
		
		// decrease i to the next multiplier of the factorial
		i--;
		
		// create a while loop to get each multiplier of the factorial
		while(i>1) {
			// multiply the result by itself and the next multiplier
			result = result*(i);
			
			// decrease the value of i to get the next multiplier
			i--;
		}
		
		// Print out the result of the factorial
		System.out.println("Q4: " + result);
	}

}
