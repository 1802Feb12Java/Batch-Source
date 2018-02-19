package com.revature;

import java.util.ArrayList;
import java.math.*;

public class Q9 {
	//9.Create an ArrayList which stores numbers from 1 to 100 and prints out all the prime
	// numbers to the console.
	//function use to calculate prim numbers
	public static boolean isPrime(int num)
	       {
	           if(num <= 3)
	               return true;

	           for(int i=2;i<=Math.sqrt(num);i++)
	           {
	               if(num%i==0)
	                   return false;
	           }

	           return true;
	       }
	public static void primeArrayList()
	{
		ArrayList<Integer> intList = new ArrayList<>();
		for (int i = 1; i <= 100; i++)
		{
			intList.add(i); //initialize arraylist from 1 to 100
		}  
		for(int i = 0; i < 100; i++)//loop to print out prime number
		{
			if(intList.indexOf(i) == 2)
			{//print out 2 since 2 is prime
				System.out.print(intList.indexOf(i) + " ");
			}
			else if(i > 2 && isPrime(intList.indexOf(i))) //prints out other prime numbers
			{
				System.out.print(intList.indexOf(i) + " ");				

			}
		}
	}
}
