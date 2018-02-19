package com.revature;

import java.util.Arrays;

public class Q1 {
	
	public Q1(int[] nums) {
		
		// get length of array and set int temp variable
		int len = nums.length;
		int temp;
		
		// use for loop to loop through the length of the array
		for(int i=0; i<len; i++) {
			
			// use another for loop to compare adjacent numbers down the array
			// this loop will move the biggest number to the end of the array first
			// and then it will move the next biggest number to position before the previous number
			// until all numbers are in ascending order
			for(int j=1; j<(len-i); j++) {
				
				// use if statement to to determine if the number before is bigger than the number after
				// if it is, change the position of the numbers in the array using the temp variable
				if(nums[j-1] > nums[j]) {
					
					// set position variables for clarity purposes
					int pos1 = j-1;
					int pos2 = j;
					
					// store first number in temp variable
					temp = nums[pos1];
					
					// set first number = to second number
					nums[pos1] = nums[pos2];
					
					// change first number to temp variable
					nums[pos2] = temp;
				}
				
			}
		}
		
		// Print out the array
		System.out.println("Q1: " + Arrays.toString(nums));
	}
	
}
