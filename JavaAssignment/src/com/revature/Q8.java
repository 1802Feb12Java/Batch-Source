package com.revature;

import java.util.ArrayList;

public class Q8 {
	//8. Write a program that stores the following strings in an ArrayList and saves all the
	//palindromes in another ArrayList. “karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, “john”, “refer”, “billy”, “did”
	
	public static void arrayListPalindromes()
	{
		//create arrayList to store string
		ArrayList<String> names = new ArrayList<> (11);
		//initializing ArrayList
		names.add("karan");
		names.add("madam");
		names.add("tom");
		names.add("civic");
		names.add("radar");
		names.add("jimmy");
		names.add("kayak");
		names.add("john");
		names.add("refer");
		names.add("billy");
		names.add("did");
		ArrayList<String> palindromeName = new ArrayList<>();
		for(String str : names)//add names to palindrome name to the new list
		{
			boolean check = checkPalindromes(str);
			if(check)// if check returns true then add name string
				palindromeName.add(str);
		}
		for(String str :palindromeName )//print palindromeName
		{
			System.out.println(str);
		}
				
	}
	public static boolean checkPalindromes(String str)
	{
		//compare first and last char in string and then increment/decrement
		for(int i = 0, j = str.length() - 1; i < str.length()/2; i++, j--)
		{
			if(str.charAt(i) != str.charAt(j)) {
				return false; //if characters don't match return false
			}
		}
		return true;
	}
}
