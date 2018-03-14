package com.revature;

import java.util.Scanner;

public class TestCasingClass {
	/*
	 * The purpose of this class is to test the Casing Class code.
	 */
	public static void main(String[] args) {
		
		runTestCasingClass();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/
		
	}//end main

	public static void runTestCasingClass() {
		Scanner read = new Scanner(System.in);
		System.out.print("Please enter a string you would like to use: ");
		String userStr = read.nextLine();
		CasingConcreteClass userStringObject = new CasingConcreteClass(userStr);
		Boolean run = true;
		while(run) {
			System.out.println("Please choose an option:");
			System.out.println("1. Check for uppercase characters.");
			System.out.println("2. Convert all lowercase characters to uppercase.");
			System.out.println("3. Convert your string to an integer and return the result plus 10.");
			System.out.print("Please indicate your selection: ");
			int selection = read.nextInt();
			
			switch(selection) {
				case 1:
					Boolean result = userStringObject.checkingForUpperCase();
					if(result) {
						System.out.println("There are uppercase letters in your string.");
					} else {
						System.out.println("There are no uppercase letters in your string.");
					}
					//run = false;
					break;
				case 2:
					String upperCaseString = userStringObject.makeUpperCase();
					System.out.println("The uppercase version of your string is: " + upperCaseString);
					//run = false;
					break;
				case 3:
					int stringAsInt = userStringObject.stringToIntPlus();
					System.out.println("The result of turning your string into an integer and adding 10 is: " + stringAsInt);
					//run = false;
					break;
				default:
					System.out.println("Your input was invalid.");
					
			}//end switch
			//the following code allows for the user to run the code again,
			//this way all cases can be tested.
			System.out.println("Would you like to make another selection? (yes or no)");
			String answer = read.next();
			if(answer.equals("no")) {
				run = false;
			}
		}//end while
		
	}//end runTestCasingClass method
	
	
}//end class
