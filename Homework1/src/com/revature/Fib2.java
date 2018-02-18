package com.revature;

public class Fib2 {

	public static void Fibonacci(int[] arr) {
				 
		arr[0] = 0;	// base case
		arr[1] = 1;
		    
		for (int i = 2; i < arr.length; i++) {	// for all other cases
		    	
		    arr[i] = arr[i - 1] + arr[i - 2];	// add the last 2 values
		}
		
		for(int i = 0; i < arr.length; i++) {	// print the array
			
			System.out.print(arr[i]);
			System.out.print("  ");
		}
	}
}
