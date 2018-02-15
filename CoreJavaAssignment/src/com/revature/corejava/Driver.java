package com.revature.corejava;
import java.util.Arrays;
import java.util.Scanner;


public class Driver {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String stringInput;
		int intInput;
/*		System.out.println("Scott Bennett - Core Java Assignment");
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
		System.out.println("Enter a string to reverse");
		stringInput = scanner.nextLine();
		System.out.println(stringInput + " reversed is: " + 
							Question3.reverseString(stringInput));
		
		System.out.println("------End Question 3 Output------");
		
		System.out.println("------Begin Question 4 Output------");
		System.out.println("Enter a number < 20 to compute a factorial.");
		intInput = scanner.nextInt();
		scanner.nextLine(); //consumes trailing \n
		System.out.println("You Entered " + intInput);
		int result = Question4.computeFactorial(intInput);
		if (result > -1) {
			System.out.println(intInput + "! is: " + result);
		}
		System.out.println("------End Question 4 Output------");
		
		System.out.println("------Begin Question 5 Output------");
		System.out.println("Enter a string");
		stringInput = scanner.nextLine();
		System.out.println("Enter an index to get substring");
		intInput = scanner.nextInt();
		scanner.nextLine(); //consumes trailing \n
		System.out.println("The substring is: " + Question5.getSubstring(stringInput, intInput));
		System.out.println("------End Question 5 Output------");
		
		System.out.println("------Begin Question 6 Output------");
		System.out.println("Enter a number to check parity.");
		intInput = scanner.nextInt();
		scanner.nextLine(); //consumes trailing \n
		System.out.println(intInput + " is even: " + Question6.isEven(intInput));
		System.out.println("------End Question 6 Output------");
		
		System.out.println("------Begin Question 7 Output------");

		System.out.println("------End Question 7 Output------");
		
		System.out.println("------Begin Question 8 Output------");
		Question8.storePalindromes();
		System.out.println("------End Question 8 Output------");
		
		System.out.println("------Begin Question 9 Output------");
		Question9.printPrimes();
		System.out.println("------End Question 9 Output------");*/
		
		System.out.println("------Begin Question 10 Output------");
		System.out.println("Enter First Number");
		int a = scanner.nextInt();
		scanner.nextLine();
		System.out.println("Enter second number");
		int b = scanner.nextInt();
		scanner.nextLine();
		System.out.println("The smaller number is: " + Question10.findMinimum(a, b));
		System.out.println("------End Question 10 Output------");
		System.out.println("------Begin Question 11 Output------");

		System.out.println("------End Question 11 Output------");
		
		System.out.println("------Begin Question 12 Output------");

		System.out.println("------End Question 12 Output------");
		System.out.println("------Begin Question 13 Output------");

		System.out.println("------End Question 13 Output------");
		System.out.println("------Begin Question 14 Output------");

		System.out.println("------End Question 14 Output------");
		System.out.println("------Begin Question 15 Output------");

		System.out.println("------End Question 15 Output------");
		System.out.println("------Begin Question 16 Output------");

		System.out.println("------End Question 16 Output------");
		System.out.println("------Begin Question 17 Output------");

		System.out.println("------End Question 17 Output------");
		System.out.println("------Begin Question 18 Output------");

		System.out.println("------End Question 18 Output------");
		System.out.println("------Begin Question 19 Output------");

		System.out.println("------End Question 19 Output------");
		System.out.println("------Begin Question 20 Output------");

		System.out.println("------End Question 20 Output------");
		scanner.close();

	}

}
