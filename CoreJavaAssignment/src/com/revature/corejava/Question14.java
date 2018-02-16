package com.revature.corejava;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Question14 {
	
	public static void demonstrateSwitch() {
		System.out.println("Switch Demo.");
		System.out.println("Enter 1 to find a square root");
		System.out.println("Enter 2 to display the date");
		System.out.println("Enter 3 to display a message");
		
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		scanner.nextLine(); //consume the \n left in buffer
		
		switch (input) {
		case 1:
			System.out.println("Please enter a number to find the square root");
			input = scanner.nextInt();
			scanner.nextLine(); 
			System.out.println("The square root of " + input + " is: " +
					Math.sqrt(input));
			break;
		case 2:
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate localDate = LocalDate.now();
			System.out.println();
			System.out.println("Today's date is: " + 
					dateTimeFormatter.format(localDate));
			break;
		case 3:
			System.out.println("I am learning Core Java");
			 break;
		default:
			System.out.println("Did you enter a number between 1 and 3?");
			break;
		}
	}
}
