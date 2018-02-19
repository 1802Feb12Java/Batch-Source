package com.revature.core.java;

/**
 * @author Johne Vang
 * Q14. Write a program that demonstrates the switch case. 
 * Implement the following functionalities in the cases:java 
	Case 1: Find the square root of a number using the Math class method.
	Case 2: Display today’s date.
	Case 3: Split the following string and store it in a string array.
           	“I am learning Core Java”
 */
import java.util.*;

public class Q14_SwitchCase {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter a number between 1 and 3: ");
		int caseNum = input.nextInt();
		
		switch(caseNum) {
			case 1: 
				System.out.print("Enter a number to solve square root: ");
				double num = input.nextInt();
				double result = Math.sqrt(num);
				System.out.println("Sqaure root for " + num + " is " + result);
				break;
			case 2 : 
				Calendar currentTime = Calendar.getInstance();
				System.out.println("Today's Date " + currentTime.getTime());
				break;
			/*
			 * String[] split(String regex): It returns an array of strings after 
			 * splitting an input String based on the delimiting regular expression.
			 * Source: https://beginnersbook.com/2013/12/java-string-split-method-example/
			 */
			case 3 :
				String str = new String("I am learning Core Java");
				String[] strArray = str.split(" ");
				System.out.println(Arrays.toString(strArray));
				break;
			default :
				System.out.println("bummer");
			
		} //end of switch

	} //end of main

} //end of class
