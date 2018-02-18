package com.revature;

public class Q12 {
	public static void run() {
		int[] arr = new int[100];
		for(int i=1; i<=100; i++) {
			arr[i-1] = i;	//fill up the array with 1-100
		}
		
		System.out.println("All evens from 1-100: ");
		for(int i : arr) {	//enhanced for to go through the array
			if(i%2 == 0) {	//only print the evens
				System.out.print(i + " ");
			}
		}
		System.out.println();	//formatting
	}
}
