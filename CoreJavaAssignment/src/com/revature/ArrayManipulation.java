package com.revature;

import java.util.ArrayList;
import java.util.Iterator;

//calculate the odd sum and even sum of array from [1 ... 10]
//remove all primes from the array
public class ArrayManipulation {

	private static ArrayList<Integer> intArr = new ArrayList<Integer>(10);
	
	//helper method to print the array outside of class
	public static void print() {
		System.out.println(intArr);
	}
	
	
	//Initializes the array with 1 to 10
	public static void initArr() {
		for(int i = 1; i <= 10; i++) {
			intArr.add(i);
		}
	}
	
	//calculate the odd sum of the array
	public static int oddSum() {
		int sum = 0;
		for(int n: intArr) {
			if(n % 2 == 1)
				sum += n;
		}
		return sum;
	}
	
	//calculate the even sum of the array
	public static int evenSum() {
		int sum = 0;
		for(int n: intArr) {
			if(n % 2 == 0)
				sum += n;
		}
		return sum;
	}
	
	//remove the primes of the array (2, 3, 5, 7)
	public static void removePrime() {
		Iterator<Integer> iter = intArr.iterator();
		while(iter.hasNext()) {
			Integer curI = iter.next();
			
			if(Primes.isPrime(curI)) {
				iter.remove();
			}
		}
	}
}
