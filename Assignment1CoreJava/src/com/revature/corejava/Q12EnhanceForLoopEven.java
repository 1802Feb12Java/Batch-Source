package com.revature.corejava;

/*Write a program to store numbers from 1 to 100 in an array. Print out all 
the even numbers from the array. Use the enhanced FOR loop for printing out the numbers.
*/

public class Q12EnhanceForLoopEven {
	public static void printEvens(int [] numbers) {
		for(int current : numbers) {
			if (current % 2 == 0) {
				System.out.println(current);
			}
		}
	}
}
