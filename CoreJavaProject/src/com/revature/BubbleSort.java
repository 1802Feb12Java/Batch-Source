package com.revature;

import java.util.Arrays;

public class BubbleSort {
	
	//a function to display bubblesort example
	public static void example() {
		int[] array =  {1,0,5,6,3,2,3,7,9,8,4};
		System.out.println(Arrays.toString(array));
		array = bubbleSort(array);
		
		System.out.println(Arrays.toString(array));
	}
	
	//this performs a bubblesort on any array
	public static int[] bubbleSort(int[] array) {
		int temp = 0;
		
		//For loops used to swap array elements when needed in order to sort them from
		//least to greatest
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j < array.length; j++) {
				if(array[i] < array[j]) {
					temp = array[i];
					array[i] = array[j];
					array[j] = temp;
				}
			}
		}
		
		return array;
	}

}
