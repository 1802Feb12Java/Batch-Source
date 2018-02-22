package com.revature.exceptions;

public class DessertTime {

	public static void main(String[] args) {

		int[] arr = new int[3];

		try {
			arr[2] = 2;
			throw new TooMuchPieException("oops");
		} catch (TooMuchPieException e) {
			e.printStackTrace();
		} finally {
			System.out.println("Finally");
		}
		System.out.println("Done");

		Boolean test2;
	}

}
