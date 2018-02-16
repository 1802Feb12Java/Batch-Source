package com.revature;

public class QuestionTwo {

	public void fibonnaci(int iterations) {

		int[] fibonnaciSeries = new int[iterations];
		try {
			fibonnaciSeries[0] = 1;
			fibonnaciSeries[1] = 1;

			for (int i = 2; i < iterations; i++) {
				fibonnaciSeries[i] = fibonnaciSeries[i - 2] + fibonnaciSeries[i - 1];
			}
		} catch (Exception e) {

		}

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