package com.revature;

import java.util.Scanner;

public class Q4 {
	public void run() {
		System.out.println("1! = " +factorial(1));	//print out a couple samples
		System.out.println("4! = " +factorial(4));
		System.out.println("7! = " +factorial(7));
		System.out.println("10! = " +factorial(10));
		Scanner scan = new Scanner(System.in);
		System.out.print("Want to test your own? Type an integer between 0 and 20 (inclusive, but 21! exceeds long's range): ");
		int userInput = scan.nextInt();		//get some user input because Matt said to
		System.out.println(userInput + "! = "+factorial(userInput));
	}
	
	public long factorial(int n) {
		if(n==0) {
			return 1;
		}
		long nFactorial = n;		//this will be the final thing returned
		int numToMultiplyBy = n-1;	//this is the next number to multiply nFactorial by in each step
		while(numToMultiplyBy > 1) {	//go until you hit 1, at which point multiplying by 1 will do nothing
			nFactorial *= numToMultiplyBy;	//multiply the current factorial value by the next number down in the list
			numToMultiplyBy--;	//decrement the next number in the list
		}
		return nFactorial;
	}
}
