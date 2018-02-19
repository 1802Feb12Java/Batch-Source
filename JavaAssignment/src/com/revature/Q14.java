package com.revature;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Q14 {
	/*
	 * Write a program that demonstrates the switch case. Implement the following functionalities
in the cases:java
Case 1: Find the square root of a number using the Math class method.
Case 2: Display today’s date.
Case 3: Split the following string and store it in a string array.
“I am learning Core Java”
	 */
	//compute square root
	public static void squareRoot(double num)
	{
		double result;
		result = Math.sqrt(num);
		System.out.println("The square root of " + num + " is " + result);
		 
	}
	//display date
	public static void displayDate()
	{
		 LocalDate localDate = LocalDate.now();// using current date
		 System.out.println("Today's date: " + localDate);
	}
	//split string using string API
	public static void splitString()
	{
		String str = "I am learning Core Java";
		String[] strArray = str.split(" ");
		for(int i = 0; i < strArray.length; i++)
		{
			System.out.println(strArray[i] );
		}
	}
	public static void switchCase(int input)
	{
		switch(input)
		{
		case 1 :
			Scanner in = new Scanner(System.in);
			System.out.println("Enter a number for square root: ");
			int num = in.nextInt();
			squareRoot(num);
			break;
		case 2:
			displayDate();
			break;
		case 3:
			splitString();
			break;
		default:
			break;
		}
			
	}
	public static void print()
	{
		boolean isTrue = true;
		int choice;
		Scanner input = new Scanner(System.in);
		while(isTrue)
		{
			System.out.println("Enter number/ 0 to exit" );
			System.out.println("1. Find the square root of a number using the Math class method.");
			System.out.println("2. Display today's date");
			System.out.println("3. Split string ");
			choice = input.nextInt();
			if(choice == 0)
			{
				isTrue = false;
			}
			else
				switchCase(choice);
		}
	}
}
