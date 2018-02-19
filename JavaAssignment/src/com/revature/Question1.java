/* This is a class file for performing a bubble sort.
 * 
 * @author Dominic Nguyen
 * 
 */

package com.revature;

import java.util.Arrays;

public class Question1 {
	
	// constructor
	public Question1() {
		
	}
	
	/* This method sorts an array using bubble sort
	 * @param array - list of numbers
	 */
	public void BubbleSort(int[] array) {
		int i = 0;  // index of array
		int temp = 0;  // stores an array value
		int count = 0;  // stores the max number of times that sorting can occur
		 
		// keep sorting the list until all of the numbers are sorted
		while(count != array.length-1) {
			count = 0;  // reset count to 0
			
			// use bubble sort on the array
			for(i = 0; i < array.length-1; i++) {
				if(array[i] > array[i + 1]) {
					temp = array[i]; // store current index of array
					array[i] = array[i + 1]; // set current index of array to next index of array
					array[i + 1] = temp; // set next index of array to previous index of array
				}
				else {
					count++;  // if the compared elements don't need to be sorted, increment the count
				}
			}
		}
		
		// display answer to question 1
		System.out.println("Q1: Bubble sort: " + Arrays.toString(array));
	}
}
