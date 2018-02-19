package com.revature;

import java.util.ArrayList;

public class Q8 {
	
	public Q8(String[] myList) {
		
		ArrayList<String> listOne = new ArrayList<String>();
		ArrayList<String> listTwo = new ArrayList<String>();
		
		for(int i=0; i<myList.length; i++) {
			
			String str = myList[i];
			
			if(isPalindrome(str)) {
				listOne.add(str);
			} else {
				listTwo.add(str);
			}
		}
		
		System.out.println("Q8(palindromes): " + listOne);
		System.out.println("Q8(non-palindromes): " + listTwo);
	}
	
	boolean isPalindrome(String s) {
		
	  int n = s.length();
	  
	  for (int i = 0; i < (n/2); ++i) {
	     if (s.charAt(i) != s.charAt(n - i - 1)) {
	         return false;
	     }
	  }

	  return true;
	}

}
