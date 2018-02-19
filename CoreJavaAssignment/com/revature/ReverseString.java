package com.revature;

public class ReverseString {
	
	String stringReverse(String s)
	{
		String returnString = "";
		
	    for(int i=s.length()-1; i>=0; i--)
	    {
	    	returnString += s.charAt(i); //beginning at the final char of the string, add that char to a new string
	    }		
		
	    return returnString; //return the new string
	}

}
