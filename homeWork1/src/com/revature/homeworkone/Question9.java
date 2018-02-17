package com.revature.homeworkone;

import java.util.ArrayList;

public class Question9 {

	private static ArrayList<Integer> aList= new ArrayList<>();
	
	public static void fillArray() {
		for (int i = 1; i < 101; i++) {
			
			aList.add(i);
		}
	}
	
	public static ArrayList<Integer> answer() {
		
		fillArray();
		ArrayList<Integer> oList = new ArrayList<>();
		
		for (int i : aList) {
			
			if(prime(i)) {
				oList.add(i);
			}
			
		}		
		
		return oList;
	}
	
	public static boolean prime(int num) {
		
		for (int i=2; i<num; i++) {
			if( num % i == 0) {
				return false;
			}
		}
		
		return true;
	}
	
	
}
