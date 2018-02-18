package com.revature.assignments;

import java.util.ArrayList;
import java.util.Arrays;

public class AssignmentNine {
	// Create an ArrayList which stores numbers from 1 to 100 and prints out all
	// the prime numbers to the console

	private ArrayList<Integer> numberList;

	public AssignmentNine(int x) {
		numberList = new ArrayList<Integer>();
		fillList(x);
	}

	public void printPrimes() {
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for (Integer x : numberList)
			if (isPrime(x))
				intList.add(x);
		System.out.println(Arrays.toString(intList.toArray()));
	}

	private void fillList(int x) {
		for (int i = 1; i <= x; i++)
			numberList.add(i);
	}

	public static boolean isPrime(int x) {
		// 1 isn't prime
		if (x == 1)
			return false;
		for (int i = 2; i < x; i++)
			if (x % i == 0)
				return false;
		return true;
	}

	public ArrayList<Integer> getPrimeNumbers() {
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (Integer x : numberList)
			if (isPrime(x))
				primes.add(x);
		return primes;
	}
}
