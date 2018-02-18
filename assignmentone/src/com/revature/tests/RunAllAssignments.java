package com.revature.tests;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import com.revature.assignments.AssignmentEight;
import com.revature.assignments.AssignmentEighteenSub;
import com.revature.assignments.AssignmentEleven;
import com.revature.assignments.AssignmentFifteenDriver;
import com.revature.assignments.AssignmentFive;
import com.revature.assignments.AssignmentFour;
import com.revature.assignments.AssignmentFourteen;
import com.revature.assignments.AssignmentNine;
import com.revature.assignments.AssignmentNineteen;
import com.revature.assignments.AssignmentOne;
import com.revature.assignments.AssignmentSeven;
import com.revature.assignments.AssignmentSeventeen;
import com.revature.assignments.AssignmentSix;
import com.revature.assignments.AssignmentSixteen;
import com.revature.assignments.AssignmentTen;
import com.revature.assignments.AssignmentThirteen;
import com.revature.assignments.AssignmentThree;
import com.revature.assignments.AssignmentTwelve;
import com.revature.assignments.AssignmentTwenty;
import com.revature.assignments.AssignmentTwo;
import com.revature.assignments.Employee;
import com.revature.assignments.eleven.Floats;

class RunAllAssignments {

	// Perform a bubble sort on the following integer array: 1,0,5,6,3,2,3,7,9,8,4
	@Test
	void assignmentOne() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		int[] testList = { 1, 0, 5, 6, 3, 2, 3, 7, 9, 8, 4 };
		int[] sortedList = { 0, 1, 2, 3, 3, 4, 5, 6, 7, 8, 9 };
		System.out.println("TestList " + Arrays.toString(testList));
		System.out.println("== SortedList " + Arrays.toString(sortedList) + "?");
		assertArrayEquals(AssignmentOne.bubbleSort(testList), sortedList);
	}

	// Write a program to display the first 25 Fibonacci numbers beginning at 0
	@Test
	void assignmentTwo() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		assertEquals(AssignmentTwo.fibonacci(25), 75025);
		System.out.println("Printing the first 25 Fibonacci Numbers");
		AssignmentTwo.printFibonacci(25);
	}

	// Reverse a string without using a temporary variable. Do NOT use reverse() in
	// the StringBuffer or the StringBuilder APIs.
	@Test
	void assignmentThree() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		System.out.println("Reversing 12345 = " + AssignmentThree.reverse("12345"));

		assertEquals(AssignmentThree.reverse("racecar"), "racecar");
		assertEquals(AssignmentThree.reverse("jennifer"), "refinnej");
		assertEquals(AssignmentThree.reverse(""), "");
		assertEquals(AssignmentThree.reverse("12345"), "54321");

	}

	// Write a program to compute N factorial.
	@Test
	void assignmentFour() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		System.out.println("Compute 5 factorial = " + AssignmentFour.computeFactorial(5));
		System.out.println("Compute 10 factorial = " + AssignmentFour.computeFactorial(10));
		assertEquals(AssignmentFour.computeFactorial(0), 1);
		assertEquals(AssignmentFour.computeFactorial(1), 1);
		assertEquals(AssignmentFour.computeFactorial(5), 120);
		assertEquals(AssignmentFour.computeFactorial(10), 3628800);
	}

	// Write a substring method that accepts a string str and an integer idx and
	// returns the substring contained between 0 and idx-1 inclusive. Do NOT use any
	// of the existing substring methods in the String, StringBuilder, or
	// StringBuffer APIs.
	@Test
	void assignmentFive() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		System.out.println("Substring: kittens, 5 = " + AssignmentFive.subString("kittens", 5));

		assertEquals(AssignmentFive.subString("asdf", 2), "asdf".substring(0, 2));
		assertEquals(AssignmentFive.subString("kittens", 7), "kittens".substring(0, 7));
		assertEquals(AssignmentFive.subString("", 0), "".substring(0));
	}

	// Write a program to determine if an integer is even without using the modulus
	// operator (%)
	@Test
	void assignmentSix() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		System.out.println("Three even? " + AssignmentSix.isEven(3));
		System.out.println("Four even? " + AssignmentSix.isEven(4));

		assertTrue(AssignmentSix.isEven(0));
		assertTrue(AssignmentSix.isEven(2));
		assertTrue(AssignmentSix.isEven(20));
		assertTrue(AssignmentSix.isEven(212));

		assertFalse(AssignmentSix.isEven(1));
		assertFalse(AssignmentSix.isEven(3));
		assertFalse(AssignmentSix.isEven(7));
		assertFalse(AssignmentSix.isEven(211));
		assertFalse(AssignmentSix.isEven(1003));

	}

	// sort two employees based on their name, dept, and age using comparator
	// interface
	@Test
	void assignmentSeven() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		ArrayList<Employee> emps = AssignmentSeven.generateEmployeeList();
		assertArrayEquals(emps.toArray(), emps.toArray());
		// sort by name
		Collections.sort(emps, Employee.getNameCompare());
		System.out.println("\nSorted by name:");
		for (Employee e : emps)
			System.out.println("\t" + e);

		// sort by dept
		Collections.sort(emps, Employee.getDepartmentCompare());
		System.out.println("\nSorted by department:");
		for (Employee e : emps)
			System.out.println("\t" + e);

		// sort by age
		Collections.sort(emps, Employee.getAgeCompare());
		System.out.println("\nSorted by age:");
		for (Employee e : emps)
			System.out.println("\t" + e);

	}

	// Write a program that stores the following strings in an ArrayList and saves
	// all the palindromes in another ArrayList
	@Test
	void assignmentEight() {
		String[] palindromes = { "madam", "civic", "radar", "kayak", "refer", "did" };
		String[] nonPalindromes = { "karan", "tom", "jimmy", "john", "billy" };

		AssignmentEight ao8 = new AssignmentEight();
		ao8.storeWords("karan", "madam", "tom", "civic", "radar", "jimmy", "kayak", "john", "refer", "billy", "did");
		// check palindrome list
		assertArrayEquals(ao8.getPalindromes().toArray(), palindromes);
		System.out.println("\nPalinedrome List:" + Arrays.toString(ao8.getPalindromes().toArray()));
		// check non palindrome list
		assertArrayEquals(ao8.getNonPalindromes().toArray(), nonPalindromes);
		System.out.println("\nNon-Palinedrome List:" + Arrays.toString(ao8.getNonPalindromes().toArray()));
	}

	// Create an ArrayList which stores numbers from 1 to 100 and prints out all
	// the prime numbers to the console
	@Test
	void assignmentNine() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		System.out.println("Print out all the prime numbers to console");
		AssignmentNine ao9 = new AssignmentNine(100);
		ao9.printPrimes();
		Integer[] primesToHundred = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
				79, 83, 89, 97 };
		assertArrayEquals(ao9.getPrimeNumbers().toArray(), primesToHundred);

	}

	// Find the minimum of two numbers using ternary operators.
	// overload to account for other number types
	@Test
	void assignmentTen() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		long l1 = 2l;
		long l2 = 5l;
		// long
		System.out.println("Mininmum of 2,5 long type = " + AssignmentTen.min(l1, l2));
		assertTrue(AssignmentTen.min(l1, l2).equals(l1));
		// int
		int i1 = 2;
		int i2 = 5;
		System.out.println("Mininmum of 2,5 int type = " + AssignmentTen.min(i1, i2));
		assertTrue(AssignmentTen.min(i1, i2).equals(i1));

		// double
		double d1 = 2.0;
		double d2 = 5.0;
		System.out.println("Mininmum of 2,5 double type = " + AssignmentTen.min(d1, d2));
		assertTrue(AssignmentTen.min(d1, d2).equals(d1));
		// float

		float f1 = 2f;
		float f2 = 5f;
		System.out.println("Mininmum of 2,5 float type = " + AssignmentTen.min(f1, f2));
		assertTrue(AssignmentTen.min(f1, f2).equals(f1));

	}

	// Write a program that would access two float-variables from a class that
	// exists in another package. Note, you will need to create two packages to
	// demonstrate the solution
	@Test
	void assignmentEleven() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		System.out.println("Accessing float one " + AssignmentEleven.getF1());
		System.out.println("Accessing float two " + AssignmentEleven.getF2());
		assertEquals(AssignmentEleven.getF1(), Floats.getF1());
		assertEquals(AssignmentEleven.getF2(), Floats.getF2());

	}

	// Write a program to store numbers from 1 to 100 in an array. Print out all the
	// even numbers from the array. Use the enhanced FOR loop for printing out the
	// numbers.
	@Test
	void assignmentTwelve() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		AssignmentTwelve ao12 = new AssignmentTwelve();
		System.out.println("Printing all even numbers from 1-100:");
		ao12.printEvens();
	}

	// Display the triangle on the console as follows using any type of loop. Do NOT
	// use a simple group of print statements to accomplish this.
	// 0
	// 1 0
	// 1 0 1
	// 0 1 0 1
	@Test
	void assignmentThirteen() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		AssignmentThirteen.printIlluminati();
	}

	/*
	 * Write a program that demonstrates the switch case. Implement the following
	 * functionalities in the cases:java Case 1: Find the square root of a number
	 * using the Math class method. Case 2: Display today’s date. Case 3: Split the
	 * following string and store it in a string array. “I am learning Core Java”
	 * 
	 */
	@Test
	void assignmentFourteen() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		System.out.println("Switch Case 0");
		AssignmentFourteen.switchTest(0);
		System.out.println("Switch Case 1");
		AssignmentFourteen.switchTest(1);
		System.out.println("Switch Case 2");
		AssignmentFourteen.switchTest(2);
		System.out.println("Switch Case 3");
		AssignmentFourteen.switchTest(3);
		System.out.println("Switch Case 4");
		AssignmentFourteen.switchTest(4);
	}

	// Write a program that defines an interface having the following methods:
	// addition, subtraction, multiplication, and division. Create a class that
	// implements this interface and provides appropriate functionality to carry out
	// the required operations. Hard code two operands in a test class having a main
	// method that calls the implementing class
	@Test
	void assignmentFifteen() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		AssignmentFifteenDriver.main(null);
	}

	// Write a program to display the number of characters for a string input. The
	// string should be entered as a command line argument using (String [ ] args).
	@Test
	void assignmentSixteen() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		System.out.println("Inputting argument '12345'");
		AssignmentSixteen.main(new String[] { "12345" });
	}

	// Write a program that calculates the simple interest on the principal, rate of
	// interest and number of years provided by the user. Enter principal, rate and
	// time through the console using the Scanner class.
	@Test
	void assignmentSeventeen() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		AssignmentSeventeen.calculateInterest();
	}

	// Write a program having a concrete ;subclass that inherits three abstract
	// methods from a superclass. Provide the following three implementations in the
	// subclass corresponding to the abstract methods in the superclass:

	// 1. Check for uppercase characters in a string, and return ‘true’ or ‘false’
	// depending if any are found.
	// 2. Convert all of the lower case characters to uppercase in the input string,
	// and return the result.
	// 3. Convert the input string to integer and add 10, output the result to the
	// console.
	// Create an appropriate class having a main method to test the above setup

	@Test
	void assignmentEighteen() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		AssignmentEighteenSub ao18 = new AssignmentEighteenSub();
		ao18.addTen("4");
		assertTrue(ao18.containsUppercase("Aa"));
		assertFalse(ao18.containsUppercase("aa"));
		assertFalse(ao18.containsUppercase(""));
		assertEquals(ao18.convertToUpper("asdf"), "ASDF");
		assertEquals(ao18.convertToUpper("AAA"), "AAA");
	}

	/**
	 * Create an ArrayList and insert integers 1 through 10. Display the ArrayList.
	 * Add all the even numbers up and display the result. Add all the odd numbers
	 * up and display the result. Remove the prime numbers from the ArrayList and
	 * print out the remaining ArrayList.
	 */
	@Test
	void assignmentNineteen() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		AssignmentNineteen.initList();
		System.out.println("Arraylist with integers 1-10:");
		AssignmentNineteen.printList();
		AssignmentNineteen.printOddAddition();
		AssignmentNineteen.removePrimes();
		System.out.println("ArrayList 1-10 with primes removed:");
		AssignmentNineteen.printList();
	}

	// Create a notepad file called Data.txt and enter the following:
	// Mickey:Mouse:35:Arizona
	// Hulk:Hogan:50:Virginia
	// Roger:Rabbit:22:California
	// Wonder:Woman:18:Montana
	//
	// Write a program that would read from the file and print it out to the screen
	// in the following format:
	//
	// Name: Mickey Mouse
	// Age: 35 years
	// State: Arizona State
	@Test
	void assignmentTwenty() {
		System.out.println("\nRunning " + new Object() {
		}.getClass().getEnclosingMethod().getName());

		AssignmentTwenty.main(null);
	}

}
