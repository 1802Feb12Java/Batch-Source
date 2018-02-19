package com.revature.corejavaassignment;

public class Q16 {
/*Q16. Write a program to display the number of characters for a string input. 
 * The string should be entered as a command line argument using (String [ ] args).
 */
	public static void answer(String[] args) {
		System.out.println("Q16. Write a program to display the number of characters for a string input. \r\n" + 
				" * The string should be entered as a command line argument using (String [ ] args).\n");
		dispChars(args[0]);
	}
	/*
	 * This method displays the number of characters in a string that was retrieved as
	 * a command line argument.
	 */
	
	public static void dispChars(String string) {
		System.out.println("The number of characters in \"" + string + "\" is " + string.length());
	}
}
