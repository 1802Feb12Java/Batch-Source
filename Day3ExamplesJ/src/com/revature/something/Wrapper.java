package com.revature.something;

import java.util.Arrays;

public class Wrapper {
	
	public static void main(String[] args) {
		
		//conversion: int to double
		int a = 4;
		System.out.println(addThis(a));
		
		//boxing
		Double f = 6.6;
		System.out.println(addThis(f));
		
		//casting and varargs example
		method(a, f.intValue(), (int)f.doubleValue(), 12);
		method1("Roll Tide!!", a, f.intValue(), (int)f.doubleValue(), 12);
		method1("Roll Tide!!");
		
		method("Roll Tide!!", a, f.intValue(), (int)f.doubleValue(), 12);
		method(5, 6, 7, 8);
		
	}//end main
	
	
	
	public static double addThis(double d) {
		double answer = d+5;
		return answer;
	}
	
	//Varargs
	public static void method(int...x) {
		System.out.println(Arrays.toString(x));
	}
	
	public static void method(String a, int...x) {
		System.out.println(a + Arrays.toString(x));
	}
	
	public static void method1(String a, int...x) {
		System.out.println(a + Arrays.toString(x));
	}
	
	
	
	
	
	
}
