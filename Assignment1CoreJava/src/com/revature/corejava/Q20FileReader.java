package com.revature.corejava;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

/*Question 20: Create a notepad file called Data.txt and enter the following:
Mickey:Mouse:35:Arizona
Hulk:Hogan:50:Virginia
Roger:Rabbit:22:California
Wonder:Woman:18:Montana
 
Write a program that would read from the file and print it out to the screen in the following format:
 
Name: Mickey Mouse
Age: 35 years
State: Arizona State*/

public class Q20FileReader {
	public static void readToScreen() {
		String filename = "Data.txt";
		String firstName = "";
		String lastName = "";
		String age = "0";
		String state = "";
		File file = new File(filename);
		Scanner scanner = null;
		
		//open the file for the scanner
		try {
			scanner = new Scanner(file);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		//set the delimiter for the scanner
		scanner.useDelimiter(":");
		
		//loop through each item in the file, stopping at the delimiter ':'
		while(scanner.hasNext()) {
			firstName = scanner.next();
			lastName = scanner.next();
			age = scanner.next();
			state = scanner.nextLine();
			
			//print out the values formatted for each line
			System.out.println("Name: " + firstName + " " + lastName);
			System.out.println("Age: " + age);
			System.out.println("State" + state + " State");
			System.out.println();
		}
		//close the scanner connection to the file
		scanner.close();
	}
}	
