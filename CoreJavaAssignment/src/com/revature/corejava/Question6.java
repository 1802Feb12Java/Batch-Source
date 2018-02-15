package com.revature.corejava;

public class Question6 {
	
	public static boolean isEven(int number) {
		
		return ((number/2)*2) == number; //integer division truncates, so check will be false if odd
	}

}
