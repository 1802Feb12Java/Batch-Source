package com.revature.arrays;

import java.util.Arrays;

public class FunWithArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//int array w/ 7 "slots"
		int[] myArray = new int[7];
		//int array w/ hardcoded values
		int[] chaos = {2,5,12,15,24,1,23,500,14};
		
		for(int i = 0; i < myArray.length; i++) {
			myArray[i] = i*5;
			//System.out.println(i + " element is " + myArray[i]);
		}
		//for each loop
		for(int i: myArray) {
			System.out.println(i);
		}
		Arrays.sort(chaos);
		System.out.println(Arrays.toString(myArray));
		System.out.println(Arrays.toString(chaos));
	}

}
