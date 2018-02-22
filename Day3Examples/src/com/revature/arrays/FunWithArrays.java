package com.revature.arrays;

import java.util.Arrays;
import java.util.function.Consumer;

import com.revature.controlflow.LoopyLoop;

public class FunWithArrays {

	public static void main(String[] args) {

		int[] myArray = { 1, 2 };
		int[] myArray2 = new int[2];
		int[] myArray3 = new int[] { 1, 2 };

		String[][] myArray4 = { { "cow", "cow2" }, { "dog", "dog2" } };

		LoopyLoop.print.accept(Arrays.toString(myArray));

		LoopyLoop.print.accept(Arrays.toString(myArray2));
		LoopyLoop.print.accept(Arrays.toString(myArray3));

		LoopyLoop.print.accept(Arrays.toString(myArray4));
		LoopyLoop.print.accept(Arrays.deepToString(myArray4));

		Consumer<String[]> printA = (String[] s) -> {
			for (String x : s)
				System.out.println(x);
		};

		String[] myArray5 = { "1", "2" };
		printA.accept(myArray5);

		try {
			int x = 1 / 0;

		} catch (Exception e) {
			System.out.println("caught");
		}
	}

}
