package com.revature.corejava;

/*Display the triangle on the console as follows using any type of loop.  Do NOT use a simple group of print statements to accomplish this.
0
1 0
1 0 1
0 1 0 1
*/

public class Q13Triangle {
	public static void printTriangle(int numOfLines) {
		String printLine = "";
		for (int index = 0; index < numOfLines; index++) {
			printLine = printLine + (index % 2 == 0 ? 0 : 1) + " ";
			System.out.println(printLine);
		}
	}
}
