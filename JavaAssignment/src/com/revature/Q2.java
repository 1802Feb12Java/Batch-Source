package com.revature;

public class Q2 {
	//2.Write a program to display the first 25 Fibonacci numbers beginning at 0.

	//function to calculate fib sequence
	public static int fibonacci(int fib)
	{
		
		if (fib == 0)
		{
			return 0;
		}
		else if (fib == 1)
		{
			return 1;
		}
		else {
			return fibonacci(fib-1) + fibonacci(fib-2);
	
		}
	}
	//function to display Fib sequence
	public static void displayFibonacci()
	{
		for (int i = 0; i < 25; i++)
		{
			System.out.print(fibonacci(i) + " ");
		}
	}
}
