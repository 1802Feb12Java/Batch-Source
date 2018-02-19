package com.revature;

import java.util.ArrayList;

public class Q9 {

	public void getPrimes() {
		int num = 100;
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for(int i = 0; i<=num; i++) {
			numbers.add(i);
		}
		for(Integer n : numbers) {
			int counter = 0; 				//this counter is to count the amount of times the remainder=0
			for(int c = n; c>=1; c--) {
				if(n%c == 0) {
					counter += 1;
				}
			}
			if (counter==2) { //if counter =2 then number is only divisible by itself and 1 which makes it prime
				System.out.print(n + " ");
			}
		}
	}
}
