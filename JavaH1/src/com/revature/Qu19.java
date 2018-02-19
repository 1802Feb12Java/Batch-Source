package com.revature;

import java.util.ArrayList;

public class Qu19 {
	
	public static ArrayList<Integer> intArray = new ArrayList<Integer>();
	
	public Qu19(int num) {
		
		// Insert numbers 1 through num into the intArray ArrayList
		for(int i=1; i<=num; i++) {
			intArray.add(i);
		}
		System.out.println("Q19(Array List - All): " + intArray);
		
		// Add even numbers and odd numbers and display the results separately
		int addEvenNumbers = 0;
		int addOddNumbers = 0;
		
		// iterate through array with for each loop
		for(int j : intArray) {
			if(j%2 == 0) {
				addEvenNumbers += j;
			} else {
				addOddNumbers += j;
			}
		}
		System.out.println("Q19(Sum of even numbers): " + addEvenNumbers);
		System.out.println("Q19(Sum of odd numbers): " + addOddNumbers);
		
		// Remove prime numbers from array
		int len = intArray.size();
		for(int x=0; x<len; x++) {
			
			int num2 = intArray.get(x);
			boolean isPrime = true;
			
			for(int y=2; y<num2; y++) {
				if(num2%y == 0) {
					isPrime = false;
				}
			}
			
			if(isPrime) {
				intArray.remove(x);
				x = x-1;
			}
			len=intArray.size();
			
		}
		
		System.out.println("Q19(Array without prime numbers): " + intArray);
	}
	
	
	

}
