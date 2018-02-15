package com.revature.arrays;

import java.util.Arrays;

public class FunWithArrays {

	public static void main(String[] args) {
		
		int[] myArray = new int[7];
		int[] chaos = {7, 6, 5, 8, 7, 255, 52, 2};
		
		System.out.println(myArray.toString());
		System.out.println(myArray[1]);
		
		for (int i = 0; i < myArray.length; i++) {
			myArray[i]=i*5;
			System.out.println("The " + i + " element is " + myArray[i]);
		}
		
		for (int i:myArray) {
			System.out.println(i);
		}
		
		System.out.println(Arrays.toString(myArray));
		
		System.out.println(Arrays.toString(chaos));
		Arrays.sort(chaos);
		System.out.println(Arrays.toString(chaos));
		
		
	}//end main

	
	
}//end class
