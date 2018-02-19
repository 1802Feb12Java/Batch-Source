package com.revature.homeworkfeb19;

import java.util.ArrayList;

//Q8. Write a program that stores the following strings in an 
//ArrayList and saves all the palindromes in another ArrayList.
//“karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, 
//“john”,  “refer”, “billy”, “did”


public class Question08 {
	ArrayList<String> all = new ArrayList<String>();
	ArrayList<String> pali = new ArrayList<String>();
	
	{
		all.add("karan");
		all.add("madam");
		all.add("tom");
		all.add("civic");
		all.add("radar");
		all.add("jimmy");
		all.add("kayak");
		all.add("john");
		all.add("refer");
		all.add("billy");
		all.add("did");
	}
	{
		for (String str : all)
		{
			if(palindrome(str))
			{
				pali.add(str);
			}
		}
		for (String str : pali)
			System.out.println(str);
	}
	
	private boolean palindrome(String s)
	{
		int i1 = 0;
		int i2 = s.length()-1;
		while (i2 > i1)
		{
			if (s.charAt(i1) != s.charAt(i2))
			{
				return false;
			}
			i1++;
			i2--;
		}
		return true;
	}
	
	
	
}
