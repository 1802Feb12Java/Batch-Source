package com.revature;

public class Q4 {

	//4. Write a program to compute N factorial.
	
	//factorial function
	public static long factorial(long  x)
	{
		//if x = 1! or x = 0!, then return 1
		if (x == 1 || x == 0)
		{
			return 1;
		}
		//if x > 1, use recursive to return x!
		else
		{
			return x*factorial(x-1);
		}
	}

}
