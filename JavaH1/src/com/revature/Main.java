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
		new Q5("antidisestablishmentarianism", 10);
		
		// Question six - determine if given number is even
		new Q6(30);
		
//		StringBuilder b1 = new StringBuilder("snorkler");
//		StringBuilder b2 = new StringBuilder("yoodler");
//		
//		b1.append(b2.substring(2,5).toUpperCase());
//		b2.insert(3, b1.append("a"));
//		b1.replace(3, 4, b2.substring(4)).append(b2.append(false));
//		
//		System.out.println(b1);
//		System.out.println(b2);
		
//		int i = 1234567890;
//		float f = i;
//		System.out.println(i - (int)f);
		
	}

}