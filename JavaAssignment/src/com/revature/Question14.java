/*  This is a class file for using a switch case
 * 
 * @author Dominic Nguyen
 */

package com.revature;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Question14 {

	public static Scanner scan = new Scanner(System.in);
	
	// constructor
	public Question14() {
		
	}
	
	/*  This method will determine a switch case from user input
	 * @param number - user input number for selecting a switch case
	 * @return - int value
	 */
	public int Switch(int number) {
		double input;
		String str = "I am learning Core Java";
		String[] splitArray = str.split(" ");
		//String[] array = new String[1];

		switch(number) {
		case 1:
			System.out.println("Enter a number to use a square root on:");
			input = scan.nextInt();
			System.out.println("The sqaure root of " + input + " is " + Math.sqrt(input));
			return 0;
		case 2:
			Date today = Calendar.getInstance().getTime();
			System.out.println("Today's date is " + today);
			return 0;
		case 3:
			for(int i = 0; i < splitArray.length; i++) {
				System.out.println(splitArray[i]);
			}
			return 0;
		default:
			return 0;
		}
	}
}
