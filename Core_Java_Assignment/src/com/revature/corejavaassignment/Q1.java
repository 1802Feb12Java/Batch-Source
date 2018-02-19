package com.revature.corejavaassignment;

import java.util.Arrays;

public class Q1 {
/*
 * Q1. Perform a bubble sort on the following integer array:  1,0,5,6,3,2,3,7,9,8,4
 */

	public static void answer() {
		int[] arr= {1,0,5,6,3,2,3,7,9,8,4};
		System.out.println("Q1.\tPerform a bubble sort on the following integer array:  1,0,5,6,3,2,3,7,9,8,4\nSorting.....\n");
		
		arr=bubbleSort(arr);
		System.out.println("\nDone!! Array is sorted\n");
	}
	/*
	 * This method takes in an int array and sorts it in increasing order using bubble sort
	 * in i
	 */
	public static int[] bubbleSort(int[] array) {
		for (int index=0; index < array.length -1; index++ ) {
			for (int index2 =0; index2< array.length-index-1; index2++) {
				if(array[index2] > array[index2+1]) {
					int temp = array[index2];
					array[index2] = array[index2+1];
					array[index2+1]=temp;
					System.out.println(Arrays.toString(array));
				}
			}
		}
		return array;
	}
}
