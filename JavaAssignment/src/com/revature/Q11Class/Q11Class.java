package com.revature.Q11Class;

public class Q11Class {

	private static float firstNum;
	private static float secondNum;
	public static float getFirstNum() {
		return firstNum;
	}
	public static float getSecondNum() {
		return secondNum;
	}
	public Q11Class(float firstNum, float secondNum) {
		super();
		Q11Class.firstNum = firstNum;
		Q11Class.secondNum = secondNum;
	}
	@Override
	public String toString() {
		return "Q11Class [firstNum=" + firstNum + ", secondNum=" + secondNum + "]";
	}
	
}
