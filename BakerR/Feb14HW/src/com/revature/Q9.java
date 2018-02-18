package com.revature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class PrimeGenerator {
	public static Set<Integer> generatePrimes(int lowerBounds, int upperBounds) {
		// Bounds: [lowerBounds, upperBounds]
		
		// Fill the set with all numbers within the range.
		Set<Integer> primeSet = new HashSet<>();
		for(int i = 2; i <= upperBounds; ++i) {
			primeSet.add(i);
		}
		
		// Sieve of Eratosthenes
		// Only need to check for multiples up until sqrt(upperBounds)
		final int UPPER_CHECKING_BOUNDS = (int)Math.ceil(Math.sqrt(upperBounds));
		for(int i = 2; i <= UPPER_CHECKING_BOUNDS; ++i) {
			// i is prime if it is found in the set when iterated to it.
			if(primeSet.contains(i)) {
				// Remove multiples from the set (starting with the square of the prime)
				for(int multiple = i*i; multiple <= upperBounds; multiple += i) {
					primeSet.remove(multiple);
				}
			}
		}
		
		// Truncate everything less than lowerBounds from the set.
		primeSet.removeIf((Integer i) -> (i < lowerBounds));
		
		return primeSet;
	}
}


public class Q9 implements Runnable {
	private final int LOWER_BOUND;
	private final int UPPER_BOUND;
	
	public Q9(int lowerBound, int upperBound) {
		LOWER_BOUND = lowerBound;
		UPPER_BOUND = upperBound;
	}
	
	@Override
	public void run() {
		System.out.println("Question 9: Finding Primes");
		System.out.println("    Finding primes in range [" + LOWER_BOUND 
							+ ", " + UPPER_BOUND + "]");
		
		// Store the numbers within the bounds in the list.
		List<Integer> boundList = new ArrayList<>();
		for(int i = LOWER_BOUND; i <= UPPER_BOUND; ++i)
			boundList.add(i);
		
		// Create the set of primes
		Set<Integer> primeSet = PrimeGenerator.generatePrimes(LOWER_BOUND, UPPER_BOUND);
		
		// Keep values that are in the prime set.
		boundList.retainAll(primeSet);
		
		System.out.println("    Primes: " + boundList.toString());
	}

}
