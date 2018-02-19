package com.revature;

public class Q1 {

	//1.Perform a bubble sort on the following integer array: 1,0,5,6,3,2,3,7,9,8,4
	
	public static void bubbleSort(int[] array)
	{
		for (int j = 0 ; j < array.length - 1 ; j++)
		{
			for (int i = 0; i < array.length - j - 1; i++)
			{
				//if index i > index i+1, swap elements
				if(array[i]> array[i + 1])
				{
					//swapping values
					int temp = array[i + 1 ];
					array[i + 1] = array[i];
			     	array[i] = temp; 
				}

			}
		}
		
	}
}
	