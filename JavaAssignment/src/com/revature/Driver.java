package com.revature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.revature.Q11Class.Q11Class;

public class Driver {

	public static void main(String[] args) {

		//Q1-Bubble Sort
		int[] intArray = new int[] {1,0,5,6,3,2,3,7,9,8,4};
		   //print unordered array
			for(int i : intArray)
			{
				System.out.print(i + " ");
			}
			//print line break
		   System.out.println("\n");
		   // sort array
			Q1.bubbleSort(intArray);
			//print sorted array
			for(int i : intArray)
				{
					System.out.print(i + " ");
				}
		
		
	/*
	 * ------------------------------------
	 */
	//Q2 - first 25 Fibb
	System.out.println("\n");
	Q2.displayFibonacci();
	
	//Q3 - Reverse String
	System.out.println("\nReverse String");

	String temp = "Try me!";
	System.out.println(Q3.stringReverse(temp));
	//Q4 - N Factorial
	//creates scanner for user input
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter a factorial number: ");
			//get user input to compute factorial
			long userInput = scanner.nextLong();
			//executes factorial function
			Q4.factorial(userInput);
			System.out.println(userInput+ "! = "+ Q4.factorial(userInput));
	//Q5 - return sub string
			System.out.println(Q5.subString("Try to slice pizza", 8));
	//Q6 - check even or odd without using %
			System.out.println("Enter a number to check for even or odd");
			int num = scanner.nextInt();
			Q6.checkEven(num);
	//Q7 - using Comparator
			Q7.displaySorted();

	//Q8 - palindromes
    	Q8.arrayListPalindromes();
    //Q9 - print prime array
    	Q9.primeArrayList();

    //Q10 - ternary operators
		Q10.minimum(23,54);
	//Q11 - access variables from another package
		Q11Class q = new Q11Class(123,13);
		Q11.add();
	//Q12 - print even numbers using enhanced for loop
		Q12.numArray();
	//Q13 - display triangle
		Q13.print(10);
	//Q14
		Q14.print();
    //Q15
		Q15 q2 = new Q15();
		q2.addition(10, 11);
		q2.division(5,10);
		
    //Q16 - print string length in line argument
		//Q16.print();
	//Q17 - prints interest rate
		Q17.printInterest();

	//Q18 - uppercase
		Q18.print();
	//Q19 - prints prime number and odd/even sum
		ArrayList<Integer> intArrList = Q19.initializeArrayList();
		Q19.addSum(intArrList);
		Q19.primeNum(intArrList);
	//Q20 - file I/O
		Q20.file();



	}
}	
