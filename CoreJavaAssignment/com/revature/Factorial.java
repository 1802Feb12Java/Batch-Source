package com.revature;

public class Factorial 
{
	
	int factorial(int n) //calculate a factorial using recursion
	{
	      if (n <= 1) return 1;
	      else return n * factorial(n - 1);
	}
	
}
