package com.revature;

public class Q3 {
	
	public Q3(String str) {
		
		int len = str.length();
		String[] arr = str.split("(?!^)");
		String reversed = "";
		
		for(int i=1; i<=len; i++) {
			reversed += arr[len-i];
		}
		
		System.out.println("Q3: " + reversed);
	}

}
