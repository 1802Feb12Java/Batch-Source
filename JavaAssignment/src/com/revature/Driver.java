/*  This is a driver class for the Java Assignment
 * 
 *  @author Dominic Nguyen
 */

package com.revature;

import java.util.Scanner;

//import com.revature.io.IO;

public class Driver {
	
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int[] array1 = {1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4};
		int[] array2 = new int[25];
		String str = "This is a string"; 
		int factorialNum = 5;
		String str2 = "This is a second string"; 
		int idx = 10; 
		int number = 10;
		String[] str3 = {"karan", "madam", "tom", "civic", "radar", "jimmy", "kayak", "john", "refer", "billy", "did"};
		int strCount = 11; // string element count
		int maxNum = 100;
		int max1 = 50;
		int max2 = 80;
		int input = 0;  // scanner input variable
		Addition add = new Addition("A", "Addition");
		Subtraction sub = new Subtraction("S", "Subtraction");
		Multiplication mult = new Multiplication("M", "Multiplication");
		Division div = new Division("D", "Division");
		Question18 str4 = new Question18("s", "String");
		String str5 = "This Is A Third String";
		boolean bool;
		String str6;
		int strToInteger = 0;
		String str7 = "";
		String answer = "yes";
		
		// create objects
		Question1 q1 = new Question1();
		Question2 q2 = new Question2(); 
		Question3 q3 = new Question3();
		Question4 q4 = new Question4(); 
		Question5 q5 = new Question5();
		Question6 q6 = new Question6();
		//Question7 q7 = new Question7();
		Question8 q8 = new Question8();
		Question9 q9 = new Question9();
		Question10 q10 = new Question10();
		Question11 q11 = new Question11();
		Question12 q12 = new Question12();
		Question13 q13 = new Question13();
		Question14 q14 = new Question14();
		Question16 q16 = new Question16();
		Question17 q17 = new Question17();
		//Question18 q18 = new Question18();
		Question19 q19 = new Question19();
		Question20 q20 = new Question20();
		
		q1.BubbleSort(array1);  // sort array using bubble sort
		q2.Fibonacci(array2); // get the first 25 fibonacci numbers
		q3.ReverseString(str); // Reverse the string
		q4.Factorial(factorialNum); // get the n factorial for the specified number
		q5.SubString(str2, idx); // get the substring
		q6.IsEven(number); //check if the number is even or odd
		q8.ArrayStorage(str3, strCount); // find the palindrome strings
		q9.PrimeNumber(maxNum); // find the prime numbers
		q10.MinimumNumber(max1, max2); // get the minimum number of 2 numbers
		q11.AccessPackage();  // access a class with another package
		q12.EnhancedForLoop();  // display even numbers using enhanced for loop
		q13.Triangle();  // display triangle using 1's and 0's
		
		System.out.println("Q14: Enter a switch case integer value from 1 to 3:");
		input = scan.nextInt();
		scan.nextLine();
		
		q14.Switch(input); // perform the chosen switch case
		
		System.out.println("Q15: ");
		
		// Perform calculations using an interface
		add.calculate();
		sub.calculate();
		mult.calculate();
		div.calculate();
		
		args = new String[4];   // create an array of type String for the command line argument variable args
		q16.characterCount(args[0]);  // get the character count for the input string
		
		q17.simpleInterest(args);  // get the simple interest rate
		
		bool = str4.checkUpperCase(str5);  // check if the string has upper case characters
		System.out.println("Q18: Does the string contain upper case letters? " + bool);
		str6 = str4.upperCase(str5);  // change the string to upper case
		System.out.println("Upper case: " + str6);
		strToInteger = str4.stringToInteger(str5);  // Convert the string to an integer value
		System.out.println("Integer value: " + strToInteger);
		
		q19.integerArray();  // create an ArrayList for storing integer values

		System.out.println("Do you want to add text to the file (yes or no)?");
		answer = scan.nextLine();
		
		// create a text file, get user input for writing to the file, and display the content of the file
		while(answer.equals("yes")) {
			System.out.println("Enter a string to write to the file: ");
			str7 = scan.nextLine();
			
			q20.writeOutputStreamContents(str7);
			q20.writeOutputStreamContents("\n");
			
			System.out.println("Do you want to add more text to the file (yes or no)?");
			answer = scan.nextLine();
		}
		
		// display the content of the file
		System.out.println(q20.readInputStreamContents());
	}
}
