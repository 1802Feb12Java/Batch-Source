package com.revature.scanner;

import java.util.Scanner;

public class FunScan {

	public static Scanner sc = new Scanner(System.in);
	//System.in is a 'standard' input stream
	
	public static void printMe() {
		
		String contents;
		System.out.println("What would you like to print? ");
		//contents = sc.next(); //next() can be used to read in phone numbers
		//sc.close(); //will empty out whatever you may have been storing that next didnt read
		contents = sc.nextLine(); //as soon as you hit enter it closes what it takes
		System.out.println(contents);
		
		
	}//end printMe method
	
	
	public static void main(String[] args) {
		
		printMe();
		
		
	}//end main
	
	
	
}//end class
