package com.revature.work;

import java.util.ArrayList;

public class Q12 {
	public void findEven (int x) {
		// creating arraylists for all integers and even integers
		ArrayList<Integer> all = new ArrayList<Integer>();
		ArrayList<Integer> even = new ArrayList<Integer>();
		// filling up the all list
		for (Integer a = 1; a <= x ; a++) {
			all.add(a);
		}
		
		// using for each to fill up even list
		for (Integer i: all) {
				if(i%2 == 0) {
				even.add(i);
				}
		}
		System.out.println(even);
	}
}
