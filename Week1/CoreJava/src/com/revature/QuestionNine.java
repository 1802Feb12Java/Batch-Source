package com.revature;

import java.util.ArrayList;

public class QuestionNine {

	// populates array list with numbers from 1 to 100
	public static ArrayList<Integer> fillArrayListWithNumbers() {
		ArrayList<Integer> integerArrayList = new ArrayList<Integer>();

		for (int i = 1; i <= 100; i++) {
			integerArrayList.add(i);
		}

		return integerArrayList;
	}

	public void printPrimes() {
		ArrayList<Integer> searchThisForPrimes = fillArrayListWithNumbers();

		for (Integer w : searchThisForPrimes) {
			boolean isDivisible = false;

			// checks if w is divisible by smaller numbers
			for (int g = 2; g < w.intValue(); g++) {
				if (w.intValue() % g == 0) {
					isDivisible = true;
				}
			}

			if (!isDivisible) {
				System.out.println(w);
			}

		}
	}

}