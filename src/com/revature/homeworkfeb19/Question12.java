package com.revature.homeworkfeb19;

//Q12. Write a program to store numbers from 1 to 100 in an array. 
//Print out all the even numbers from the array. 
//Use the enhanced FOR loop for printing out the numbers.



public class Question12 {
	private int arr[]= new int[100];
	{
		for (int i=0; i<100; i++)
		{
			arr[i] = i+1;
		}
	}

	public void print() {
		for (int i : arr)
		{
			if ((i % 2)==0)
				System.out.println(i);
		}
	}
}
