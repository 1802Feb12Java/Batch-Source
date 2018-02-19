package com.revature;

public class Q16 {
	/*
	 * Q16. Write a program to display the number of characters for a string input. The string
should be entered as a command line argument using (String [ ] args).
	 */
	public static void print(String[] args)
	{
		String str = args[0];
	    System.out.println("The number of characters is: " + str.length());
	}

}
