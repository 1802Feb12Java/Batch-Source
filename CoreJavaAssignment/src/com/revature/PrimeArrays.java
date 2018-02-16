package com.revature;

import java.util.ArrayList;

public class PrimeArrays {

	public static void main(String[] args) {
		ArrayList<Integer> allNums = new ArrayList<Integer>();
		for (int i = 1; i < 101; i++) {
			allNums.add(i);
		}//end for
		
		
		ArrayList<Integer> primeNums = new ArrayList<Integer>();
		for(int i : allNums) {
			if(isPrime(i)) {
				primeNums.add(i);
			}
		}//end for
		
		System.out.println(allNums.toString());
		System.out.println(primeNums.toString());
	
	}//end main
	
	public static Boolean isPrime(Integer n) {
		if(n == 2) { return true; }
		
		for(int i = 2; i < n; i++) {
			if(n%i == 0) {
				return false;
			}
		}//end for
		return true;
	}

}//end class
