package com.revature;

public class Q5 {
	
	public Q5(String str, int index) {
		
		int len = str.length();
		String[] arr = str.split("");
		String substr = "";
		
		if(index > len) {
			System.out.println("Q5: " + "Index must not exceed " + len + " for the given string.");
		} else {
			for(int i=0; i<index; i++) {
				substr += arr[i];
			}
			System.out.println("Q5: " + substr);
		}
	}

}
