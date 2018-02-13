package com.revature;

public class Mouse {
	private int weight;
	private int numTeeth;
	private int numWhiskers;
	
	//default constructor
	public Mouse() {
		this(90);  //calls constructor with one parameter
	}
	
	public Mouse(int weight) {
		this(weight, 15);  //calls constructors w/2 parameters
	}
	
	public Mouse(int weight, int numTeeth) {
		this(weight, numTeeth, 6);  //calls constructor with 3 parameters
	}
	
	public Mouse(int weight, int numTeeth, int numWhiskers) {
		this.weight = weight;
		this.numTeeth = numTeeth;
		this.numWhiskers = numWhiskers;
	}
	
	@Override
	public String toString() {
		return "Mouse [weight=" + weight + ", numTeeth=" + numTeeth + ", numWhiskers=" + numWhiskers + "]";
	}
	
	
}



