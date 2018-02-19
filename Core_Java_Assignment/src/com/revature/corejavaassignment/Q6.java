package com.revature.corejavaassignment;

public class Q6 {
/*
 * Q6. Write a program to determine if an integer is even without using the modulus operator (%)
 */
	public static void answer() {
		System.out.println("Q6.\tWrite a program to determine if an integer is even without using the modulus operator (%)\n");
		isEven(11);
	}
	/*
	 * this method determines if an integer is even
	 */
	public static void isEven(int integer) {
		System.out.println("Checking if " + integer + " is even or odd\n");
		int i = integer;
		do {
			System.out.println(i);
			if (i==0) {
				System.out.println("The integer "+integer+" is even\n");
			}else if(i==1) {
				System.out.println("The integer "+integer+" is odd\n");
			}
			i-=2;
		}while(i>=0);
	}
}
