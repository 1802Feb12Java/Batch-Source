package com.revature;

import java.util.Scanner;

public class CountChars16 {

	public static void count() {
		
		System.out.println("What is the string?");
		Scanner sc = new Scanner(System.in);
		
		String word = sc.nextLine();
		System.out.println("Your string length is " + word.length());		// built in function return length of string
	}
}
