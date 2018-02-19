package com.revature;

import java.util.Calendar;
import java.util.Date;

public class SwitchStatement {

	public static void example() {
		// TODO Auto-generated method stub
		switchExample(1);
		switchExample(2);
		switchExample(3);
	}
	
	//exapmple of how a switch statement works
	public static void switchExample(int choice) {
		
		switch(choice) {
		
		//get square root
		case 1:
			System.out.println("The root of 25 is: " + Math.sqrt(25));
			break;
			//print out current date
		case 2:
			//setting date to current date using Calendar
			//Date itself has mostly deprecated functions
			Date date = Calendar.getInstance().getTime();
			System.out.println(date);
			break;
			// split the string
		case 3:
			String myString = "I am learning Core Java";
			//limit argument of 0, which is what i want
			String[] stringArray = myString.split(" ");
			for(int i = 0; i < stringArray.length; i++) {
				System.out.println(stringArray[i]);
			}
			break;
		//if all else fails, inform the user
		default:
			System.out.println("INVALID CASE");
			break;
			
		}
		
	}

}
