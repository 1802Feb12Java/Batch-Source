package com.revature;

import java.util.Arrays;

public class Q2 {
	
	public Q2() {
	
		int[] arr = new int[25];
		arr[0] = 0;
		arr[1] = 1;
		
		for(int i=2; i<arr.length; i++) { 
			arr[i] = arr[i-1] + arr[i-2]; 
		} 

		System.out.println("Q2: " + Arrays.toString(arr));
	}

}
