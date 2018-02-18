package com.revature;

//Q1. Perform a bubble sort on the following integer array:  1,0,5,6,3,2,3,7,9,8,4

public class BubbleSort {
	
	//helper method to switch two elements in the array
	public static void swap(int [] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	//implementation of bubble sort in java
	public static void bubbleSort(int [] arr) {
		//using .length to keep track of the size of the array
		int len = arr.length - 1;
		//print out the array before sorted 
		System.out.println("before: " + java.util.Arrays.toString(arr));
		
		//nested for loops to sort
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len; j++) {
				if(j < len && arr[j] > arr[j+1]) {
					//swap j and j+1
					swap(arr,j,j+1);
				}
			}
		}
		//print out array after sorted
		System.out.println("after: " + java.util.Arrays.toString(arr));
	}
}
