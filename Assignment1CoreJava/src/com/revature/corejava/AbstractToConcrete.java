package com.revature.corejava;

public class AbstractToConcrete {
	public boolean uppercaseInString(String string) {
		return !string.equals(string.toLowerCase());
	}
		
	public String toUpper(String string) {
		return string.toUpperCase();
	}
		
	public int toIntPlus10(String string) {
		int converted = Integer.parseInt(string);
		return converted + 10;
	}
}
