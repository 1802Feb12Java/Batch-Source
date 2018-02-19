package com.revature;

import java.util.Scanner;

public class Factorial {
	
	public static void example() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("What integer would you like to find the factorial of?: ");
		System.out.println(getFactorial(sc.nextLong()));
		
		//sc.close();
	}
	
	//recursive function to get n factorial
	public static Long getFactorial(Long n) {
		
		if(n == 0) {
			return 1L;
		}
		
		//will keep returning (in a recursive manner), until finally returning n factorial
		return n * getFactorial(n - 1);
	}
	
}
