package com.revature;

import java.util.Scanner;

public class Q3 {
	public void run() {
		System.out.println("Alphabet backwards: "+reverseString("abcdefghijklmnopqrstuvwxyz"));
		Scanner s = new Scanner(System.in);	//get some user input because why not
		System.out.print("Try your own? Type in a String: ");
		System.out.println("Your String backwards = " + reverseString(s.nextLine()));	//reverse their string
	}
	
	public String reverseString(String s) {
		String reverse = "";
		for(int i=s.length()-1; i>=0; i--) {	//go through the string
			reverse += s.charAt(i);	//add the last character to the front of the new one
		}
		return reverse;
	}
}
