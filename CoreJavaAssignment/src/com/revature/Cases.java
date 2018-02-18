package com.revature;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Arrays;

//using switch cases to print oiut diffent things
public class Cases {
	
	//method that calculates the square root using java.lang.Math
	private static double sqr(double d) {
		return java.lang.Math.sqrt(d);
	}
	
	//prints out the current date using java.util.Date
	private static void printDate() {
		DateFormat dF = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		System.out.println(dF.format(date));
	}
	
	//split the string and store it in a string array
	private static void printMsg() {
		String strToSplit = "I am learning Core Java";
		String [] tokens = strToSplit.split(" ",0);
		System.out.println(Arrays.toString(tokens));
	}
	
	public static void test(int i) {
		switch(i) {
		case 1:
			System.out.println(sqr(124));
			break;
		case 2:
			printDate();
			break;
		case 3:
			printMsg();
			break;
		}
	}
}
