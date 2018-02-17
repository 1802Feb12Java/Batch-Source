package com.revature.homeworkone;

public class FibNumGenerator {

	private static int fibNum = 0;
	
	public static int[] genFibNum() {
		//just hard coding array size, and first 2 numbers.
		int[] fibArray = new int[25];
		fibArray[0] = fibNum;
		fibArray[1] = 1;
		//Then just iterate through until the array is filled.
		for (int i = 2; i < fibArray.length; i++) {
			fibArray[i] = fibArray[i-2] + fibArray[i-1];
		}
			
		return fibArray;
	}
	
	
}
