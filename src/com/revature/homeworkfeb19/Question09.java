package com.revature.homeworkfeb19;

import java.util.ArrayList;

//Q9. Create an ArrayList which stores numbers from 1 to 100 
//and prints out all the prime numbers to the console.


public class Question09 {
	ArrayList<Integer> ints = new ArrayList<Integer>();
	ArrayList<Integer> primes = new ArrayList<Integer>();
	{
		for (int i = 0; i<100; i++)
		{
			ints.add(i+1);
		}
	}
	
	public void printPrimes() {
		boolean flag= false;
		for (int i : ints)
		{
			flag = true;
			for (int j = 2; 2*j <= i; j++) {
				if (i%j==0) {
					flag = false;
					break;
				}
			}
			if (flag)
				primes.add(i);
		}
		for (int i : primes)
			System.out.println(i);
	}

}