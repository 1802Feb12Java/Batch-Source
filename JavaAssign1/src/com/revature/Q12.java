package com.revature;

public class Q12 {
	public void evenArray() {
		int [] newArray = new int[100];
		for(int i = 1; i<=100; i++) {  //regular for loop
			newArray[i-1] = i;
		}
		
		
		for(int n : newArray) { //enhanced for loop
			if(n%2 == 0) {
				System.out.print(n + " ");
			}
		}

	}
}
