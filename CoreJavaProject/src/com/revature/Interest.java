package com.revature;

public class Interest {

	public static void example() {
		System.out.println("Principal: 1000");
		System.out.println("rate: .08");
		System.out.println("Time: 50");
		System.out.println("Interest:" + simpleInterest(1000, .08f, 50));
	}
	
	public static float simpleInterest(float principal, float rate, float time) {
		return principal * rate * time;
	}
	
}
