package com.revature.answers;

import java.util.ArrayList;

public class Question9 {

	public static void main(String[] args) {

		prime1to100();
		
	}
	
	public static void prime1to100() {
		ArrayList<Integer> num = new ArrayList<>();
		for(int i = 0; i < 100; i++) {
			num.add((Integer) i+1);
		}
		System.out.println("2");								//setting up the base cases that are hard to catch.
		System.out.println("3");
		System.out.println("5");
		System.out.println("7");
		for(int i = 1; i < num.size(); i++) {
			if((num.get(i).intValue()%2) != 0) {					//all these ifs test the prime values
				if((num.get(i).intValue()%3) != 0) {				//all primes under 100 are not divisible by these.
					if((num.get(i).intValue()%5) != 0) {
						if((num.get(i).intValue()%7) != 0) {
							System.out.println(num.get(i).toString()); //only prints out the primes.
						}
					}
				}
			}
		}
		
	}

}
