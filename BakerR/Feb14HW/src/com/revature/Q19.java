package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Q19 implements Runnable {
	private static final String TAB = "    ";
	private final int LOWER_BOUND, UPPER_BOUND;
	
	/**
	 * Set lower and upper bounds of list of integers to
	 * perform operations on.
	 * @param lowerBound Lower bound, inclusive
	 * @param upperBound Upper bound, inclusive
	 */
	public Q19(int lowerBound, int upperBound) {
		LOWER_BOUND = lowerBound;
		UPPER_BOUND = upperBound;
	}
	
	@Override
	public void run() {
		System.out.println("Question 19: Operating on Lists");
		// Fill ArrayList<Integer> with [lower,upper]
		List<Integer> intList = new ArrayList<>();
		for(int i = LOWER_BOUND; i <= UPPER_BOUND; ++i) intList.add(i); 
			
		// Display ArrayList
		System.out.println(TAB + "List: " + intList.toString());
		
		// Add all evens and display
		Integer evenSum = intList.stream().reduce(0, (Integer result, Integer operand) -> {
			return ((operand & 1) == 0) ? result + operand : result;
		});
		System.out.println(TAB + "Sum of Evens: " + evenSum);
		
		// Add all odds and display
		Integer oddSum = intList.stream().reduce(0, (Integer result, Integer operand) -> {
			return ((operand & 1) == 1) ? result + operand : result;
		});
		System.out.println(TAB + "Sum of Odds: " + oddSum);
		
		// Remove all primes and display
		Set<Integer> primeSet = Q9.PrimeGenerator.generatePrimes(LOWER_BOUND, UPPER_BOUND);
		intList.removeAll(primeSet);
		System.out.println(TAB + "List with primes removed: " + intList.toString());
	}

}
