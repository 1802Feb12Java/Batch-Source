package com.revature;

import java.util.ArrayList;

public class QuestionNineteen {

	public static void example() {
		// TODO Auto-generated method stub
		ArrayList<Integer> numbers = new ArrayList<Integer>();
		
		//add numbers to list
		for(int i = 1; i <= 10; i++) {
			numbers.add(i);
		}
		
		System.out.print("Add Evens: ");
		addEven(numbers);
		System.out.print("Add Odds: ");
		addOdd(numbers);
	}
	
	//add all even numbers of the array, and then returns the sum
	public static void addEven(ArrayList<Integer> numbers) {
		int evens = 0;
		
		for(int i: numbers) {
			//checks if number is even, if so, it adds it to the sum

			if(i % 2 == 0) {
				evens += i;
			}
		}
		
		System.out.println(evens);
		
	}
	
	//add all odd numbers of the array, and then returns the sum
	public static void addOdd(ArrayList<Integer> numbers) {
		int odds = 0;
		
		for(int i: numbers) {
			//checks if number is odd, if so, it adds it to the sum
			if(i % 2 != 0) {
				odds += i;
			}
		}
		
		System.out.println(odds);
		
	}
	
	//get rid of prime numbers (1-10)
	public static void ridOfPrimes(ArrayList<Integer> numbers) {
		
		//get rid of all prime numbers (1 - 10)
		for(int i = 0; i < numbers.size(); i++) {
			if(numbers.get(i) % 2 != 0 || numbers.get(i) % 3 != 0) {
				numbers.remove(i);
			}
		}
		
		//print out the numbers
		for(int i = 0; i < numbers.size(); i++) {
			System.out.print(numbers.get(i) + " ");
		}
		
		System.out.println();
	}

}
