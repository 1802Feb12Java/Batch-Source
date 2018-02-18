package com.revature;

//Custom extended class, extending from Custom Abstract
public class CustomExtended extends CustomAbstract{
	
	//definition of checkUpper
	boolean checkUpper(String s) {
		int strLen = s.length();
		for(int i = 0; i < strLen; i++) {
			char tempC = Character.toUpperCase(s.charAt(i));
			if(tempC == s.charAt(i))
				return true;
		}
		return false;
	}
	
	//definition of toUpper
	String toUpper(String s) {
		return s.toUpperCase();
	}
	
	//definition of intPlus10
	void intPlus10(String s) {
		System.out.println(Integer.parseInt(s) + 10);
	}

}
