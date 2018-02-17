package com.revature.homeworkone;

import java.util.Arrays;
import java.util.Date;

public class Question14 {

	
	public static void answer() {
		
		System.out.println("\nQuestion #14 :");
		
		for (int java = 0; java<4; java++) {
			
			switch(java) {
			
				case 1:
					System.out.println(".sqrt(25) = " + Math.sqrt(25));
					break;
				case 2:
					Date day = new Date();
					System.out.println(day);
					break;
				case 3:			
					String needSplit = "I am learning Core Java.";
					String[] split = needSplit.split(" ");
					System.out.println(Arrays.toString(split));
					break;
			}
			
		}
	
	}
	
	
	
	
}
