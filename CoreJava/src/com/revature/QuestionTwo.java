package com.revature;

public class QuestionTwo {

	public void fibonnaci(int iterations) {

		int[] fibonnaciSeries = new int[iterations];
		try {
			// 1st 2 numbers of fibonnaci series
			fibonnaciSeries[0] = 0;
			fibonnaciSeries[1] = 1;

			// adds previous 2 values to get current value
			for (int i = 2; i < iterations; i++) {
				fibonnaciSeries[i] = fibonnaciSeries[i - 2] + fibonnaciSeries[i - 1];
			}
		} catch (Exception e) {

		}

		// text formatting
		System.out.print("[");

		try {

			for (int i = 0; i < iterations - 1; i++) {
				System.out.print(fibonnaciSeries[i] + ", ");
			}

			System.out.print(fibonnaciSeries[iterations - 1]);
		} catch (Exception w) {

		}
		System.out.println("]");
	}
}