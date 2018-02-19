package com.revature.homeworkfeb19;

import java.util.Arrays;

//Q1. Perform a bubble sort on the following integer array:  1,0,5,6,3,2,3,7,9,8,4
public class Question01 {
	private int arr[];
	
	public Question01()//noArgs constructor sets arr = {0}
	{
		this.arr[0] = 0;
	}

	public Question01(int arr[])//sets this.arr = passed array
	{
		this.arr = arr;
	}
	
	public void bubbleSort()
	{
		boolean sorted = false;
		while (!sorted)			//while not sorted, run the sorting loops
		{
			for (int i=0; i<this.arr.length-1; i++)	//for entire array
			{
				if ( this.arr[i]>this.arr[i+1] )		//if element > elemnt to the right, swap them. 
				{
					this.swap(i, i+1);
				}
			}
			for (int i=(this.arr.length - 1); i>0; i--)	//for entire array
			{
				if (this.arr[i]>=this.arr[i-1])		//if sorted
				{
					sorted = true;				//set sorted
				}
				if (this.arr[i]<this.arr[i-1])
				{
					sorted = false;
					break;
				}
			}
			
		}
		
		
		
		
		
		
		
	}
	
	public void swap(int i1, int i2)//swaps the values @ index i1, and i2 in this.arr
	{
		int temp = this.arr[i1];
		this.arr[i1] = this.arr[i2];
		this.arr[i2] = temp;
	}

	
	@Override
	public String toString() {
		return "Question01 [arr=" + Arrays.toString(arr) + "]";
	}
	
	
	
}
