package com.revature.homeworkone;

public class BubbleSort {

private static int[] arrayToSort = {1,0,5,6,3,2,3,7,9,8,4};
	
	public static int[] bubbleSort() {

		//nested for loops in order to run through the array.length times
		for (int i = 0; i < arrayToSort.length; i++) {
			for (int j = 0; j < arrayToSort.length - i - 1; j++) {
				
				if (arrayToSort[j] > arrayToSort[j+1]) {
					int swap = arrayToSort[j];
					arrayToSort[j] = arrayToSort[j + 1];
					arrayToSort[j+1] = swap;
				}	
			}
		}
		return arrayToSort;
	}
	
}
