package com.revature;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Q14 {
	public void run() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Hi. Please input 1, 2, or 3: ");
		int choice = scan.nextInt();		//get the user input for if they want to do 1, 2, or 3
		switch(choice) {
			case 1:
				System.out.print("Please input any number: ");
				double numToRoot = scan.nextDouble();		//get whatever number the user types in
				System.out.println("The square root is: "+Math.pow(numToRoot, 0.5));	//raise it to the 1/2 power to take square root
				break;
			case 2:
				Date currDate = new Date();			//month + 1 because it returns 0-11, date is day or month, year + 1900 b/c it starts at 1900
				System.out.println("Today's date is: " + (currDate.getMonth()+1)+"/"+currDate.getDate()+"/"+(currDate.getYear()+1900));
				break;
			case 3:
				String given = "I am learning Core Java";
				System.out.println("Original string: "+given);
				String[] splitGiven = given.split(" ");	//split it up and store it in an array whenever there's a space
				System.out.println("Original string in an array: " + Arrays.toString(splitGiven));	//print the array
				break;
		}
	}
}
