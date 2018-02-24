package com.revature;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Q14 implements Runnable {
	private static final String TAB = "    ";
	@Override
	public void run() {
		System.out.println("Question 14: Switch-Case");
		for(int i = 0; i < 5; ++i) {
			System.out.println(TAB + "Choice: " + i);
			switchTest(i);
			System.out.println();
		}

	}
	
	public static void switchTest(int choice) {
		switch(choice) {
		case 1:
			int randNum = (new Random()).nextInt();
			System.out.println(TAB + "sqrt(" + randNum + ") = " 
								+ Math.sqrt(randNum));
			break;
		case 2:
			DateFormat fmt = new SimpleDateFormat("dd LLLL YYYY");
			System.out.println(TAB + "Date: " + fmt.format(new Date()));
			break;
		case 3:
			String str = "I am learning Core Java";
			String[] strAry = str.split(" ");
			System.out.println(TAB + "Input: " + str);
			System.out.println(TAB + "Result Array: " + Arrays.toString(strAry));
			break;
		default:
			System.out.println(TAB+ "Not a valid case.");
		}
	}
	

}
