package com.revature.answers;

public class Question5 {

	public static void main(String[] args) {
		
		System.out.println(cutString("abcdefghij", 5));
		
	}

	public static String cutString(String str, int idx) {
		String og = "";
		char[] holder = new char[str.length()];
		
		str.getChars(0, str.length()-1, holder, 0);
	
		for(int i = 0; i < idx; i++) {
			og += holder[i];
		}
		return og;
	}
}
