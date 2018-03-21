package com.revature.wrapper;

import java.util.Arrays;

public class Wrappers {
	//primitives
	/*
	 * int -> Integer
	 * double -> Double
	 * long -> Long
	 * float -> Float
	 * char -> Character
	 * boolean -> Boolean
	 * short -> Short
	 * byte -> Byte
	 */
	
	//Method Overloading:
	//1. Exact Match
	//2. Conversion
	//3. Autoboxing
	//4. Varargs
	public static void main(String...args) {
		short s = 7;
		foo(new Integer(5), (short) 5, new Integer(5), 56l, 2l, 6l, 4l);
		foo(new Integer(5), (short) 5, new Integer(5));
	}
	public static void foo(short i)
	{
		System.out.println("short");
	}
	
	public static void foo(Integer i)
	{
		System.out.println("Integer");
	}
	public static void foo(int i)
	{
		System.out.println("int");
	}
	public static void foo(Short s)
	{
		System.out.println("Short");
	}
	public static void foo(short...s)
	{
		System.out.println("Variable Arguments (varargs)");
		System.out.println(Arrays.toString(s));
	}
	public static void foo(Integer t, Short s, int x, long...ls)
	{
		System.out.println(t+" "+s+" "+x+" "+Arrays.toString(ls));
	}
}
