package com.revature;

import java.util.Arrays;

public class Wrapper {
	public static int[] y = {4, 5, 6};
	public static String w = "GO DAWGS!";

	public static double addThis(double d) {
		return d + 5;
	}

	//VarArgs: variable number of arguments
	//VarArgs must be last parameter
	//can have 0 to many
	public static void method (int...x) {
		System.out.println(Arrays.toString(x));
	}

	public static void method (String s, int...x) {
		System.out.println(s + " " + Arrays.toString(x));
	}

	public static void main(String[] args) {
		int a = 4;
		Double f = 6.6;

		System.out.println(addThis(a));
		System.out.println(addThis(f));

		method(w, y);
	}

}