package com.revature;

import java.lang.Math;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Q14 {

	public void switchCase(int c) { //case is decided in Driver class
		switch(c) {
			case 1: double num = 3.14;
					System.out.println("The square root of " + num + " is " + Math.sqrt(num));
					//determines square root of number
					
			case 2: Date today = new Date();
					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
					System.out.println(dateFormat.format(today));
					//prints Date
					
			case 3: String sentence = "I am learning Core Java";
					String[] sentSplit = sentence.split(" ");
					System.out.println(Arrays.toString(sentSplit));
					//splits string and puts in String array
		}
	}
}
