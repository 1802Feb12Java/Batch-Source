package com.revature.homeworkone;

public class Question13 {

	public static void answer() {
		//need a variable to increment continuously
		//so you can get the 01010101 pattern.
		int c = 0;
		System.out.println("\nQuestion #13:");
		//nested loops to build the pyramid structure
		for (int i = 0; i < 4; i++) {
			String str = "";
			for(int j = 0; j <= i; j++) {
				str +=  c%2;
				c++;
			}
			System.out.println(str);
		}
		
		
	}
	
	
}
