package com.revature;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		// Question one - bubble sort
		int nums[] = {1,0,5,6,3,2,3,7,9,8,4};
		new Q1(nums);
		
		// Question two - first x Fibonacci numbers
		new Q2(25);
		
		// Question three - reverse a string
		new Q3("supercalifragilisticexpialidocious");
		
		// Question four - calculate x factorial
		new Q4(8);
		
		// Question five - create a substring to the given index-1
		// This will return a substring the length of the given parameter
		new Q5("antidisestablishmentarianism", 10);
		
		// Question six - determine if given number is even
		new Q6(30);
		
		// Question seven - sort employees using Comparator interface
		System.out.println("Q7: ");
		Q7Employee employee[] = new Q7Employee[2];
		
		employee[0] = new Q7Employee();
		employee[0].setName("Bob");
		employee[0].setDepartment("Finance");
		employee[0].setAge(25);
		
		employee[1] = new Q7Employee();
		employee[1].setName("Joe");
		employee[1].setDepartment("Accounting");
		employee[1].setAge(45);
		
		Arrays.sort(employee, new Q7NameComparator());
		System.out.println("Order of employees by name:");
		for (int i = 0; i < employee.length; i++) {
			System.out.println(employee[i].getName() + "\t"
					+ employee[i].getDepartment() + "\t"
					+ employee[i].getAge());
		}
		System.out.println("");
		
		Arrays.sort(employee, new Q7DepartmentComparator());
		System.out.println("Order of employees by department:");
		for (int i = 0; i < employee.length; i++) {
			System.out.println(employee[i].getName() + "\t"
					+ employee[i].getDepartment() + "\t"
					+ employee[i].getAge());
		}
		System.out.println("");
		
		Arrays.sort(employee, new Q7AgeComparator());
		System.out.println("Order of employees by age:");
		for (int i = 0; i < employee.length; i++) {
			System.out.println(employee[i].getName() + "\t"
					+ employee[i].getDepartment() + "\t"
					+ employee[i].getAge());
		}
		System.out.println("");
		
		// Question 8 - Store strings and palindromes in separate array lists
		String[] myList = {"karan", "madam", "tom", "civic", "radar", "jimmy", "kayak", "john",  "refer", "billy", "did"};
		new Q8(myList);
		
		// Question 9 - Create array list to store numbers from 1 to x
		new Q9(100);
		
		// Question 10 - Find minimum of two numbers using ternary operators
		new Qu10(80, 79);
		
		// Question 11 - Access two variables from a class in another package
		new Qu11();
		
		// Question 12 - Store numbers from 1 to x and print out even numbers
		new Qu12(100);
		
		// Question 13 - Display a 0 1 triangle for the given amount of lines
		new Qu13();
		
		// Question 14 - Perform function based on case 1, 2 or 3
		// Second parameter is used for the square root function for case 1
		new Qu14(3, 100);
		
		// Question 15 - Create CustomCalc interface
		Qu15 q15 = new Qu15();
		q15.addition(10, 25);
		q15.subtraction(50, 20);
		q15.multiplication(16, 24);
		q15.division(100, 50);
		
		// Question 16 - Display number of characters for a string input from a command line argument
		int strLen = args.length;
		System.out.println("Q16: The numbers of characters in the string is: " + strLen);
		
		// Question 17 - Scanner class to take input (Principal, Rate, Time) to calculate simple interest
		// Uncomment next line to use scanner
//		new Qu17();
		
		// Question 18 - Use concrete class to inherit methods and perform actions
		Qu18B q18 = new Qu18B();
		q18.checkUppercase("alsdkjfklAsdjflkdas");
		q18.toUppercase("This is a string");
		q18.toInt("This is a sentence that could go on for a while, but will end now.");
		
		// Question 19 - Perform functions for numbers 1 through x
		new Qu19(50);
		
		// Question 20 - Create and read from Data.txt file
		new Qu20();
		
		
		

		
	}

}