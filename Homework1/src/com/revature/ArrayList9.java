package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ArrayList9 {

	static List<Integer> storage = new ArrayList<Integer>();	// holds all values 1 to 100
	static List<Integer> primes = new ArrayList<Integer>();		// holds primes
	
	public static void storeAllNum(int i) {		// fills list storage with shortcut range function
		
		storage = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
	}
	
	public static void findPrimes(int i) {	// adds values to the primes list
		
		while(i < 100) {
		
			for(int j = 2; j < i; j++) {
			        
				 if(i % j == 0) {	// it is composite because it has a divisor
			            
			        break;
				 }
				 
				 if(j == i-1) {
					 
					 primes.add(storage.get(i-1));	// it is prime if no divisor between 1 and itself is found
				 }
			}
	
			i++;
		}
	}
	
	public static void printPrimes() {	// main method run, prints out the prime list
		
		storeAllNum(1);
		findPrimes(0);
		
		System.out.println(primes);
	}
}
