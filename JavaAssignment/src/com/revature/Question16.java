/*  This is a class file for counting the number of characters in a string
 * 
 * @author Dominic Nguyen
 */

package com.revature;

import java.util.Scanner;

public class Question16 {

	// constructor
	public Question16() {
		
	}
	
	public static Scanner scan = new Scanner(System.in);
	
	/*  This is a method for counting the number of characters in a string
	 *  @param args - command line argument of type string
	 */
	public void characterCount(String args) {
		System.out.println("Q16: Enter a string: ");
		args = scan.nextLine();

		System.out.println("Character count: " + args.length());
		
		//scan.nextLine();
	}
}
