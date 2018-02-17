package com.revature.homeworkone;

import java.util.ArrayList;

public class Question8 {

	private static ArrayList<String> aList = new ArrayList<>();
	
	
	public static ArrayList<String> q8Answer(){
		
		aList.add("karan");
		aList.add("madam");
		aList.add("tom");
		aList.add("civic");
		aList.add("radar");
		aList.add("jimmy");
		aList.add("john");
		aList.add("refer");
		aList.add("billy");
		aList.add("did");
		ArrayList<String> oList = new ArrayList<>();
		
		for (String str : aList) {
			char[] aStr = str.toCharArray();
			
			if(findPalin(aStr)) {
				oList.add(str);
			}
		}
		return oList;
	}
	
	private static boolean findPalin(char[] aStr) {
		int aLen = aStr.length;
		for (int i = 0; i < aLen; i++) {
			
			if (aStr[i] != aStr[aLen - 1 - i]) {
				return false;
			}		
		}
		return true;
	}
	
}
