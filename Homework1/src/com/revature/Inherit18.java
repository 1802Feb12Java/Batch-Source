package com.revature;

public class Inherit18 extends AbstractClassFor18{

	@Override
	public boolean checkUpper(String word) {
		
		char[] string = word.toCharArray();
		int i = 0;
		
		while(i < string.length) {		// ensures we do not gho out of bounds
			
			if(Character.isUpperCase(string[i])) {
				
				return true;		// return true if uppercase char is found
			}
			
			i++;
		}
		
		return false;
	}

	@Override
	public String toUpper(String word) {	
		
		String res = word.toUpperCase();	// built in function allows conversion of string to all caps
		return res;
	}

	@Override
	public void toIntPlus(String word) {
		
		int hold = Integer.parseInt(word);	// built in function converts string to int
		int res = hold+10;
		System.out.println("Your integer "+ hold + " + 10 + is: " + res);
	}
}
