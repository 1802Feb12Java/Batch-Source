package com.revature;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadingAFile {
	/*
	 * The purpose of this class is to demonstrate reading in from a text file
	 * and accessing the entries by recognizing the delimiter and format of 
	 * the information in the file.
	 */

	public static void main(String[] args) {
		
		readAndPrintFile();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/
	}//end of main

	public static void readAndPrintFile() {
		
		Scanner input = null;
		String fileName = "src\\com\\revature\\Data.txt";
		try{
			input = new Scanner(new File(fileName));
		}
		catch (FileNotFoundException e){
			System.out.println("Unable to open file " + fileName + ".");
		}
				
		while(input.hasNextLine()) { //while there is still a line to read
			String next = input.next(); //read in the line
			String[] tempArray = next.split(":"); //split the strings in the line by the delimiter :
			System.out.println("Name: " + tempArray[0] + " " + tempArray[1]
					+ "\nAge: " + Integer.valueOf(tempArray[2]) + "\nState: " + tempArray[3] +
					" State");
			//the information is not required to be stored, so here I print the 
			//information according to the desired formatting. The format follows
			//for all lines in the text file, so this can be printed using a loop.
			
		}//end while loop
		
		
	}//end of readAndPrintFile method
	
}//end of class
