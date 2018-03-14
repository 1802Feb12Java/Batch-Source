package com.revature;

import java.util.ArrayList;
	/*
	 * The purpose of this class is to create a method to check if 
	 * an integer is prime.
	 */
public class PrimeArrays {

	public static void main(String[] args) {
		
		runPrimeArrays();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/
	}//end main
	
	public static void runPrimeArrays() {
		ArrayList<Integer> allNums = new ArrayList<Integer>();
		for (int i = 1; i < 101; i++) {
			allNums.add(i);
		}
		//adds the integers 1 through 100 to the array list
		
		ArrayList<Integer> primeNums = new ArrayList<Integer>();
		for(int i : allNums) {
			if(isPrime(i)) {
				primeNums.add(i);
			} //if the number is prime, it is added to the prime array list
		}//end for
		
		System.out.println("The array containing 1-100\n" + allNums.toString());
		System.out.println("The sub-array containing only the primes from the original array\n" + primeNums.toString());
	}//end runPrimeArrays method
	
	public static Boolean isPrime(Integer n) {
		//this method checks to see if an integer is prime by checking
		//if any integer smaller than it (other than 1) divides it evenly
		
		//1 and 2 are special cases, so they are handled individually
		if(n == 1) { return false; }
		if(n == 2) { return true; }
		
		for(int i = 2; i < n; i++) {
			if(n%i == 0) {
				return false;
			}
		}//end for
		return true;
	}//end isPrime method

}//end class
