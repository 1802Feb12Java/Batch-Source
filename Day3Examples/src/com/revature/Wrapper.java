package com.revature;

import java.util.Arrays;

public class Wrapper {

	/**
	 * Overloading: the first thing to look at: Exact Match,
	 * 
	 * Conversion -> Primitives/Objects cast themselves to other types.
	 * 
	 * Boxing -> Primitives/Objects will undergo Auto Boxing/UnBoxing
	 * 
	 * Varargs -> arguments wrappered into arrays
	 * 
	 */
	public static double addThis(double... d) {
		double total = 5;
		for (double e : d)
			total += e;
		return total;
	}

	public static int addThis(int... d) {
		int total = 5;
		for (int e : d)
			total += e;
		return total;
	}

	public static void printThis(String a, int... x) {
		System.out.println(a + " " + Arrays.toString(x));
	}

	public static void main(String[] args) {
		int a = 4;
		Double f = 5.4;

		for (String x : args)
			System.out.println(x);

		// Conversion
		System.out.println(addThis(a));

		// Boxing
		System.out.println(addThis(f));

		// Varargs
		System.out.println(addThis(f, a, 4));
		System.out.println(addThis(f.intValue(), (int) f.doubleValue(), (int) Math.floor(f)));
		printThis("asdf");

		System.out.println("\n\n\n");
		comparingPrimitivesAndWrappers();
		test();
	}

	public static void comparingPrimitivesAndWrappers() {
		int intPrimitive = 2;
		Integer intObject = 2;
		short shortPrimitive = 2;
		Long longObject = new Long(2);

		// unwraps the object to match the primitive to the object's value
		System.out.println("intPrimitive==intObject: " + (intPrimitive == intObject));
		// Compare memory locations of two objects
		System.out.println("intObject==new Integer(5): " + (intObject == new Integer(5)));
		// compare values of two objects
		System.out.println("intObject.equals(new Integer(5)): " + (intObject.equals(new Integer(5))));
		// comparing two primitives compares the value, regardless of order
		System.out.println("intPrimitive==shortPrimitive: " + (intPrimitive == shortPrimitive));
		System.out.println("shortPrimitive==intPrimitive: " + (shortPrimitive == intPrimitive));

		// cannot compare two different object types
		System.out.println("longObject.equals(intObject): " + (longObject.equals(intObject)));
		// retrieve the value from the wrapper.
		System.out.println(longObject.longValue() == intObject.intValue());
	}

	public static void test() {
		Integer x = 4;
		Integer y = 4;
		System.out.println("x==y? " + (x == y));
		x = new Integer(4);
		System.out.println("x==y? " + (x == y));
	}
}
