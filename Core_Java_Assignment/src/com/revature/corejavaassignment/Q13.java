package com.revature.corejavaassignment;

public class Q13 {
/*	
 * Q13. Display the triangle on the console as follows using any type of loop.
 * 	Do NOT use a simple group of print statements to accomplish this.
 *     0    
 *     1 0    
 *     1 0 1    
 *     0 1 0 1
 */
	public static void answer() {
		System.out.println("Q13.\tDisplay the triangle on the console as follows using any type of loop.\r\n" + 
				"\tDo NOT use a simple group of print statements to accomplish this.\r\n" + 
				"\t\t0    \r\n" + 
				"\t\t1 0    \r\n" + 
				"\t\t1 0 1    \r\n" + 
				"\t\t0 1 0 1");
		buildTriangle(5);
	}
	/*
	 * This method prints a "triangle" using 1's and 0's adding to the left then to the right
	 * before switching numbers 
	 */
	public static void buildTriangle(int numLines) {
		if(numLines<1) {
			return;
		}
		String lineOut = "0\n", lastLine="0";
		boolean left=true, zero=false;
		for (int line =1; line<=numLines; line++) {
			if(left) {
				if(zero) {
					lastLine = "0 " + lastLine;
					lineOut += lastLine + "\n";
				}else {
					lastLine = "1 " + lastLine;
					lineOut += lastLine + "\n";
				}
				left=false;
			}else {
				if(zero) {
					lastLine =  lastLine + " 0";
					lineOut += lastLine + "\n";
					zero=false;
				}else {
					lastLine = lastLine + " 1" ;
					lineOut += lastLine + "\n";
					zero=true;
				}
				left=true;
			}
		}
		System.out.println("Triangle with " + numLines + ":");
		System.out.println(lineOut);
	}
}
