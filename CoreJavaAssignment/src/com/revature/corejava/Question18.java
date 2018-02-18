package com.revature.corejava;

abstract class aSuperAbstractClass {
	abstract boolean findUppercase(String string);
	abstract String convertToUppercase(String string);
	abstract int convertStringToInt(String string);
}

public class Question18 extends aSuperAbstractClass {
	Question18() {
		super();
	}
	@Override
	boolean findUppercase(String string) {
		if (string.equals(string.toLowerCase()))
			return false;
		else
			return true;
	}

	@Override
	String convertToUppercase(String string) {
		// TODO Auto-generated method stub
		return string.toUpperCase();
	}

	@Override
	int convertStringToInt(String string) {
		int returnValue = -1;
		try {
			returnValue = Integer.valueOf(string);
			returnValue += 10;
		}
		catch (NumberFormatException e) {
			System.out.println("Sorry, converting string to int failed! -1 Error Code.");
		}
		return returnValue;
		
	}

}
