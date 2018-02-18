package com.revature.corejava;

import java.util.ArrayList;

// Question 9: Create an ArrayList which stores numbers from 1 to 100 and prints out all the prime numbers to the console.

public class Q9PrimePrinter {
	public static void printPrimes(int[] numbers) {
		ArrayList<Integer> primes = new ArrayList<>();
		boolean isPrime = false;
		int divisor = 5;			//for checking primes above 4
		
		for (int index = 0; index < numbers.length; index++) {
			//negative numbers and the number 1 are not prime
			if(numbers[index] <= 1) {
				isPrime = false;
			}
			
			//2 and 3 are both primes
			else if(numbers[index] <= 3) {
				isPrime = true;
			}
			
			//even numbers above 4 are not prime
			else if((numbers[index] % 2 == 0) || (numbers[index] % 3 == 0)) {
				isPrime = false;
			}
			
			//divisors greater than the square root of n are redundant
			while(divisor * divisor <= numbers[index]);{
				if(numbers[index] % divisor == 0) {
					isPrime = false;
				}

				divisor += 2;
			}
			
			divisor = 5;
			
			if(isPrime) {
				primes.add(numbers[index]);
			}
			
			isPrime = false;
			
		}
		
		for(Integer current : primes) {
			System.out.println(current);
		}
	}
}
