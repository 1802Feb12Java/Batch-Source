package com.revature.assignments;

public class AssignmentThirteen {

	// Display the triangle on the console as follows using any type of loop. Do NOT
	// use a simple group of print statements to accomplish this.
	// 0
	// 1 0
	// 1 0 1
	// 0 1 0 1

	public static void printIlluminati() {
		int loopCount = 1;
		int totalCount = 0;
		for (int i = 0; i < 4; i++) {
			int j = 0;
			while (j++ < loopCount)
				System.out.print(totalCount++ % 2 + " ");
			System.out.println();
			loopCount++;
		}
	}

}
