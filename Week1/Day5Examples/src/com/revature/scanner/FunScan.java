package com.revature.scanner;

import java.util.Scanner;

public class FunScan {
	public static Scanner sc = new Scanner(System.in);
	
	public static void printMe() {
		System.out.println("What would you like to print?");
		String contents = sc.nextLine();
		System.out.println(contents);
	}
	
	public static void main (String[] args) {
		printMe();
	}
}