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
		
		//build each line by adding onto the existing line
		for (int index = 0; index < numOfLines; index++) {
			//begin the line with a 0 if it is an even line, a 1 if it is odd
			printLine = printLine + (index % 2 == 0 ? 0 : 1) + " ";
			//print the created line
			System.out.println(printLine);
		}
	}
}
