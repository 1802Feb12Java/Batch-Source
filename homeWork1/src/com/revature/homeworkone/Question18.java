package com.revature.homeworkone;

public class Question18 extends Question18Abstract {

	
	public void answer() {
		
		System.out.println("\nQuestion #18");
		System.out.println("True has capital? " + upperCaseCheck("True"));
		System.out.println("AlLToUPPERcase -> " + allUpperCase("AlLToUPPERcase"));
		System.out.println("''15'' is passed in to addTen() = " + addTen("15"));
	}
	
	@Override
	public boolean upperCaseCheck(String str) {
		//String newStr = str.toLowerCase();
		return !str.equals(str.toLowerCase());
		
	}

	@Override
	public String allUpperCase(String str) {
		// TODO Auto-generated method stub
		return str.toUpperCase();
	}

	@Override
	public Integer addTen(String str) {
		// TODO Auto-generated method stub
		return Integer.parseInt(str) + 10;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
