package com.revature.scanner;

import java.util.Scanner;

public class FunScan {
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void printMe() {
		String contents;
		System.out.println("What do you want to print?");
		//contents = sc.next();            Gets everything up until a space
		//sc.reset();                      Empties out the scanner so it's fresh for the next time. Maybe
		contents = sc.nextLine();
		sc.reset();
		
		System.out.println(contents);
	}
	
	public static void main(String[] args) {
		printMe();
	}
	
}
