package com.revature.corejava;

//Q1. Perform a bubble sort on the following integer array:  1,0,5,6,3,2,3,7,9,8,4
public class Q1BubbleSort {

	public static int [] bubbleSort(int [] array) {
		boolean swapOccurred;   //loop control value
		int temp = 0;  			//temp variable for swapping
		
		//do-while loop used because loop needs to run at least one time to determine if the 
		//array is sorted or not
		do {
			swapOccurred = false;	//at the start of each loop iteration, a swap has not occurred
			for(int counter = 0; counter < array.length - 1; counter++) {
				//determine if the value in the lower index is smaller than the value in index + 1
				//if the values are equal, or the index + 1 is larger, there is no swap
				if(array[counter] > array[counter + 1]) {
					//copy the value in the higher index to the temporary variable
					temp = array[counter + 1];
					//copy the value in the lower index to the higher index
					array[counter + 1] = array[counter];
					//copy the value in the temporary variable into the lower index
					array[counter] = temp;
					//set the swap flag to indicate a swap has occurred
					swapOccurred = true;
				}
			}
		}while(swapOccurred);
		
		return array;
	}
}
