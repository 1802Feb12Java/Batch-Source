package com.revature.controlflow;

import java.util.function.Consumer;

public class LoopyLoop {

	/**
	 * if
	 * 
	 * switch
	 * 
	 * loops
	 */

	public static void main(String[] args) {

		// if
		ifFun(conditional);

		ifBigger(1, 2);

		// STRING POOL TEST
		String x = "a";
		String y = "b";
		String y2 = "b";
		String z = x + y;
		StringBuilder s = new StringBuilder();
		s.append(x).append(y);
		print.accept((z == "ab"));
		print.accept((x == "a"));
		print.accept((y == y2));
		print.accept((y.toString() == y2));
		print.accept((s.toString() == x + y));
		print.accept((s.toString().equals(x + y)));

		int count = 0;
		while (count++ < 3)
			print.accept("asdf");

	}

	public static Consumer<Object> print = (Object o) -> System.out.println(o);
	private static boolean conditional;

	public static void ifFun(boolean condi) {
		if (condi) {
			print.accept("it was true");
		} else
			print.accept("it was false");
	}

	public static void ifBigger(int a, int b) {
		if (a > b)
			print.accept(a + " larger than " + b);
		else
			print.accept(a + " smaller than " + b);
	}

}
