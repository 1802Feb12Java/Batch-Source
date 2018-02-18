package com.revature;

public class Q16 {
	public static void run(String[] input) {
		int inputLength = 0;
		for(String s : input) {
			inputLength += s.length();	//go through the array, add up all the string lengths
		}
		if(inputLength == 0) {
			System.out.println("In eclipse, chose Run -> Run configurations -> this Driver class -> arguments -> (type in whatever you want) to do command line arguments. \n" + 
					"Your machine will show 0 until you add a command line argument this way.\n");
		}	//print out a message if there's no command line argument
		
		System.out.println("Number of characters in your command line argument: " + inputLength);
	}
}
