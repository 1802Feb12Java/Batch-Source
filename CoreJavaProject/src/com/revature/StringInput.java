package com.revature;

import java.util.Scanner;

public class StringInput {
	
	//example of counting all the characters in all the strings of args
	public static void example(String[] args) {
		
		int totalChars = 0;
		
		System.out.println("args[] contains: " + args.length + " total strings");
		
		//if args contains more than 0 elements
		if(args.length > 0) {		
			//get total characters for all strings in args[]
			for(int i = 0; i < args.length; i++) {
				totalChars += args[i].length();
			}
		}
		else {
			
			//get total characters for all strings in args[]
			System.out.println("No string from String args[]");
		}
		
		System.out.println("args[] contains: " + totalChars + " total characters");

	}
}
