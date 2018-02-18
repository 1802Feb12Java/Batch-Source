package com.revature;

import java.util.ArrayList;
import java.util.List;

public class ArrayList19 {

	static List<Integer> a = new ArrayList<Integer>();
	
	private static void makeList() {	// adds ints 1 to 10 to a list
		
		int i = 1;
		
		while(i < 10 || i == 10) {
			
			a.add(i);
			i++;
		}
		
		System.out.println(a);
	}
	
	public static void main(String[] args) {

		System.out.println("Here is the list: ");
		makeList();
				
		System.out.println("We add the even values to get: "+addEven());
		System.out.println("We add the odd values to get: "+addOdd());
		
		System.out.println("After removing the primes, the list becomes: ");
		removePrimes();
		
		System.out.println();
	}



	private static void removePrimes() {	// parses through list a and removes primes encountered
		
		for(Integer i : a) {
			
			for(int j = 2; j < i; j++) {
		        
				 if(i % j == 0) {	// it is composite
			            
			        break;
				 }
				 
				 if(j == i-1) {	// it is prime
					 
					 // a.remove(i-1);
					 System.out.println(a);
					 System.out.println(i);
				 }
			}
		}
		
		System.out.println(a);
	}



	private static int addOdd() {	// parses through list a, adding odd ints encountered

		int sum = 0;
		
		for(Integer i : a) {
			
			if(i % 2 == 1) {
				
				sum = sum + i;
			}
		}
		
		return sum;
	}



	private static int addEven() {	// parses through list a, adding even ints encountered
		
		int sum = 0;
		
		for(Integer i : a) {
			
			if(i % 2 == 0) {
				
				sum = sum + i;
			}
		}
		
		return sum;
	}
}
