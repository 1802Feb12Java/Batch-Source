package com.revature.corejava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Driver {
	public static void main(String[] args) {
		//Question 1:  Bubble Sort
		System.out.println("Question 1:");
		int [] q1Array = {1,0,5,6,3,2,3,7,9,8,4};  //unsorted array
		System.out.println("Unsorted Array:");
		for (int index = 0; index < q1Array.length; index++) {
			System.out.println(q1Array[index]);
		}
		q1Array = Q1BubbleSort.bubbleSort(q1Array);
		
		System.out.println("Sorted Array:");
		for (int index = 0; index < q1Array.length; index++) {
			System.out.println(q1Array[index]);
		}
		
		//Question 2:  Fibonacci Sequence
		System.out.println();
		System.out.println("Question 2:");
		System.out.println("First 25 Fibonacci numbers:");
		int numOfElements = 25;
		int [] fibonacciArray = new int[numOfElements];  //the array to hold the sequence		
		fibonacciArray = Q2Fibonacci.generateFibonacciSequence(numOfElements);
		for (int index = 0; index < fibonacciArray.length; index++) {
			System.out.println(fibonacciArray[index]);
		}
		
		//Question 3:  String reversal
		System.out.println();
		System.out.println("Question 3:");
		
		String reversedString = "!em ta kool ,skeeseeM .rM m'I";  //string that will be reversed
		System.out.println("Initial string: " + reversedString);
		reversedString = Q3ReverseString.reverseString(reversedString);
		System.out.println("Reversed string: " + reversedString);
		
		//Question 4:  Factorial
		System.out.println();
		System.out.println("Question 4:");
		System.out.println("5! = " + Q4Factorial.factorial(5));
		
		//Question 5:  Substring
		System.out.println();
		System.out.println("Question 5:");
		String deprecatedString = "Get to the (string) choppa!";
		System.out.println("String: " + deprecatedString);
		deprecatedString = Q5SubString.deprecateString(deprecatedString, 0);
		System.out.println("Deprecated string: " + deprecatedString);	
		
		//Question 6:  Evenness checker
		System.out.println();
		System.out.println("Question 6:");
		System.out.println("Is 11 even? " + Q6IntIsEven.intIsEven(11));
		System.out.println("Is 4444 even? " + Q6IntIsEven.intIsEven(4444));
		
		//Question 7: Sorting via Comparator
		System.out.println();
		System.out.println("Question 7:");
		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		Employee employee1 = new Employee("Marty McFly", "Time Travel", 30);
		Employee employee2 = new Employee("Biff Tannen", "JerkShop", 18);
		Employee employee3 = new Employee("Biff Tannen", "JerkShop", 55);
		
		employeeList.add(employee1);
		employeeList.add(employee2);
		employeeList.add(employee3);
		
		System.out.println("Unsorted list: ");
		for(Employee current : employeeList) {
			System.out.println(current.toString());
		}
		
		Collections.sort(employeeList, new Q7SortEmployees());
		
		System.out.println("Sorted list: ");
		for(Employee current : employeeList) {
			System.out.println(current.toString());
		}
		
		//Question 8: Storing palindromes
		System.out.println();
		System.out.println("Question 8:");
		ArrayList<String> normal = new ArrayList<>();
		ArrayList<String> palindromes = new ArrayList<>();
		
		normal.add(new String("karan")); 
		normal.add(new String("madam"));
		normal.add(new String("tom"));
		normal.add(new String("civic"));
		normal.add(new String("radar"));
		normal.add(new String("jimmy"));
		normal.add(new String("kayak"));
		normal.add(new String("john"));
		normal.add(new String("refer"));
		normal.add(new String("billy"));
		normal.add(new String("did"));
		
		palindromes = Q8Palindrome.creaePalindromeArray(normal);
		System.out.println("Palindromes:");
		for (String current : palindromes) {
			System.out.println(current);
		}

		//Question 9: Print Primes
		System.out.println();
		System.out.println("Question 9:");
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int index = 0; index < 100; index++) {
			numbers.add(index+1);
		}
		
		System.out.println("Primes from 1-100:");
		for (Integer current : numbers) {
			if(Q9PrimePrinter.isPrime(current)) {
				System.out.println(current);
			}
		}

		//Question 10:  Find minimum using ternary operator
		System.out.println();
		System.out.println("Question 10:");
		System.out.println("The minimum of (2,4) is " + Q10TernaryMinimum.findMinimum(4, 2));

		//Question 11: access floats from another package
		System.out.println();
		System.out.println("Question 11:");
		System.out.println("Floats from package 11b:");
		com.revature.corejava.q11a.GimmeDatFloat grabFloats = new com.revature.corejava.q11a.GimmeDatFloat();
		grabFloats.getFloats();
		grabFloats.showReceivedFloats();

		//Question 12:  Print evens with enhanced for loop
		System.out.println();
		System.out.println("Question 12:");
		int [] values = new int[99];
		
		for (int index = 0; index < values.length; index++) {
			values[index] = index + 1;
		}
		System.out.println("List of evens:");
		Q12EnhanceForLoopEven.printEvens(values);

		//Question 13:  Print triangle
		System.out.println();
		System.out.println("Question 13:");
		Q13Triangle.printTriangle(4);

		//Question 14:  Demonstrate case switching
		System.out.println();
		System.out.println("Question 14:");
		Q14CaseSwitch.switchUp(1);
		Q14CaseSwitch.switchUp(2);
		Q14CaseSwitch.switchUp(3);

		//Question 15:  Implement an interface
		System.out.println();
		System.out.println("Question 15:");
		ShutYourInterface interfaceTest = new ShutYourInterface();
		System.out.println("Adding 2 and 4: " + interfaceTest.Add(2, 4));
		System.out.println("Subtracting 2 and 4: " + interfaceTest.Subtract(2, 4));
		System.out.println("Multiplying 2 and 4: " + interfaceTest.Multiply(2, 4));
		System.out.println("Dividing 2 and 4: " + interfaceTest.Divide(2, 4));
		
		//Question 16:  Character counter
		System.out.println();
		System.out.println("Question 16:");
		int numOfCharacters = Q16CharacterCounter.countChars(args);
		System.out.println("There are " + numOfCharacters + " characters in the string \"How many characters?\"");

		//Question 17:  Interest calculator
		System.out.println();
		System.out.println("Question 17:");
		Q17CalculateInterest.payMe();

		//Question 18: Abstract to concrete
		System.out.println();
		System.out.println("Question 18:");
		AbstractToConcrete inheritedClass = new AbstractToConcrete();
		String testString = new String("giggity");
		System.out.println("Upper case in string? " + inheritedClass.uppercaseInString(testString));
		System.out.println("Converting to upper case");
		testString = inheritedClass.toUpper(testString);
		System.out.println("Upper case in string now? " + inheritedClass.uppercaseInString(testString));
		inheritedClass.uppercaseInString(testString);
		System.out.println("Converting string \"4\" to int and adding 10.");
		System.out.println(inheritedClass.toIntPlus10("4"));

		//Question 19: Add odd, even, and remove primes
		System.out.println();
		System.out.println("Question 19:");
		Q19AddAddPrime.addAddPrime();

		//Question 20:  Read from file and print to screen
		System.out.println();
		System.out.println("Question 20:");
		Q20FileReader.readToScreen();
	}//end main
}
