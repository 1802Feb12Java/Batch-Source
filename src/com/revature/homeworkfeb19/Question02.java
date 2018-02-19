package com.revature.homeworkfeb19;

//Q2. Write a program to display the first 25 Fibonacci numbers beginning at 0.

public class Question02 {

	private int numA = 0;
	private int numB = 0;
	
	public Question02()
	{
		numA = 0;
		numB = 0;
	}
	
	public Question02(int a)
	{
		numA = 0;
		numB = 0;
	}
	
	public void fib()
	{
		int temp = 0;
		for (int i=1; i<=25; i++)
		{
			System.out.print(numB + ", ");
			if (numB == 0)
			{	
				numB++;
			}
			else
			{
				temp = numB;
				numB = numB + numA;
				numA = temp;
			}	
			if(i%5 == 0)
			{
				System.out.println();
			}
			
			
			
		}
	}
	
}
