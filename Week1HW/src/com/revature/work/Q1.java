package com.revature.work;

import java.util.Arrays;

public class Q1 {
	
	// Sorting list by comparing adjacent to it
	public void bubbleSort(int myList[]) {
		// temp is placeholder
		int temp;
		// 2 for loops so that it can place min as temp and compare it to the complete list to find its position
		for (int x = 0; x < myList.length-1;x++) {
			for (int y = 0; y < myList.length-1;y++) {
				if (myList[y] > myList[y+1]) {
					temp = myList[y];
					myList[y]= myList[y+1];
					myList[y+1]=temp;
				}
			}
		}
	}
	
	void print(int myList[]) {
			System.out.println(Arrays.toString(myList));
		
	}
	
}

