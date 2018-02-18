package com.revature;

import java.util.Arrays;

public class BubbleSort {
	/*
	 * The purpose of this class is to create a bubble sort method.
	 */

	public static void main(String[] args) {
		int[] myArray = {1,0,5,6,3,2,3,7,9,8,4}; //initialize array
		System.out.println(Arrays.toString(myArray)); //print unsorted array
		myBubbleSort(myArray); //sort array
		System.out.println(Arrays.toString(myArray)); //print sorted array

	}

	public static void myBubbleSort(int[] array) {
		int s = array.length; 
		for (int i = 0; i < s-1; i++) { 
			//want to iterate over all of the positions in the array
			//don't need to check the last position as by default it should be the largest at the
			//end of the sort
			for (int j = i+1; j < s; j++) { 
				//want to check all of the values in positions greater
				//than the one which we currently consider the smallest 
				//value yet to be checked
				if(array[j] < array[i]) { 
					//checking to see if the jth position holds a smaller value
					//than the ith position
					int temp = array[i]; //using temp variable to avoid losing data in the swap
					array[i] = array[j]; //storing the smaller value in the ith position
					array[j] = temp; //storing the larger value in the jth position
					
				}//end if statement
				
			}//end inner for loop
			
		}//end outer for loop
		
	}//end myBubbleSort
	
}//end class
