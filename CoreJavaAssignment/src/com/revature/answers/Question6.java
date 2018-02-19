package com.revature.answers;

public class Question6 {

	public static void main(String[] args) {

		if(isEven(10)) {
			System.out.println("10 is even");
		} else {
			System.out.println("10 is odd");
		}
		if(isEven(11)) {
			System.out.println("11 is even");
		} else {
			System.out.println("11 is odd");
		}
		
	}

	public static boolean isEven(int num) {

		int k = num/2;								//stores the value of a rounded int
		if(k * 2 == num) {							//if k is even and you double it, it should be the same as num
			return true;
		} else {
			return false;
		}
		
	}
	
}
