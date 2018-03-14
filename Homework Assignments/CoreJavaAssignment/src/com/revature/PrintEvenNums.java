package com.revature;

import java.util.Arrays;

public class PrintEvenNums {
	/*
	 * The purpose of this class is to identify and print the even
	 * numbers in an array.
	 */

	public static void main(String[] args) {
		
		printEvenNums();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/

	}//end main

	public static void printEven(int[] intArray) {
		//this method uses the isEven method in the EvenNumbers class
		for(int i : intArray) {
			if(EvenNumbers.isEven(i)) {
				System.out.print(i + " ");
			}//end if
		}//end enhanced for
	}//end printEven method
	
	public static void printEvenNums() {
		int[] numArray = new int[100];
		for(int i = 1; i < 101; i++) {
			numArray[i-1] = i;
		}//adds the value of i into the i-1 spot in the array.
		//for example, the value 1 will be inserted into the 0th 
		//spot in the array
		
		System.out.println("Original array: " + Arrays.toString(numArray));
		printEven(numArray);
	}//end printEvenNums method
		
}//end class
