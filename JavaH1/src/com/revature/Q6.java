package com.revature;

public class Q6 {
	
	public Q6(float num) {
		
		float result = num/2;
		String[] arr = String.valueOf(result).split("\\.");
		int val = Integer.parseInt(arr[1]);
		
		if(val == 0) {
			System.out.println("Q6: Number is even");
		} else {
			System.out.println("Q6: Number is odd");
		}
		
	}

}
