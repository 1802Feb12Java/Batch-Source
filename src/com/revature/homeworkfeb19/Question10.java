package com.revature.homeworkfeb19;

//Q10. Find the minimum of two numbers using ternary operators.


public class Question10 {

	private int a;
	private int b;
	
	public Question10(int a, int b)
	{
		this.a = a;
		this.b = b;
	}
	
	public int minimum() {
		int min;
		min = a<b? this.a:this.b;
		
		return min;
	}
	
}
