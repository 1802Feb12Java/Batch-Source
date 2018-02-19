package com.revature.answers;

import java.util.ArrayList;

public class Question19 {

	public static void main(String[] args) {

		whatToDo();
		
	}

	public static void whatToDo() {
		ArrayList<Integer> ones = new ArrayList<>();
		for(int i = 0; i < 10; i++) {					//Initializing the arrlist and adding the values
			ones.add(i+1);
		}
		System.out.println(ones);
		int holderEven = 0;								//count for even
		int holderOdd = 0;								//count for odd
		for(int i = 0; i < ones.size(); i++) {
			if(ones.get(i)%2 == 0) {					//checks if even and adds to holderEven
				holderEven += ones.get(i);
			}else {										//if not even then adds to holderOdd
				holderOdd += ones.get(i);
			}
		}
		System.out.println(holderEven);
		System.out.println(holderOdd);
		ones.remove(1);									//removing the prime values
		ones.remove(1);
		ones.remove(2);
		ones.remove(3);
		System.out.println(ones);
	}
	
}
