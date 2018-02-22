package com.revature;

import java.util.ArrayList;

public class Q9 {
	
	public Q9(int num) {
		
		ArrayList<Integer> listOne = new ArrayList<Integer>();
		ArrayList<Integer> listTwo = new ArrayList<Integer>();
		
		listOne.add(1);
		
		for(int i=2; i<=num; i++) {
			
			listOne.add(i);
			boolean isPrime = true;
			
			for(int j=2; j<i; j++) {
				if(i%j == 0) {
					isPrime = false;
				}
			}
			
			if(isPrime) {
				listTwo.add(i);
			}
		}
		
		System.out.println("Q9(number list): " + listOne);
		System.out.println("Q9(prime numbers): " + listTwo);
	}

}
