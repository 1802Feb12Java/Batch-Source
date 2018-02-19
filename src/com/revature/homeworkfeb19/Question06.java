package com.revature.homeworkfeb19;

//Q6. Write a program to determine if an integer is even 
//without using the modulus operator (%)

public class Question06 {

	public Question06()
	{
	}
	
	public static boolean testEven(int n)
	{
		boolean even = false;
		int temp=n;
		n=n/2;
		n= 2 * n;
		if (n == temp)
			even=true;
		return even;
	}
}
