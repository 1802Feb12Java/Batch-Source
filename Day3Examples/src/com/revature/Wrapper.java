package com.revature;

import java.util.Arrays;

public class Wrapper {

	public static void main(String[] args) {
		int a = 4;
		Double f = 6.6;
		double g = 5.5;
		//conversion
		System.out.println(addThis(a));
		//boxing
		System.out.println(addThis(f));
		//Varargs
		method(a, f.intValue(), (int) g, 12);
		method("For the tower!", a, (int) g, 12);
		method("For the tower!");
		
		Integer j = 5;
		Integer z = 5;
		if(z==j) {
			System.out.println("did it");
		}else {
			System.out.println("nope");
		}
	}
	
	public static double addThis(double d) {
		double answer = d + 5;
		return answer;
	}
	//Varargs method
	public static void method(int...x) {
		System.out.println(Arrays.toString(x));
	}
	
	public static void method(String a, int...x) {
		System.out.println(a + Arrays.toString(x));
	}
	
}
