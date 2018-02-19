package com.revature.corejavaassignment;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class Q14 {
/*
 * Q14. Write a program that demonstrates the switch case. Implement the following 
 * functionalities in the cases:java 
 * 
 * Case 1: Find the square root of a number using the Math class method.
 * Case 2: Display today’s date.
 * Case 3: Split the following string and store it in a string array.
 *            	“I am learning Core Java”
 */
	public static void answer() {
		System.out.println("Q14. Write a program that demonstrates the switch case. Implement the following \r\n" + 
				" * functionalities in the cases:java \r\n" + 
				" * \r\n" + 
				" * Case 1: Find the square root of a number using the Math class method.\r\n" + 
				" * Case 2: Display today’s date.\r\n" + 
				" * Case 3: Split the following string and store it in a string array.\r\n" + 
				" *            	“I am learning Core Java”\n");
		System.out.println("Switch statement case 1");
		switchJava(1);
		System.out.println("Switch statement case 2");
		switchJava(2);
		System.out.println("Switch statement case 3");
		switchJava(3);
		System.out.println("Switch statement case 4");
		switchJava(4);
	}
	/*
	 * this method takes an int and passes it to another method based on value
	 * where the other method does one of four things
	 * 1-makes a random number and takes its square root
	 * 2- displays todays date
	 * 3-splits a string by space and stores it in a string array then prints it
	 */
	public static void switchJava(int java) {
		switch(java) {
			case 1: case1(); break;
			case 2: case2(); break;
			case 3: case3(); break;
			default: System.out.println("I don't know what to do......Roll Tide?? ¯\\_(ツ)_/¯\n");
		}
	}
	public static void case1() {
		double num=Math.random()*100000;
		System.out.println(Math.sqrt(num));
	}
	public static void case2() {
		System.out.println(LocalDate.now());
	}
	public static void case3() {
		String string = "I am learning Core Java";
		String[] split = string.split(" ");
		System.out.println(Arrays.toString(split));
	}
}
