package com.revature;

//Q12

public class PrintEven {
	private static int[] intArr = new int[100];
	
	//initializes the list with 1 to 100
	private static void init() {
		for(int i = 1; i <= 100;i++) {
			intArr[i-1] = i;
		}
	}
	
	//goes through the array to only print even numbers
	//using enhance for loop
	public static void print() {
		init();
		System.out.print("[");
		for(int n:intArr) {
			if(n % 2 == 0)
				System.out.print(n + " ");
		}
		System.out.println("]");
	}
}
