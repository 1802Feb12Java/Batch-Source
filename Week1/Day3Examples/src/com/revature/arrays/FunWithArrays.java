package com.revature.arrays;

import java.util.Arrays;

public class FunWithArrays {

	public static void main(String[] args) {
		int[] intArray = new int[7];
		int[] chaos = { 7, 6, 5, 8, 7, 255, 52, 2 };

		try {
			for (int i = 0; i < intArray.length + 9; i++) {
				intArray[i] = i * 2;
				System.out.println(intArray[i]);
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}

		for (int i : intArray) {
			System.out.println(i);
		}

		System.out.println(Arrays.toString(chaos));
		Arrays.sort(chaos);
		System.out.println(Arrays.toString(chaos));
	}
}