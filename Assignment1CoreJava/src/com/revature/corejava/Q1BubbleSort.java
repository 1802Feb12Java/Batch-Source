package com.revature.corejava;

//Question 1: Perform Bubble Sort
public class Q1BubbleSort {

	public static int [] BubbleSort(int [] array) {
		boolean swapOccurred;   //loop control value
		int temp = 0;  			//temp variable for swapping
		
		//do-while loop used because loop needs to run at least one time to determine if the 
		//array is sorted or not
		do {
			swapOccurred = false;	//at the start of each loop iteration, a swap has not occurred
			for(int counter = 0; counter < array.length - 1; counter++) {
				//determine if the value in the lower index is smaller than the value in index + 1
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
