package com.revature;

import java.util.ArrayList;

public class Prime {
	
	//example of isPrime function
	public static void example() {
		ArrayList<Integer> numList = new ArrayList<>();
		
		//fill array list up 
		for(int i = 0; i < 100; i++){
			
			numList.add(i + 1);
			
			if(isPrime(i + 1)) {
				System.out.println(numList.get(i));
			}
		}
	}
	
	//check if number entered is prime
	public static boolean isPrime(int number) {
		
		//numbers 1 - 100 that are prime are not divisible by 2, 3, 5, and 7
		//we only needed to check these numbers
		if(number % 2 == 0 || number % 3 == 0) {
			return false;
		}
		else if(number % 5 == 0 || number % 7 == 0) {
			return false;
		}
		
		return true;
		
	}
	
}
