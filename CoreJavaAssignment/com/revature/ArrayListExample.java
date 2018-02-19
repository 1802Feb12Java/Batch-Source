package com.revature;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListExample 
{
	ArrayList<Integer> al = new ArrayList<Integer>();
	
	public ArrayListExample() //inserts integers 1-10 upon init
	{
		for(int i=0; i<9; i ++)
		{
			al.add(i);
		}
		System.out.println(Arrays.toString(al.toArray()));
	}
	
	int displayEven()
	{
		int returnInt = 0;
		for(int i=0; i<9; i++)
		{
			int b = al.get(i);
			if(b%2 == 0)
			{
				returnInt += b;
			}
		}
		return returnInt;
	}
	
	int displayOdd()
	{
		int returnInt = 0;
		for(int i=0; i<9; i++)
		{
			int b = al.get(i);
			if(b%2 != 0)
			{
				returnInt += b;
			}
		}
		return returnInt;
	}
	
	void removePrimeNums()
	{
		  boolean isPrime=false;
		  for(int i=0;i<al.size()-1;i++)
		  {
		  isPrime = checkPrime(al.get(i));
		    if(isPrime)
		    {
		        al.remove(i);
		    }
		  }
		  System.out.println(Arrays.toString(al.toArray()));
		}
		  
	boolean checkPrime(int n)
	{
	        for(int i=2;i<n;i++) 
	        {
	            if(n%i==0)
	                return false;
	        }
	        return true;
	}
	
}
