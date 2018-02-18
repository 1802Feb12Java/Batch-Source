package com.revature;

import java.util.ArrayList;

public class Primes{
	
	private static ArrayList<Integer> hundred = new ArrayList<Integer>(100);
	
	//fill the list from 1 to 100;
	private static void fillList() {
		for(int i = 1; i <= 100;i++) {
			hundred.add(i);
		}
	}
	
	
	//made public so I can reuse it in Q19
	//return true if n is prime, false otherwise
	public static boolean isPrime(int n) {
		if(n<=1)
			return false;
		else if(n<=3) {
			return true;
		}
		else if(n % 2 == 0 || n % 3 == 0)
			return false;
		int i = 5;
		while(i*i <= n) {
			if(n % i == 0) {
				return false;
			}
			i += 2;
		}
		return true;
	}
	
	//goes through the list, and only prints out primes
	public static void printPrimes() {
		fillList();
		System.out.print("[");
		for(int n:hundred) {
			if(isPrime(n))
				System.out.print(n + " ");
			
		}
		System.out.println("]");
	}
	
}
