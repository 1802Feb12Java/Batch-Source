package com.revature;

import java.util.ArrayList;

public class PrimeNumbers 
{
	private ArrayList<Integer> primeArrayList = new ArrayList<Integer>();
	
	public PrimeNumbers()
	{
		for(int i=1; i<100; i ++)
			primeArrayList.add(i);
	}
	
	void printPrimeNumbers()
	{
		String primeString = "";
		for(int i: primeArrayList)
		{
			if((i % 2) != 0)
			{
				if(i==1)
					primeString += (2 + " ");
				else if(i != 99)
					primeString += (i + " ");
			}
		}
		System.out.println(primeString);
	}
	
}
