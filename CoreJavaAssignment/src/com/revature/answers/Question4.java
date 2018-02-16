package com.revature.answers;

public class Question4 {

	public static void main(String[] args) {
		System.out.println(factorial(10));
		System.out.println(factorial(5));
		System.out.println(factorial(7));
	}
	
	public static int factorial(int num) {
		int ans = num;							//placeholder for num so we can mess with it
		for(int i = 1; i < num; i++) {			
			ans = ans*(num - i);
		}
		return ans;
	}
}
