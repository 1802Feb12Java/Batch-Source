package com.revature.assignments;

public class AssignmentSixteen {
	/**
	 * Write a program to display the number of characters for a string input. The
	 * string should be entered as a command line argument using (String [ ] args).
	 */
	public static void main(String[] args) {
		if (args != null && args.length > 0 && args[0] != null)
			System.out.println("Argument Length = " + args[0].length());
	}

}
