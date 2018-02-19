package com.revature;

public class Q13 {

	public void triangle() {
		int height = 4; //determines how many rows of "triangle"
		int counter = 0;
		for(int i = 1; i<= height; i++) {
			for(int j = 1; j<=i; j++) {
				System.out.print(counter%2 + " "); //switches between 0 and 1
				counter += 1;
			}
			System.out.print("\n");
		}
	}
}
