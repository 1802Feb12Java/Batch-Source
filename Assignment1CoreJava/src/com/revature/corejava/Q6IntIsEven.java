package com.revature.corejava;

//Question 6: Write a program to determine if an integer is even without using the modulus operator (%)

public class Q6IntIsEven {
	public static boolean intIsEven(int number) {
		int quotient = number / 2;  //divide the number by two

		//if multiplying the quotient by 2 produces the same value as the original number, it is even
		if ((2 * quotient) == number) {
			return true;
		}
		
		//otherwise, the value returned will be a int representing a deprecated double value
		else {
			return false;
		}		 
	}
}
