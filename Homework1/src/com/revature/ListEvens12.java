package com.revature;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListEvens12 {
	
	static List<Integer> storage = new ArrayList<Integer>();	// stores nums from 1 to 100
	static List<Integer> even = new ArrayList<Integer>();	// stores even nums

	public static void listAllNum() {	// shortcut range function used to fill storage list
		
		storage = IntStream.rangeClosed(1, 100).boxed().collect(Collectors.toList());
	}
	
	public static void listEvens(int i) {	// adds even values to even list
		
		while(i < 100) {
			
			if(i % 2 == 0) {
			
				even.add(i);
			}
			
			i++;
		}
	}
	
	public static void printEvens() {	// main function to run, prints even list
		
		listAllNum();
		listEvens(1);
		
		System.out.println(even);
	}
}
