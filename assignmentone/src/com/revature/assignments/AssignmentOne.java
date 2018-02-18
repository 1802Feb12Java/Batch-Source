package com.revature.assignments;

public class AssignmentOne {

	/**
	 * Sorts an integer list in ascending order
	 * 
	 * @param list
	 * @return
	 */
	public static int[] bubbleSort(int[] list) {
		for (int i = 1; i < list.length; i++)
			for (int j = i; j < list.length; j++)
				if (list[j - 1] > list[j])
					swap(list, j - 1, j);

		return list;
	}

	private static void swap(int[] a, int x, int y) {
		int temp = a[x];
		a[x] = a[y];
		a[y] = temp;

	}

}
