package com.revature;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class BinaryTriangle {

	//example of printTriangle
	public static void example() {
		// TODO Auto-generated method stub
		
		printTriangle(5);
		
	}
	
	//was somewhat confused on the pattern
	// 0, 1, 1, 0?
	public static void printTriangle(int rows) {
		StringBuilder myString = new StringBuilder();
		
		System.out.println(myString);
		
		//loops through until triangle is constructed
		for(int i = 0; i < rows - 1; i++) {
			
			//i forced the first and second to comply
			if(i == 0) {
				 myString.append(" 0 ");
			}
			else if(i == 1){
				myString.append("1 ");
				myString.reverse();
			}
			//after, they all fall into this else statement where things get alternated
			//I was unsure of what patter to follow though
			else {
				if(i % 2 == 0) {
					myString.append("1 ");
				}
				else {
					myString.append("0 ");
					myString.reverse();
				}
			}
			
			System.out.println(myString);
			
		}
		
	}

}
