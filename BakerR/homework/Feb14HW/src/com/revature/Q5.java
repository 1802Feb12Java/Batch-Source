package com.revature;

class StringModifier2 {
	public static String substring(String str, int idx) {
		char[] buffer = new char[idx];
		
		for(int i = idx; i-->0;) {
			buffer[i] = str.charAt(i);
		}
		
		
		return new String(buffer);
	}
}

public class Q5 implements Runnable {
	private final String STR;
	private final int IDX;
	
	public Q5(String str, int end) {
		STR = str;
		IDX = end;
	}
	
	@Override
	public void run() {
		System.out.println("Question 5: Substring Generation");
		
		System.out.println("    String: \"" + STR + "\"; Range: [0," + IDX + ")");
		System.out.println("    Substring: \"" + StringModifier2.substring(STR, IDX) + "\"");
	}

}
