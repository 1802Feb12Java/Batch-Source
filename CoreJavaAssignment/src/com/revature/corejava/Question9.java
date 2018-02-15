package com.revature.corejava;

import java.util.ArrayList;
import java.util.Arrays;

public class Question9 {
	public static void printPrimes() {
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		ArrayList<Integer> primes = new ArrayList<Integer>();
		for (int i = 1; i <= 100; i++) {
			numbers.add(i);
		}
		System.out.println("Initial ArrayList created!\nInitial Array:");
		System.out.println(Arrays.toString(primes.toArray(numbers.toArray())));
		for (int number : numbers) {
			if(isPrime(number)) {
				primes.add(number);
			}
		}
		System.out.println("The primes are: ");
		System.out.println(Arrays.toString(primes.toArray()));
	}

	private static boolean isPrime(int number) {
		for (int i = 2; i < (int)Math.sqrt(number); i++) {
			if (number % i != 0) {
				return false;
			}
		}
		return true;
	}

}
