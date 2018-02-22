package com.revature;

import java.util.Arrays;

public class Q2 {
	
	public Q2(int num) {
	
		// set empty array the length of given parameter
		int[] arr = new int[num];
		
		// add the first two Fibonacci values
		arr[0] = 0;
		arr[1] = 1;
		
		// create for loop to populate the array
		for(int i=2; i<arr.length; i++) { 
			
			// add the previous two numbers together and store in the i^th position in the array
			arr[i] = arr[i-1] + arr[i-2]; 
			
		} 

		// Print out the array
		System.out.println("Q2: " + Arrays.toString(arr));
	}

}
