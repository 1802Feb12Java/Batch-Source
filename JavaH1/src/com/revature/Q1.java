package com.revature;

import java.util.Arrays;

public class Q1 {
	
	public Q1(int...nums) {
		
		int len = nums.length;
		int temp;
		
		for(int i=0; i<len; i++) {
			for(int j=1; j<(len-i); j++) {
				if(nums[j-1] > nums[j]) {
					temp = nums[j-1];
					nums[j-1] = nums[j];
					nums[j] = temp;
				}
			}
		}
		
		// The Arrays class toString() method prints out the array
		System.out.println("Q1: " + Arrays.toString(nums));
	}
	
}
