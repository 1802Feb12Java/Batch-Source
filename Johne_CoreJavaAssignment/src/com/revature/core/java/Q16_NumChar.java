package com.revature.core.java;

/**
 * 
 * @author johne
 *	Q16. Write a program to display the number of characters 
 *	for a string input. The string should be entered as a command line argument using (String [ ] args).
 */
public class Q16_NumChar {

	public static void main(String[] args) {
		/*
		 * args[0] is the first command-line argument 
		 */
		String str = args[0];
		System.out.println(str.length());
	}

}
