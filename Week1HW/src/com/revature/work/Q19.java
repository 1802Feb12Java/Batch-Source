package com.revature.work;

import java.util.ArrayList;

public class Q19 {
	public void display (int limit) {
		ArrayList <Integer> all = new ArrayList<Integer>();
		int even = 0;
		int odd = 0;
		// Filling out list from 1 to limit provided 
		for (Integer x = 1; x <= limit; x++) {
			all.add(x);
		}
		System.out.println("All the integers are "+all);
		
		// adding even and odds together 
		for (int y = 1; y <= limit; y++) {
			if( y % 2 == 0) {
				even += y;
			} else {
				odd += y;
			}
		}
		
		System.out.println("All the even Integers added are: "+even);
		System.out.println("All the odd Integers added are: "+odd);
		
		// Resused code from Q9
		Integer counter=0;
		for (Integer x = 2; x <= limit; x++) {	
			for (Integer y = 1; y < x; y++) { 
				if ((x % y) == 0) {
					counter++;
				}
			}
			// removing any primes found from the complete list "all" 
			if (counter <= 1) {
				all.remove(x);
			}
			counter = 0;
		}
		
		System.out.println("Without Primes, it is: "+all);
		
	}
}
