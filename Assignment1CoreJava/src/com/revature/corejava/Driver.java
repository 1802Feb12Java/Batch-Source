package com.revature.corejava;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Driver {

	private static String karan;

	public static void main(String[] args) {
/*		//Question 1:  Bubble Sort
		int [] q1Array = {1,0,5,6,3,2,3,7,9,8,4};  //unsorted array
		Q1BubbleSort.bubbleSort(q1Array);
		
		//Question 2:  Fibonacci Sequence
		int numOfElements = 25;
		int [] fibonacciArray = new int[numOfElements];  //the array to hold the sequence		
		fibonacciArray = Q2Fibonacci.generateFibonacciSequence(numOfElements);
		
		//Question 3:  String reversale
		String reversedString = "!em ta kool ,skeeseeM .rM m'I";  //string that will be reversed
		reversedString = Q3ReverseString.reverseString(reversedString);

		//Question 4:  Factorial
		//System.out.println(Q4Factorial.factorial(5));
		
		//Question 5:  Substring
		String deprecatedString = "Get to the (string) choppa!";
		deprecatedString = Q5SubString.deprecateString(deprecatedString, 0);
		
		//Question 6:  Evenness checker
		System.out.println(Q6IntIsEven.intIsEven(11));
		System.out.println(Q6IntIsEven.intIsEven(4444));
		
		//Question 7: Sorting via Comparator
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
		for (String current : palindromes) {
			System.out.println(current);
		}

		//Question 9: Print Primes
		ArrayList<Integer> numbers = new ArrayList<>();
		for (int index = 0; index < 100; index++) {
			numbers.add(index+1);
		}
		
		for (Integer current: numbers) {
			if(Q9PrimePrinter.isPrime(current)) {
				System.out.println(current);
			}
		}

		//Question 10:  Find minimum using ternary operator
		System.out.println(Q10TernaryMinimum.findMinimum(4, 2));

		//Question 11: access floats from another package
		com.revature.corejava.q11a.GimmeDatFloat grabFloats = new com.revature.corejava.q11a.GimmeDatFloat();
		grabFloats.getFloats();
		grabFloats.showReceivedFloats();

		//Question 12:  Print evens with enhanced for loop
		int [] values = new int[99];
		
		for (int index = 0; index < values.length; index++) {
			values[index] = index + 1;
		}
		Q12EnhanceForLoopEven.printEvens(values);

		//Question 13:  Print triangle
		Q13Triangle.printTriangle(4);
*/
		
	}//end main
}
