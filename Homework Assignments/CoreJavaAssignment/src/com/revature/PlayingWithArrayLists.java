package com.revature;

import java.util.ArrayList;
	/*
	 * The purpose of this class is to demonstrate how to use ArrayLists.
	 */
public class PlayingWithArrayLists {

	public static void main(String[] args) {
		
		runPlayingWithArrayLists();
		/* 
		 * This method is calling code that would typically be run in the main method,
		 * but for the sake of the driver I created a separate method for it
		*/

	}//end main

	public static void runPlayingWithArrayLists() {
		ArrayList<Integer> myArrayList = new ArrayList<Integer>();
		for(int i = 1; i < 11; i++) {
			myArrayList.add(i);
		} //add integers 1 through 10 to the array list
		System.out.println("The array list: " + myArrayList.toString()); //display the array list
		System.out.println("The sum of the even numbers is: " + addEven(myArrayList));
		System.out.println("The sum of the odd numbers is: " + addOdd(myArrayList));
		removePrimes(myArrayList); //remove primes from the array list
		System.out.println("The array list without primes is: " + myArrayList.toString());
		//display the array list without primes
	
	}//end runPlayingWithArrayLists method
	
	public static int addEven(ArrayList<Integer> a) {
		//this method makes use of the isEven method in the EvenNumbers class
		int sum = 0;
		for(int i : a) {
			if(EvenNumbers.isEven(i)) {
				sum = sum + i;
			}
		}
		return sum;
	}//end addEven method
	
	public static int addOdd(ArrayList<Integer> a) {
		//this method makes use of the isEven method in the EvenNumbers class
		int sum = 0;
		for(int i : a) {
			if(!EvenNumbers.isEven(i)) {
				sum = sum + i;
			}
		}
		return sum;
	}//end addOdd method
	
	public static void removePrimes(ArrayList<Integer> a) {
		//this method makes use of the isPrime method in the PrimeArrays class
		for(int i = 0; i < a.size(); i++) {
			if(PrimeArrays.isPrime(a.get(i))) {
				a.remove(i);
			}
		}
	}//end removePrimes method
	
	
}//end class
