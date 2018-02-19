package com.revature;

import java.util.ArrayList;

public class Palindrome 
{
	ArrayList<String> arrayl = new ArrayList<String>();
	
	boolean isPalindrome(String chr)
	{
	    int i = 0;
	    int b = chr.length() - 1;
	    while (b > i) //increment i and decrement b while checking the char (could also use a for loop)
	    {
	        if (chr.charAt(i) != chr.charAt(b)) //compare chars at two positions
	        {
	            return false;
	        }
	        ++i;
	        --b;
	    }
	    return true;
	}	
	
	ArrayList<String> checkArrayList(ArrayList<String> al)
	{
		for (int i = 0; i < al.size(); i++)
		{
			String value = al.get(i);
		    if(isPalindrome(value))
		    	arrayl.add(value);		    		
		}
		return arrayl;
	}
	
	void printArrayL()
	{
		String s = "";
	    for(String elem : arrayl){
	        s += (elem + ",  ");
	    }
	    System.out.println(s);
	}
	
	
	
}
