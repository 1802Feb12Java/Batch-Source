package com.revature.answers;

import java.util.Scanner;

public class Question16 {

	public static Scanner sc = new Scanner(System.in);		//new scanner to take in input
	
	public static void main(String[] args) {

		stringCount();
		
	}
	
	public static void stringCount() {
		String input = "";
		System.out.println("Type a String, and I will count the characters!");
		input = sc.nextLine();
		System.out.println(input.length());			//returns how long the string is, aka the number of chars.
		
	}

}
