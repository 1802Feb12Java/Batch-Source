package com.revature.something;

import java.util.Arrays;

//Overloading
//The 1st thing we look for it:
//	Exact Match
//	conversion -> primitatives/objs themselves to other type
//	boxing -> primitatives/objs will undergo auto box/unbox
//	varargs -> args wrapped into arrays


public class Wrapper {
	
	public static void main(String[] args) {
		int i1 = 2;
		Double d1=6.26;
		System.out.println(addThis(i1));
		System.out.println(addThis(d1));
		method(1,4,i1,d1.intValue());
		method("Hello World",1, (int)d1.doubleValue());
	}
	
	public static double addThis(double d) {
		double answer = d+5;
		return answer;
	}
	
	//varargs
	public static void method(int ... x) 
	{
		System.out.println(Arrays.toString(x));
	}
	
	public static void method(String a,int ... x) 
	{
		System.out.println(a + " " + Arrays.toString(x));
	}
}

