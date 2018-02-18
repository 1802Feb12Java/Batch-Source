package com.revature;




public class Q11 implements Runnable {
	private final float FLOAT_A, FLOAT_B;
	
	public Q11(float a, float b) {
		FLOAT_A = a;
		FLOAT_B = b;
	}
	
	@Override
	public void run() {
		System.out.println("Question 11: Access Floats in a Different Package");
		com.revature.q11.Q11 q11 = new com.revature.q11.Q11();
		System.out.println("    Before modifying: " + q11.toString());
		q11.setFloatA(FLOAT_A);
		q11.setFloatB(FLOAT_B);
		System.out.println("    After modifying: " + q11.toString());
	}

}
