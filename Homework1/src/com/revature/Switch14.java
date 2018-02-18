package com.revature;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Switch14 {
	
	public static void switchState(int i) {
		
		switch(i){
		
		case 1:		// find the sqrt of user desired int
			
			System.out.println("What number do you want a sqrt of?");
			Scanner sc = new Scanner(System.in);
			
			int a = sc.nextInt();
			int sqrt = (int) Math.sqrt(a);	// sqrt
			
			System.out.println("The sqrt is "+sqrt);
			break;
			
		case 2: 	// prints todays date
			
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			System.out.println("The date is "+dateFormat.format(date));	// date
			break;
			
		case 3:		// splits sentence into string array, each string holding a word
			
			String string = "I am learning core Java";
			String[] splited = string.split(" ");		// split substrings at a white space
			int j = 0;
			
			while(j < splited.length) {		// ensures we do not go out of bounds
			
				System.out.print(splited[j]+"   ");
				j++;
			}
			
			break;
			
		default:
				
			return;
		}
	}
}
