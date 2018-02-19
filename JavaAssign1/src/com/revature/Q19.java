package com.revature;

import java.util.ArrayList;
import java.util.Iterator;

public class Q19 {
	public void playWithNums() {
		ArrayList<Integer> listOfInts = new ArrayList<Integer>();
		for(int i =1; i<= 10; i++) {
			listOfInts.add(i);
		}							//input integers into array then prints
		System.out.println("OG List of Ints: " + listOfInts.toString());
		
		int addEvens = 0;
		for(int i = 0; i<10; i++) {
			if(listOfInts.get(i)%2 == 0) { //adds even numbers
				addEvens += listOfInts.get(i);
			}
		}
		System.out.println("The even numbers added together is " + addEvens);
		
		int addOdds = 0;
		for(int i = 0; i<10; i++) {
			if(listOfInts.get(i)%2 != 0) { //adds only odd numbers together
				addOdds += listOfInts.get(i);
			}
		}
		System.out.println("The odd numbers added together is " + addOdds);
		
		for(Iterator<Integer> iterator = listOfInts.iterator(); iterator.hasNext();) {
			int counter = 0; 				//this counter is to count the amount of times the remainder=0
			Integer ss = iterator.next();
			for(int c = ss; c>=1; c--) {
				if(ss%c == 0) {
					counter += 1;
				}
			}
			if (counter==2) { //if counter =2 then number is only divisible by itself and 1 which makes it prime
				iterator.remove();
			}
		}
		System.out.println("List of Ints w/o primes: " + listOfInts);
		
	}
}
