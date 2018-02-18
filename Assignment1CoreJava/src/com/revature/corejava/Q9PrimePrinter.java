package com.revature.corejava;

// Question 9: Create an ArrayList which stores numbers from 1 to 100 and prints out all the prime numbers to the console.

public class Q9PrimePrinter {
	public static boolean isPrime(int number) {
		int divisor = 5;			//for checking odd numbers above 5
		
		//negative numbers and the number 1 are not prime
		if(number <= 1) {
			return false;
		}
			
		//2 and 3 are both primes
		else if(number <= 3) {
			return true;
		}
			
		//even numbers above 4 are not prime
		else if((number % 2 == 0) || number % 3 == 0) {
			return false;
		}
			
		//divisors greater than the square root of n are redundant
		while (divisor * divisor <= number) {
			if(number % divisor == 0) {
				return false;
			}
			divisor += 2;
		}
			
		return true;		
	}
}
