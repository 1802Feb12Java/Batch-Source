package com.revature;

public class QuestionTwelve {

	public void printEvens() {

		// fill array with numbers from 1 to 100
		int[] intArray = new int[100];

		for (int i = 1; i <= 100; i++) {
			intArray[i - 1] = i;
		}

		for (int j : intArray) {

			// print if even
			if (j % 2 == 0) {
				System.out.println(j);
			}

		}

	}

}