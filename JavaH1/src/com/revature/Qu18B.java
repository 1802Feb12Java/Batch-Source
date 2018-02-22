package com.revature;

public class Qu18B extends Qu18A {

	@Override
	public void checkUppercase(String str) {
		
		String[] strSplit = str.split("");
		boolean uppercase = false;
		
		for(int i=0; i<strSplit.length; i++) {
			if(Character.isUpperCase(str.charAt(i))) {
				uppercase = true;
				break;
			}
		}
		
		System.out.println("Q18: Any uppercase letters in string? " + uppercase);
	}

	@Override
	public void toUppercase(String str) {
		
		System.out.println("Q18: Converted string to all uppercase letters: " + str.toUpperCase());
		
	}

	@Override
	public void toInt(String str) {
		
		int result = str.length() + 10;
		System.out.println("Q18: Convert string to int and add ten: " + result);
		
	}
	
	

}
