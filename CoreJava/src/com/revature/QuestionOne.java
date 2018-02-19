package com.revature;

public class QuestionOne {

	public int[] bubbleSort() {
		int[] sortThis = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };

		// keep iterating until gone through as many times as there are elements
		for (int i = 0; i < sortThis.length; i++) {
			// loop through the array and swap
			for (int j = 0; j < sortThis.length - i - 1; j++) {
				// swap if current value bigger than next
				if (sortThis[j] > sortThis[j + 1]) {
					int temp = sortThis[j];
					sortThis[j] = sortThis[j + 1];
					sortThis[j + 1] = temp;
				}
			}
		}

		return sortThis;
	}

}