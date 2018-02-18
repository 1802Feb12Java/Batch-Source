package com.revature;

//Q8. Write a program that stores the following strings 
//	  in an ArrayList and saves all the palindromes in 
//	  another ArrayList.
//“karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, “john”,  “refer”, “billy”, “did”

import java.util.ArrayList;

public class PalindromesArray {
	
	private static ArrayList<String> strList = new ArrayList<String>(11);
	private static ArrayList<String> isPalindrome = new ArrayList<String>(11);
	
	//Initializes the list 
	public static void readList() {
		strList.add("karan");
		strList.add("madam");
		strList.add("tom");
		strList.add("civic");
		strList.add("radar");
		strList.add("jimmy");
		strList.add("kavak");
		strList.add("john");
		strList.add("refer");
		strList.add("billy");
		strList.add("did");
		System.out.println("Og List: " + strList);
	}
	
	//separating palindromes to another list
	public static void sepPalin() {
		boolean palin = true;
		for(String curStr: strList) {
			int curLen = curStr.length();
			//checking if a string is a palindrome
			for(int i = 0, j = curLen -1; i < curLen/2; i++,j-- ) {
				if(curStr.charAt(i) != curStr.charAt(j)) {
					palin = false;
					break;
				}
			}
			if(palin) {
				isPalindrome.add(curStr);
			}
			palin = true;
		}
		//printing the palin list
		System.out.println("Palindrome List: " + isPalindrome);
		
	}
}
