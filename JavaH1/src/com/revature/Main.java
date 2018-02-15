package com.revature;

public class Main {

	public static void main(String[] args) {
		
		// Question one - bubble sort
		int nums[] = {1,0,5,6,3,2,3,7,9,8,4};
		new Q1(nums);
		
		// Question two - first 25 Fibonacci numbers
		new Q2();
		
		// Question three - reverse a string
		new Q3("supercalifragilisticexpialidocious");
		
		// Question four - calculate factorial
		new Q4(8);
		
		// Question five - substring based on index
		new Q5("antidisestablishmentarianism", 28);
		
		// Question six - determine if given number is even
		new Q6(30);
	}

}