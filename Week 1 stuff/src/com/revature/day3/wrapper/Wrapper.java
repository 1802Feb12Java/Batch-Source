package com.revature.day3.wrapper;

import java.util.Arrays;

public class Wrapper {

	/*
	 * Overloading
	 * The first thing we look for is:
	 * 		Exact Match
	 * 		conversion -> Primitives/Objects cast themselves to other types
	 * 		Boxing -> Primitives/Ojbects will undergo Auto Boxing/unBoxing (primitive to object or vice versa)
	 * 		Varargs -> arguments wrapped into arrays
	 * 			Method tht will take a variable number of arguments
	 * 			Varargs must be the last argument
	 * 			Can be of any type
	 */
	
	public static void main(String[] args) {
		
		int a=4;
		System.out.println(addThis(a)); //works because conversion, casts the int to a double
	
		Double f=6.6;
		System.out.println(addThis(f));

		method(a, f.intValue(), (int) f.doubleValue(),  12); //f is Double (class) not the primitive.  have to use method to cast to int primitive or double primitive than cast to int primitive
		method("sdfasf", a, f.intValue(), (int) f.doubleValue(),  12);
		
		method("can pass in zero args to varargs method");
		
		
		Integer x = 5; //x and y are pointing to the same location in memory
		Integer y = 5; //this is short hand
		Integer z = new Integer(5);
		System.out.println(x == y);
		System.out.println(x.equals(y));
		
		System.out.println(x == z);
		System.out.println(x.equals(z));
	}
	
	//varargs
	public static void method(int...x) {
		System.out.println(Arrays.toString(x));
		
	}
	
	public static void method(String a, int...x) {
		System.out.println(a + " " + Arrays.toString(x));
		
	}
	
	
	public static double addThis(double d) {
		double answer = d+5;
		return answer;
	}
	
	
	
}
