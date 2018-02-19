package com.revature.answers;

import java.util.ArrayList;
import java.util.Arrays;

public class Question8 {

	public static ArrayList<String> forwards = new ArrayList<>();
	public static ArrayList<String> backwards = new ArrayList<>();
	

	
	public static void main(String[] args) {
		
		forwards.add("karan");
		forwards.add("madam");
		forwards.add("tom");
		forwards.add("civic");
		forwards.add("radar");
		forwards.add("jimmy");
		forwards.add("kayak");
		forwards.add("john");
		forwards.add("refer");
		forwards.add("bill");
		forwards.add("did");
		System.out.println(Arrays.toString(forwards.toArray()));
		flipFlop(forwards);
		
	}
	
	public static void flipFlop(ArrayList<String> a) {
		
		for(int i = 0; i < a.size(); i++) {
			StringBuilder sb = new StringBuilder(a.get(i));				//converts strings to builders so we can use
			String temp = sb.reverse().toString();						//reverse
			backwards.add(temp);
		}
		System.out.println(Arrays.toString(backwards.toArray()));
		
	}
	
}
