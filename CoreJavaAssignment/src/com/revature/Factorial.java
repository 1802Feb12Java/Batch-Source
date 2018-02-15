package com.revature;

public class Factorial {

	public static void main(String[] args) {
		
		nFactorial(1);
		nFactorial(5);
		

	}
	
	public static void nFactorial(int n) {
		if( n == 0) {
			System.out.println(0 + "! = " + 1);
		} else {
			int result = 1;
			for(int i = 1; i <= n; i++) {
				result = result * i;
			}
			System.out.println(n + "! = " + result);
		}
	}

}
