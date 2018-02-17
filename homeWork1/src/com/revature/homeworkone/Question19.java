package com.revature.homeworkone;

import java.util.ArrayList;

public class Question19 {

	private static ArrayList<Integer> aList;

	public Question19() {
		super();
		fillArray();
	}
	
	private static void fillArray() {
		for (int i = 1; i <= 10; i++) {
			aList.add(i);
		}
	}
	
	public static void answer() {
		aList = new ArrayList<Integer>();
		fillArray();
		System.out.println("\nQuestion #19");
		System.out.println(aList.toString());
		System.out.println(addEvens());
		System.out.println(addOdds());
		removePrimes();
		System.out.println(aList.toString());
	}
	
	private static int addEvens() {
		int sum = 0; 
		for (int i : aList) {
			if(i%2 == 0) {
				sum += i;
			}
		}
		return sum;
	}
	
	private static int addOdds() {
		int sum = 0; 
		for (int i : aList) {
			if(i%2 == 1) {
				sum += i;
			}
		}
		return sum;
	}
	
	private static boolean removePrimes() {
		Question9 q9 = new Question9();
		ArrayList<Integer> primes = q9.answer();
		
		
		return aList.removeAll(primes);
		
	}
	
}
