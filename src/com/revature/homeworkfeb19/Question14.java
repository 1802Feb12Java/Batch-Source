package com.revature.homeworkfeb19;

//Q14. Write a program that demonstrates the switch case. Implement the following functionalities in the cases:java 
//Case 1: Find the square root of a number using the Math class method.
//Case 2: Display today’s date.
//Case 3: Split the following string and store it in a string array.
//               “I am learning Core Java”

import java.util.Date;

public class Question14 {

	static void pick1to3(int choice)
	{
		switch(choice)
		{
			case 1: {//find sqrt of number using math
				System.out.println(Math.sqrt(4) + "is the square root of 4");
				break;
			}
			case 2:{//display date
				Date date = new Date();
				System.out.println(date);
				break;
			}
			case 3:{//split string to string array
				String str = "I am learning Core Java";
				String[] arr = str.split("\\ ", -1);
				System.out.println(arr[2]);
				break;
				
			}
			default: {
				System.out.println("you broke the rules. choose a number between one and three.");
			}
		}
	}
	
}
