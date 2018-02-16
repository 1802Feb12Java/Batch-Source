package com.revature.corejava;

import java.util.ArrayList;
import java.util.Arrays;

public class Question12 {
	public static void printEvenNumbers() {
		int[] numbers = new int[100];
		ArrayList<Integer> evens = new ArrayList<>();
		for (int i = 1; i <= 100; i++) {
			numbers[i-1] = i;
		}
		System.out.println("Initial Array Created\nPrinting Even Numbers:");
		for (int number : numbers) {
			if (number % 2 == 0) {
				evens.add(number);
			}
		}
		System.out.println(Arrays.toString(evens.toArray()));
	}
}
