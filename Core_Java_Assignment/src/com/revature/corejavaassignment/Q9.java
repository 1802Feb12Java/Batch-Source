package com.revature.corejavaassignment;

import java.util.ArrayList;
import java.util.List;

public class Q9 {
/*
 * Q9. Create an ArrayList which stores numbers from 1 to 100 and prints out all the 
 * prime numbers to the console.
 */
	public static void answer() {
		System.out.println("Q9.\tCreate an ArrayList which stores numbers from 1 to 100 and prints out all the \r\n" + 
				"\tprime numbers to the console.\n");
		List<Integer> nums = new ArrayList<Integer>();
		for (int i=1; i<=100; i++) {
			nums.add(i);
		}
		findPrime(nums);
	}
	/*
	 * This method takes a list of integers and find the prime numbers within it.
	 */
	public static void findPrime(List<Integer> nums) {
		List primes = new ArrayList<Integer>();
		for( Integer i: nums) {
			int divs =0;
			for (int j=1; j<=i; j++) {
				if(i%j ==0) {
					divs++;
					//System.out.println(i + " " + j);
				}
			}
			if(divs<=2) {
				//System.out.println(i);
				primes.add(i);
			}
		}
		System.out.println("The prime numbers between 0 and 100 are: \n" + primes.toString()+"\n");
	}
	
}
