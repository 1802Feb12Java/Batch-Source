package com.revature.corejava;
import java.util.Arrays;
import java.util.Scanner;


public class Driver {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Scott Bennett - Core Java Assignment");
		System.out.println("------Begin Question 1 Output------");
		int[] inputArray = {1,0,5,6,3,2,3,7,9,8,4};
		System.out.println("Created Input Array: " + Arrays.toString(inputArray));
		System.out.println("Calling Bubble Sort Method");
		System.out.println("Bubble Sort Done");
		Question1.bubbleSort(inputArray);
		System.out.println("Contents of Input Array: "+ Arrays.toString(inputArray));
		System.out.println("------End Question 1 Output------");
		
		System.out.println("------Begin Question 2 Output------");
		System.out.println("The first 25 Fibonacci numbers are:");
		Question2.displayFibonacciNumbers();
		System.out.println("------End Question 2 Output------");
		
		System.out.println("------Begin Question 3 Output------");

		System.out.println("------End Question 3 Output------");
		
		System.out.println("------Begin Question 4 Output------");

		System.out.println("------End Question 4 Output------");
		System.out.println("Enter a number < 20 to compute a factorial.");
		int input = scanner.nextInt();
		System.out.println("You Entered " + input);
		int result = Question4.computeFactorial(input);
		if (result > -1) {
			System.out.println(input + "! is: " + result);
		}
		System.out.println("------Begin Question 5 Output------");

		System.out.println("------End Question 5 Output------");
		
		scanner.close();

	}

}
