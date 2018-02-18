package com.revature;

public class Q13 implements Runnable {
	private final int ROWS;
	
	public Q13(int rowCount) {
		ROWS = rowCount;
	}
	
	@Override
	public void run() {
		System.out.println("Question 13: Printing Triangle");
		System.out.println("    Rows: " + ROWS);
		printTriangle();
	}
	
	public void printTriangle() {
		final String TAB = "    ";
		for(int i = 0; i < ROWS; ++i) {
			int startBit = ((i*(i+1))/2) & 1;
			
			System.out.print(TAB);
			for(int j = i+1, bit = startBit; j-->0; bit = ~bit & 1) {
				System.out.print(bit);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
}
