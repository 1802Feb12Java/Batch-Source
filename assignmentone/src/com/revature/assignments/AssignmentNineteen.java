package com.revature.assignments;

import java.util.ArrayList;
import java.util.Arrays;

public class AssignmentNineteen {
	/**
	 * Create an ArrayList and insert integers 1 through 10. Display the ArrayList.
	 * Add all the even numbers up and display the result. Add all the odd numbers
	 * up and display the result. Remove the prime numbers from the ArrayList and
	 * print out the remaining ArrayList.
	 */
	private static ArrayList<Integer> list;

	// init array list
	public static void initList() {
		list = new ArrayList<Integer>();
		for (int i = 1; i <= 10; i++)
			list.add(i);
	}

	public static void printList() {
		System.out.println(Arrays.toString(list.toArray()));
	}

	public static void printOddAddition() {
		int total = 0;
		for (Integer x : list)
			if (x % 2 != 0)
				total += x;
		System.out.println("Odd total = " + total);
	}

	public static void removePrimes() {
		for (int i = 0; i < list.size(); i++)
			if (AssignmentNine.isPrime(list.get(i)))
				list.remove(i);
	}

}
