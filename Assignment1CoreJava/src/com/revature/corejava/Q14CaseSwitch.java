package com.revature.corejava;

import java.time.LocalDate;
import java.util.StringTokenizer;

/*Question 14: Write a program that demonstrates the switch case. Implement the following functionalities in the cases:java 
Case 1: Find the square root of a number using the Math class method.
Case 2: Display today’s date.
Case 3: Split the following string and store it in a string array.
           	“I am learning Core Java”
*/
           	
public class Q14CaseSwitch {
	public static void switchUp(int selection) {
		switch(selection) {
		case 1:
			System.out.println("The square root of 100 is " + Math.sqrt(100));
			System.out.println("    There's a break here, calling function with option 2");
			break;
			
		case 2:
			System.out.println("Today is: " + LocalDate.now());
			System.out.println("    There's a break here, calling function with option 3");
			break;
			
		case 3:
			//tokenize the string
			StringTokenizer string = new StringTokenizer("I am learning Core Java");
			String [] tokenized = new String[string.countTokens()];
			int index = 0;
			
			//add the tokens into an array
			while(string.hasMoreTokens()) {
				tokenized[index] = string.nextToken();
				index++;
			}
			
			System.out.println("Tokenized with manual spaces: " + tokenized[2] + " " + tokenized[4] 
					+ " " + tokenized[3] + "," + tokenized[0] + " " + tokenized[1]);
			System.out.println("    Breaks are over, get back to work!");
			break;
			
		default:
			break;
		}
	}
}
