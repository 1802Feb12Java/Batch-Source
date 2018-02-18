package com.revature;

//Q6. Write a program to determine if an integer is even 
//	  without using the modulus operator (%)

public class IsEven {
	
	public static void isEven(int n) {
		//using ternary with a custom "mod" condition 
		String ans = ( n-((n/2)*2) < 1) ? "Even" : "Odd";
		System.out.println(ans);
	}
}
