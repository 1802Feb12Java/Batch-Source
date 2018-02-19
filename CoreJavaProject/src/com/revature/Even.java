package com.revature;

import java.util.Scanner;

public class Even {

	//example of isEven method
	public static void example() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter a number to see if it is even: ");
		System.out.println(isEven(sc.nextInt()));
		
		sc.close();
	}
	
	//check if number is even without modulus
	public static boolean isEven(int num) {
		int newNum = num / 2;
		newNum = newNum * 2;
		
		//if the newNum is equal to the original, we know for a fact that it is even
		if(newNum == num) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
