package com.revature;

public class Q18 extends Q18Superclass {

	/*
	 *Q18. Write a program having a concrete ;subclass that inherits three abstract methods
from a superclass. Provide the following three implementations in the subclass
corresponding to the abstract methods in the superclass:

1. Check for uppercase characters in a string, and return ‘true’ or ‘false’ depending
if any are found.
2. Convert all of the lower case characters to uppercase in the input string, and
return the result.
3. Convert the input string to integer and add 10, output the result to the console.
	 */
	public static void print()
	{
		Q18 q = new Q18();
		System.out.println(q.checkUppercase("hsfhDFif"));
		System.out.println(q.convertToUppercase("dfdfgd"));
		q.convertToInteger("20");
	}
	//function to check for uppercase
	@Override
	Boolean checkUppercase(String str) {
		for(int i = 0 ; i < str.length(); i++)
		{
			
			char strChar = str.charAt(i);
			if(Character.isUpperCase(strChar))
			{
				return true;
			}
		}
		return false;
		
	}

	//convert to uppercase
	@Override
	String convertToUppercase(String str) {
		
		return str.toUpperCase();
	}

	//convert to integer
	@Override
	void convertToInteger(String str) {

		int num =  Integer.parseInt(str);
		System.out.println(num + 10);
	}

}
