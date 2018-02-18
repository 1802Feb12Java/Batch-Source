package com.revature.corejava;

/*Question 5: Write a substring method that accepts a string str and an integer idx and 
returns the substring contained between 0 and idx-1 inclusive.  Do NOT use 
any of the existing substring methods in the String, StringBuilder, or StringBuffer APIs.*/

public class Q5SubString {
	public static String deprecateString(String str, int idx) {
		String tempString = "";		//string which will hold the deprecated value
		int index = 0;				//loop counter
		
		//in order to make zero an inclusive index, the loop must run at least one time, 
		//thus a do-while loop is used to ensure this behavior
		do{
			//add the character to the temporary string and iterate the index
			tempString += str.charAt(index);  
			index++;
		}while(index < idx);

		//return the deprecated string
		return tempString;
	}
}
