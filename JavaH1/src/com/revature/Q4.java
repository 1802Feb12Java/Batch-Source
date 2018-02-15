package com.revature;

public class Q4 {
	
	public Q4(int i) {
		
		int result = i;
		while(i>1) {
			result = result*(i-1);
			i--;
		}
		
		System.out.println("Q4: " + result);
	}

}
