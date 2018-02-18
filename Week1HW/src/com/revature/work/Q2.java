package com.revature.work;

import java.util.Arrays;

public class Q2 {
	
	int[] bella = new int[24];
	
	public void create (int total) {
		// first 2 indexes are hardcoded for the fibonacci pattern
		bella[0]=0;
		bella[1]=1;
		// starting with 3rd index to the nth index determined by total
		for (int x = 2; x < total - 1;x++) {
			bella[x]=bella[x-1]+bella[x-2];
		}	
	}
	
	public void print() {
		System.out.println(Arrays.toString(bella));
	}
	
}
