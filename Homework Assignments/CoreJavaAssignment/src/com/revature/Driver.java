package com.revature;

import java.util.Arrays;
import java.util.Scanner;

/* The purpose of this class is simply to run each class for each question.
 * All output required for the assignment will be printed using this class.
 */

public class Driver {

	public static void main(String[] args) {		
		System.out.println("Core Java Assignment");
		System.out.println("Jessica Colson");
		System.out.println();
		System.out.println();
		
		System.out.println("Question 1:");
		System.out.println();
		int[] myArray = {1,0,5,6,3,2,3,7,9,8,4};
		System.out.println("The original array " + Arrays.toString(myArray));
		BubbleSort.myBubbleSort(myArray);
		System.out.println("The sorted array " + Arrays.toString(myArray));
		System.out.println();
		System.out.println();
		
		/* The following block of code is repeated throughout the driver to allow
		* adequate time to review each questions output before the next question
		* prints to the console.
		*/
		
		Scanner read = new Scanner(System.in);
		boolean cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 2:");
		System.out.println();
		System.out.println("The first 25 Fibonacci Numbers:");
		FibonacciGenerator.fibonacciList(25);
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 3:");
		System.out.println();
		StringReverse.runStringReverse();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 4:");
		System.out.println();
		Factorial.runFactorial();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 5:");
		System.out.println();
		MySubstringClass.runMySubstring();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 6:");
		System.out.println();
		EvenNumbers.runEvenNumbers();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 7:");
		System.out.println();
		Employee.runEmployee();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 8:");
		System.out.println();
		StringArrays.runStringArrays();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 9:");
		System.out.println();
		PrimeArrays.runPrimeArrays();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 10:");
		System.out.println();
		TernaryMins.runTernaryMins();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 11:");
		System.out.println();
		AccessingMyFloatClass.runAccessingMyFloatClass();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 12:");
		System.out.println();
		PrintEvenNums.printEvenNums();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 13:");
		System.out.println();
		PrintTriangle.printTriangle();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 14:");
		System.out.println();
		SwitchDemo.switchDemo();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 15:");
		System.out.println();
		TestMyMath.runTestMyMathClass();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 16:");
		System.out.println();
		CountingCharacters.runningCountingCharacters();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 17:");
		System.out.println();
		InterestCalculator.runInterestCalculator();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 18:");
		System.out.println();
		TestCasingClass.runTestCasingClass();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 19:");
		System.out.println();
		PlayingWithArrayLists.runPlayingWithArrayLists();
		System.out.println();
		System.out.println();
		
		cont = false;
		while (!cont) {
			System.out.println("Press enter to continue.");
			read.nextLine();
			cont = true;
		}
		
		System.out.println("Question 20:");
		System.out.println();
		ReadingAFile.readAndPrintFile();
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("Thank You.");
		read.close();
		
	}//end main

}//end class
