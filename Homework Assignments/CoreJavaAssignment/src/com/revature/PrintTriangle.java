package com.revature;

public class PrintTriangle {
	/*
	 * The purpose of this class is to print a triangle of values
	 * without simply using print statements. There is a pattern in
	 * the values that are to be printed (0 1 0 1 0...etc) as well as
	 * a pattern in how many values are printed per line (1, 2, 3, 4)
	 * so I utilized loops to achieve the desired output.
	 */

	public static void main(String[] args) {
		printTriangle();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/

	}//end main
	
	public static void printTriangle() {
		int counter = 1;
		int j=1;// going to switch between 1 and 2, so that the printed out number switches between 0 (when j = 1) and 1 (when j = 2)
		int numToPrint;
		while (counter < 5) {
			for(int i = 1; i < counter+1; i++) {
				
				if(EvenNumbers.isEven(j)) {
					numToPrint = 1;
					j=1;
				} else {
					numToPrint = 0;
					j=2;
				}//end if-else
				
				System.out.print(numToPrint + " ");
				
			}//end for
			System.out.println();
			counter++;
			
			
		}//end while
	}//end printTriangle method

}//end class
