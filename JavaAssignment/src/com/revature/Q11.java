package com.revature;

import com.revature.Q11Class.Q11Class;
public class Q11 {

	// 11. Write a program that would access two float-variables from a class that exists in
	//another package. Note, you will need to create two packages to demonstrate the
	//solution.
	public static void add()
	{
		float sum;
		sum = Q11Class.getFirstNum() + Q11Class.getSecondNum();
		System.out.println("Sum of two floats number is " + sum);
	}
}
