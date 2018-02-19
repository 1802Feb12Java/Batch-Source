package com.revature;

public class MathClass implements MathInterface //implementing the Math Interface allows us to call its default scope methods
{
	public MathClass(double a, double b)
	{
		System.out.println(MathInterface.addNum(a,b));
		System.out.println(MathInterface.subtractNum(a,b));
		System.out.println(MathInterface.multiplyNum(a,b));
		System.out.println(MathInterface.divideNum(a,b));
	}
	
}
