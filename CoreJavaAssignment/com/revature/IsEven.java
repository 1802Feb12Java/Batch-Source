package com.revature;

public class IsEven 
{
	
	String evenCheck(int i)
	{
		if ( (i & 1) == 0 )
		{
			return (i + " is even");
		}
		
		else
			
		{
			return (i + " is odd");
		}
	}
	
}
