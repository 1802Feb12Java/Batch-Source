package com.revature;


class StringModifier {
	public static String reverse(String str) {
		char[] buffer = new char[str.length()];
		
		// Insert into buffer into reverse order
		for(int i = buffer.length; i-->0;) {
			buffer[buffer.length - 1 - i] = str.charAt(i);
		}
		
		
		return new String(buffer);
	}
}


public class Q3 implements Runnable {
	private String str;
	
	public Q3(String str) {
		this.str = str;
	}
	
	@Override
	public void run() {
		// Printout
		System.out.println("Question 3: String reversal");
		System.out.println("    Input : \"" + str + "\"");
		
		System.out.println("    Output: \"" + StringModifier.reverse(str) + "\"");
		
	}

}
