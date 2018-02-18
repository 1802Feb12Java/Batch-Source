package com.revature.assignments;

/**
 * Write a program to store numbers from 1 to 100 in an array. Prints out all
 * the even numbers from the array. Uses the enhanced FOR loop for printing out
 * the numbers.
 */
public class AssignmentTwelve {

	private final int[] HUNDRED_NUMBERS = new int[100];

	public AssignmentTwelve() {
		initArray();
	}

	public void initArray() {
		for (int i = 1; i <= 100; i++)
			HUNDRED_NUMBERS[i - 1] = i;
	}

	public void printEvens() {
		for (int x : HUNDRED_NUMBERS)
			if (x % 2 == 0)
				System.out.print(x + ", ");
		System.out.println();
	}

}
