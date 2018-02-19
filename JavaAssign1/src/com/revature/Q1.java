package com.revature;

import java.util.Arrays;

public class Q1 {  //uses bubblesort method to sort array

	public void bubbleSort(int[] a) {
		System.out.println("Unsorted Array: " + Arrays.toString(a));
		
		int num = a.length;
		for(int i = 0; i < num-1; i++) {
			for(int j = 0; j < num-i-1; j++) {
				if(a[j] > a[j+1]) {
					int temp = a[j];  //temp is created to swap a[j] and a[j+1]
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
		System.out.println("Sorted Array: " +Arrays.toString(a));
	}
	
	
}
