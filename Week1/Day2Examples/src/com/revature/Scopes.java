package com.revature;

public class Scopes {

	static int numOfPuppies; //static scope, lifetime = class

	public int methodTest(int a) { //method scope, lifetime = method
		int test = 3 + a;
		return test;
	}

	//instance scope
	private int numOfLegs;
	private boolean isGoodBoy;
	private String nameColor;

	public Scopes(int numOfLegs, boolean isGoodBoy, String nameColor) { //method scope
		this.numOfLegs = numOfLegs; //instance scope, lifetime  = object
		this.isGoodBoy = isGoodBoy;
		this.nameColor = nameColor; //shadowing: variables in different scopes have same identifier
	}

	public void loopMethod() {
		for (int i=0; i<5; i++) {// block scope, lifetime = loop
			System.out.println(i + " is a number.");
		}
	}
}