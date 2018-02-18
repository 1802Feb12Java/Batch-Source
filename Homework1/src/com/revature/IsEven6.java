package com.revature;

public class IsEven6 {

	public static boolean even(int n) {
		
		if((n / 2) * 2 == n){	// an even number follows mathematical convention of reversing operations
	        
			System.out.println(n + " is Even number");
			return true;
	    }else{
	    
	    	System.out.println(n + " is Odd Number");
	    	return false;
	    }
	}
}
