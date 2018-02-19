package com.revature;

import java.util.ArrayList;

public class Qu12 {
	
	public Qu12(int num) {
		
		ArrayList<Integer> listOne = new ArrayList<Integer>();
		ArrayList<Integer> listTwo = new ArrayList<Integer>();
		
		for(int i=1; i<=num; i++) {
			listOne.add(i);
		}
		
		for(int j : listOne) {
			if(j%2 == 0) {
				listTwo.add(j);
			}
		}
		
		System.out.println("Q12(all numbers): " + listOne);
		System.out.println("Q12(even numbers): " + listTwo);
	}

}
