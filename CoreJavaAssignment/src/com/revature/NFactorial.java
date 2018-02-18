package com.revature;

//Q4. Write a program to compute N factorial.

public class NFactorial {
	
	//Recursion!! 
	//**bad for high value of N**
	public static int nFact(int n) {
		if(n == 0 || n == 1) {
			return 1;
		}
		else
			return n * nFact(n-1);
	}

}
