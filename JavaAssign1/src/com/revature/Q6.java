package com.revature;

public class Q6 {

	public void isEven(int n) {
		int temp = n/2;
		if(temp*2 == n) {	//this works only because we are using ints. data is lost so OG number not returned
			System.out.println(n + " is even!"); 
		} else {
			System.out.println(n + " is not even...");
		}
	}
}
