package com.revature;

public class EvenNumbers {

	public static void main(String[] args) {
		
		int evenNum = 4;
		int oddNum = 7;
		
		System.out.println("evenNum is even: " + isEven(evenNum));
		System.out.println("oddNum is even: " + isEven(oddNum));

	}
	
	
	public static Boolean isEven(int n) {
		int newInt =  n / 2;
		newInt = newInt * 2;
		
		if(newInt == n) {
			return true;
		} else {
			return false;
		}
		
	}

}
