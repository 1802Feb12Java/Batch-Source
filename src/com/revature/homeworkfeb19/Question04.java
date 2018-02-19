package com.revature.homeworkfeb19;

//Q4. Write a program to compute N factorial.

public class Question04 {

	public Question04()
	{
		//no class variables necessary
	}

	public int factorial(int n)
	{
		if(n==0)
			return 1;
		else
		{
			n = n * this.factorial(n-1);
		}
		return n;
	}	
	
}
