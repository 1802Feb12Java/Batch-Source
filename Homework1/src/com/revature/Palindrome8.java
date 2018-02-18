package com.revature;

import java.util.ArrayList;

public class Palindrome8 {

	private static ArrayList<String> palindromes = new ArrayList<String>();
	
	public static ArrayList<String> palindromes(ArrayList<String> list ){
		
		int totalComparisons, counter;
		
		for(String s: list){
		
			if(s.length() % 2 == 0){	// in the case of even number of letters in string s
				
				totalComparisons = s.length() / 2;
				counter = 0;
				
				for(int i = 0; i < totalComparisons; i++){
					
					if( s.charAt(i) == s.charAt( s.length() - i - 1) )	// compare half the letters on left to right
					
						counter++;
					
					if( counter == totalComparisons )	// if its symmetric, its a palindrome so its added to the list
						
						palindromes.add(s);
				}
			} else{
				
				totalComparisons = (s.length()- 1) / 2;	// checks symmetry till the middle letter, because of odd length
				counter = 0;
				
				for(int j = 0; j < totalComparisons; j++){
					
					if( s.charAt(j) ==  s.charAt( s.length() - j - 1)  )
						
						counter++;
					
					if( counter == totalComparisons )	// if its symmetric, its a palindrome so its added to the list
						
						palindromes.add(s);	
				}
			}		
		}
	
		return palindromes;
	}
}
