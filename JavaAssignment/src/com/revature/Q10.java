package com.revature;

public class Q10 {
	// 10. Find the minimum of two numbers using ternary operators.

	public static void minimum (int a, int b)
	{
		int minNum;
		minNum = (a < b) ? a : b; //ternary operators: if (a < b) is true, minNum = a; else minNum = b
		System.out.println("The minimum number of two numbers is "+ minNum);
	}
}
