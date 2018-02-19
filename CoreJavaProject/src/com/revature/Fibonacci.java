package com.revature;

public class Fibonacci {

	
	//a function to display fibonacci example
	public static void example() {
		//local variables for function
		int previous = 0;
		int current = 1;
		int next = 0;
		
		//iterate 25 times
		for(int i = 0; i < 25; i++) {
			
			//algorithm for fibonacci sequence
			next = previous + current;
			//print the sequence
			System.out.println(previous);
			
			//get rid of previous by ovewriting it with the current
			//ex. if previous == 8, then it is now qual to 13
			previous = current;
			//overwrite the current with the next number in the sequence
			current = next;		
		}
		
	}

}
