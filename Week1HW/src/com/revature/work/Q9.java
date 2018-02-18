package com.revature.work;

import java.util.ArrayList;

public class Q9 {
	public void isPrime(Integer limit) {
		ArrayList <Integer> primos= new ArrayList<Integer>();
		Integer counter=0;
		for (Integer x = 2; x <= limit; x++) {	//we know 2 is the lowest prime number so we start with it
			for (Integer y = 1; y < x; y++) {
				// anything that can divide the integer x adds to a counter
				if ((x % y) == 0) {
					counter++;
				}
			}
			// prime means it should only be divided by itself and 1, so no more than 2 counters
			if (counter <= 1) {
				primos.add(x);
			}
			counter = 0; //reset counter for next check
		}
		
		System.out.println("Primes from 0 to "+limit+" are :"+primos);
	}
}
