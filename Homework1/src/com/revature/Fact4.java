package com.revature;

public class Fact4 {

	public static int fact(int n) {
		
		if(n == 0 || n == 1){	// base case
			
			return 1;
		}else
		
			return n*fact(n-1);	// otherwise append in a multiplication statement recursively
	}
}
