package com.revature;

public abstract class AbstractClass 
{
	boolean checkUppercase(String s)
	{
		for(int i=0; i < s.length()-1; i++)
		{
			if(Character.isUpperCase(s.charAt(i)))
				return true;
		}
		return false;
	}
	
	String convertUppercase(String s)
	{
		String newString = s.toUpperCase();
		return newString;
	}
	
	int parseInt(String s)
	{
		int result = Integer.parseInt(s);
		result += 10;
		return result;
	}
}
