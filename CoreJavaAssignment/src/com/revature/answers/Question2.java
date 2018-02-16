package com.revature.answers;

public class Question2 {
	
	public static void main(String[] args) {
		int first = 0;			//The first number in the sequence
		int second = 1;			//The second number in the sequence
		int var;				//Variable to track the sequence
		 
		System.out.println(first);		//prints the base case
		System.out.println(second);		//prints the base case
		for(int i = 0; i < 23; i++) {
			var = first + second;		//the Fibonacci equation
			System.out.println(var);
			if(first > second) {		
				second = var;
			}else {
				first = var;
			}
		}
	}
	
}
