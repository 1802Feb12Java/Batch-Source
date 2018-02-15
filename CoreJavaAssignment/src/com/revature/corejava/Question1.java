package com.revature.corejava;

public class Question1 {
	
	public static int[] bubbleSort(int[] inputArray) {
		Boolean wasSwapped; //used to control outer loop		
		do {
			wasSwapped = false;
			for (int i = 0; i < inputArray.length -1; i++) { //loop through entire array
				if (inputArray[i] > inputArray[i + 1]) {
					int temp = inputArray[i];
					inputArray[i] = inputArray[i + 1];
					inputArray[i +1] = temp;
					wasSwapped = true;
				}
			}
		}while (wasSwapped); //loop again until no swaps were made
		return inputArray;
	}

}
