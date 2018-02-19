package com.revature;

public class Q6 {

	//6. Write a program to determine if an integer is even without using the modulus
	//operator (%)
	public static void checkEven(int num)
	{
		//if the divided number equals to the original number after multiplication, 
		// that means the number doesn't have a remainder, hence even
		//
		if((num/2)*2 == num) 
		{
			System.out.println("Number is even.");
		}
		else 
			System.out.println("Number is odd.");

	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
