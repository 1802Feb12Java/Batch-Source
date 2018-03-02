package com.revature;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QuestionFourteen {

	public void testCases(int whichCase) {
		switch (whichCase) {
		case 1:
			// prints square root of number "rootThis"
			int rootThis = 64;
			System.out.println("Square root of " + rootThis + " is " + Math.sqrt(rootThis));
			break;
		// prints date in month/day/year format
		case 2:
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate localDate = LocalDate.now();
			System.out.println(dateTimeFormatter.format(localDate));
			break;
		// split string at space
		case 3:
			String[] stringArray = "I am learning core Java".split(" ");
			for (String s : stringArray) {
				System.out.println(s);
			}
			break;
		default:
			System.out.println("Choose case 1, 2, or 3");
			break;
		}
	}

}