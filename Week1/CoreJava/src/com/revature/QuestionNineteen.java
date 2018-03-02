package com.revature;

import java.util.ArrayList;

public class QuestionNineteen {

	public void doQuestion19() {
		ArrayList<Integer> intArrayList = new ArrayList<Integer>();
		ArrayList<Integer> noPrimeList = new ArrayList<Integer>();
		int evenSum = 0;
		int oddSum = 0;

		// populates array list with integers from 1 to 10
		for (int i = 1; i <= 10; i++) {
			intArrayList.add(i);
		}

		for (int i : intArrayList) {
			boolean isDivisible = false;

			// adds even numbers
			if (i % 2 == 0) {
				evenSum = evenSum + i;
			} else {
				// adds odd numbers
				oddSum = oddSum + i;
			}

		}

		System.out.println("Even Sum:\t" + evenSum);
		System.out.println("Odd Sum:\t" + oddSum);

		// checks if not prime and adds to new array list
		for (int w : intArrayList) {
			boolean isDivisible = false;

			for (int g = 2; g < w; g++) {
				if (w % g == 0) {
					isDivisible = true;
				}
			}

			if (isDivisible) {
				noPrimeList.add(w);
			}

		}

		// replace original array list with new, not prime array list
		intArrayList = noPrimeList;
		System.out.println(intArrayList);

	}

}