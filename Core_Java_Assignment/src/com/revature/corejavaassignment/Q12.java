package com.revature.corejavaassignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q12 {
/*
 * Q12. Write a program to store numbers from 1 to 100 in an array. 
 * Print out all the even numbers from the array. Use the enhanced FOR loop for
 * printing out the numbers.
 */
	public static void answer() {
		System.out.println("\nQ12.\tWrite a program to store numbers from 1 to 100 in an array. \r\n" + 
				"\tPrint out all the even numbers from the array. Use the enhanced FOR loop for\r\n" + 
				"\tprinting out the numbers.\n");
		int[] arr= new int[100];
		for (int i=1; i<=100; i++) {
			arr[i-1]=i;
		}
		System.out.println("Array with 1 to 100 ints is: \n" + Arrays.toString(arr));
		isEven(arr);
	}
	/*
	 * This method takes in an integer array and prints elements which are even.
	 */
	public static void isEven(int[] arr) {
		List<Integer> evens= new ArrayList<Integer>();
		for(int i: arr) {
			if (i%2==0) {
				evens.add(i);
			}
		}
		System.out.println("\nArray with even ints is: \n" + evens.toString() + "\n");
	}
}
