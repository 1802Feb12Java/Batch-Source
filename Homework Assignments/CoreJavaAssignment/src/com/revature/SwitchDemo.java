package com.revature;

import java.util.Arrays;
import java.util.Scanner;

public class SwitchDemo {
	/*
	 * The purpose of this class is to demonstrate using a switch
	 * statement with appropriate cases.
	 */
	public static void main(String[] args) {
		switchDemo();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/
	}//end main
	
	public static void switchDemo() {
		Scanner read = new Scanner(System.in);
		Boolean run = true;
		while(run) {
			System.out.println("Select an option from the following list:");
			System.out.println("1. Find the square root of a number.");
			System.out.println("2. Display today's date.");
			System.out.println("3. Split 'I am learning Core Java' into a string array.");
			System.out.println("Enter the number of your selection: ");
			int selection = read.nextInt();
			read.nextLine();
			
			switch(selection) {
			case 1: 
				System.out.println("Which number would you like to find the square root of? ");
				double userNum = read.nextDouble();
				System.out.println("The square root of " + userNum + " is " +
						Math.sqrt(userNum));
				//run = false;
				break;
			case 2:
				System.out.println("Today's date is: " + java.time.LocalDate.now() );
				//run = false;
				break;
			case 3: 
				String isString = "I am learning Core Java";
				System.out.println("The string to be split and stored into a string array is: " + isString);
				String[] strArray = isString.split(" ");
				System.out.println("The resulting string array is: " + Arrays.toString(strArray));
				//run = false;
				break;
			default:
				System.out.println("Your selection was invalid.");
				System.out.println("Please enter either 1, 2, or 3.");
				
			}//end switch
			//the following code allows the user to rerun through the options again
			//this way all cases can be tested
			System.out.println("Would you like to make another selection? (yes or no)");
			String answer = read.next();
			if(answer.equals("no")) {
				run = false;
			}
		}//end while
		
	}//end switchDemo method
	

}//end class
