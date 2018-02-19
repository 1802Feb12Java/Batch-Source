package com.revature;

public class StringIndex {
	
	String subString(String s, int index)
	{
		String returnString = "";
		
	    for(int i=index; i<s.length()-1; i++)
	    {
	    	returnString += s.charAt(i); //beginning at the index char of the string, add that char to a new string
	    }		
		
	    return returnString; //return the new string
	}
	
}
