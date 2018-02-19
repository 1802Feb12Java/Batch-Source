package com.revature.homeworkfeb19;

//Q19. Create an ArrayList and insert integers 1 through 10. 
//Display the ArrayList. Add all the even numbers up and display the result. 
//Add all the odd numbers up and display the result. 
//Remove the prime numbers from the ArrayList and print out the remaining 
//	ArrayList.
import java.util.ArrayList;


public class Question19 {
	
	private ArrayList<Integer> arr = new ArrayList<Integer>();
	private ArrayList<Integer> nonps = new ArrayList<Integer>();
	{
		for (int i = 0; i<10; i++)
			arr.add(i+1);			
	}
	
	public void display()
	{
		for (int i : arr)
			System.out.println(i);
	}
	public int addEvens()
	{
		int sum =0;
		for (int i :arr)
			if (i%2 == 0)
				sum += i;
		return sum;
	}
	public int addOdds()
	{
		int sum =0;
		for (int i :arr)
			if (i%2 != 0)
				sum += i;
		return sum;
	}
	public void printNonPrimes()
	{
		boolean flag= false;
		for (int i : arr)
		{
			flag = true;
			for (int j = 2; 2*j <= i; j++) {
				if (i%j==0) {
					flag = false;
					break;
				}
			}
			if (!flag)
				nonps.add(i);
		}
		for (int i : nonps)
			System.out.println(i);
	}
}

