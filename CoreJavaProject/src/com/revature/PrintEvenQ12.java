package com.revature;

import java.util.ArrayList;

public class PrintEvenQ12 {

	//example on how to print out even numbers
	public static void example() {
		// TODO Auto-generated method stub
		
		ArrayList<Integer> integers = new ArrayList<>();
		
		//fill list with Integers
		for(int i = 0; i <= 100; i++) {
			integers.add(i);
		}
		
		//print out the even numbers in the array
		for(int i: integers) {
			if(i % 2 == 0) {
				System.out.println(i);
			}
		}
		
	}

}
