package com.revature.core.java;

import java.util.ArrayList;

/**
 * 
 * @author Johne Vang
 *	Q9. Create an ArrayList which stores numbers from 1 to 100 
 *	and prints out all the prime numbers to the console.
 */
public class Q9_PrimeNumbers {

	public static void main(String[] args) {
		
		/*
		 * 
		 */
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for(int i = 1 ; i <= 100; i++) {
			intList.add(i);
		}
		
		for(Integer i : intList) {
			if(isPrime(i)) {
				System.out.println(i);
			}
		}
	}//end of main
	
	/**
	 * One is a composite number since it is not greater than one
	 * and does not have two factors. 
	 * 
	 * As long as the factor is <= the sqrt(num), check if num is 
	 * divisble by factor. if factor is greater
	 * than sqrt(num), then it is not a factor of num. 
	 * 
	 * @param num
	 * @return boolean value of isPrime
	 */
	public static boolean isPrime(int num) {
		boolean isPrime = true;
		
		if(num <= 1)
			isPrime = false;
		
		for(int factor = 2; factor <= Math.sqrt(num); factor++) {
			if(num % factor == 0) {
				isPrime = false;
			}
		}
		return isPrime;
	}//end of isPrime
}//end of class
