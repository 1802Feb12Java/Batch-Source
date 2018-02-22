package com.revature;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Qu14 {
	
	public Qu14(int num1, int num2) {
		
		switch(num1) {
			case 1:
				double sr = Math.sqrt(num2);
				System.out.println("Q14: Square root of " + num2 + " is " + sr);
				break;
				
			case 2:
				DateFormat df = new SimpleDateFormat("MM/dd/yy");
				Date dateobj = new Date();
				System.out.println("Q14: Today's date is " + df.format(dateobj));
				break;
				
			default:
				String str = "I am learning Core Java";
				String[] strArray = str.split("");
				System.out.println("Q14: " + Arrays.toString(strArray));
				break;
		}
		
	}

}
