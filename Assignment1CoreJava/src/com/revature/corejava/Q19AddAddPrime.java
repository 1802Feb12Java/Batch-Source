package com.revature.corejava;

import java.util.ArrayList;

/*Question 19: Create an ArrayList and insert integers 1 through 10. Display the ArrayList. 
Add all the even numbers up and display the result. Add all the odd numbers up and display the result. 
Remove the prime numbers from the ArrayList and print out the remaining ArrayList.
*/

public class Q19AddAddPrime {
	public static void addAddPrime() {
		ArrayList<Integer> testArray = new ArrayList<>();
		int sumOdd = 0;
		int sumEven = 0;
		
		//populate the ArrayList
		for (int index = 0; index < 10; index++) {
			testArray.add(index + 1);
		}
		
		//display the ArrayList
		System.out.println("Populated array list:");
		for(int current : testArray) {
			System.out.println(current);
		}
		
		//calculate the odd and even sums
		for(int current : testArray) {
			if (current % 2 == 0) {
				sumEven += current;
			}
			
			else {
				sumOdd += current;
			}
		}

		//remove the primes from the list using the isPrime tester from Q9
		for(int index = 9; index >= 0; index--) {
			if (Q9PrimePrinter.isPrime(testArray.get(index))) {
				testArray.remove(index);
			}
		}
		System.out.println("Sum of even numbers = " + sumEven);
		System.out.println("Sum of odd numbers = " + sumOdd);
		System.out.println("ArrayList with primes removed:");
		
		for(int current : testArray) {
			System.out.println(current);
		}
	}
}
