package com.revature;

import java.util.ArrayList;

public class Q9 {
	public static void run() {
		ArrayList<Integer> intList = new ArrayList<Integer>();
		for(int i=1; i<=100; i++) {
			intList.add(i);		//adding 1-100 to the list
		}

		System.out.print("All primes from 0-100: ");
		findAndPrintAllPrimes(intList);
		System.out.println();	//formatting
	}
	
	public static void findAndPrintAllPrimes(ArrayList<Integer> list) {
		boolean prime;	//like Q8, assumed true unless something doesn't match up
		for(Integer num : list) {
			prime = true;	//reset the prime boolean to assume it would be
			for(int i=2; i<num/2; i++) {	//check up to the number divided by 2
				if(num%i==0) {	//try to divide by every value up to half the number, if it divides evenly...
					prime=false;	//set boolean to false
				}
			}
			if(prime) {	//if the number gets through that check without changing the boolean...
				System.out.print(num + " ");	//print it
			}
		}
	}
}
