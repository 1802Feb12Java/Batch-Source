package com.revature.answers;

import java.util.Arrays;

public class Question1 {

	public static void main(String[] args) {
	
		int arr[] = {1,0,5,6,3,2,3,7,9,8,4};
		System.out.println(Arrays.toString(arr));
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));
		
		
	}
	
	public static void bubbleSort(int arr[]) {
		int l = arr.length;						//used to know how long the array is
		for(int i = 0; i < l - 1; i++) {
			for(int j = 0; j < l - i -1; j++) {		//you need 2 for loops because you're checking through the list multiple times
				if(arr[j] > arr[j+1]) {				//the check to see if you need to switch the order
					int num = arr[j];				//creating a temp var to hold j's value
					arr[j] = arr[j+1];			
					arr[j+1] = num;
				}
			}
		}
	}
}
