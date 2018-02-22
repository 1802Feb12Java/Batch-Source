package com.revature.scanner;

import java.util.Scanner;

public class FunScan {

	// set Scanner to the standard input stream
	public static Scanner sc = new Scanner(System.in);

	public static String printMe() {
		String contents = "";

		while (sc.hasNext())
			System.out.println(contents = sc.next());
		return contents;
	}

	public static void main(String[] args) {
		System.out.println("Enter quit to quit");
		while (!printMe().equals("quit"))
			;
	}

}
