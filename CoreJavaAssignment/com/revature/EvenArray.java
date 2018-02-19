package com.revature;

public class EvenArray 
{
	private int[] oneHundred = new int[100]; //define array of length 100
	
	public EvenArray()
	{
		String evenString = "";
		for(int i=1; i < 100; i ++)
		{
			oneHundred[i] = i;
		}
		
		for(int i:oneHundred)
		{
			if(i%2 == 0) //if its evenly divisible by 2, add it to evenString
				evenString += (i + " ");
		}
		System.out.println(evenString);
	}
}
