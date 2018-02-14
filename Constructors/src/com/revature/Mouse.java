package com.revature;

public class Mouse {

	private int numTeeth;
	private int numWhiskers;
	private int weight;
	
	public Mouse() {
		this(90);
	}
	
	public Mouse(int weight) {
		this(weight, 15);
	}
	
	public Mouse(int weight, int numTeeth) {
		this(weight, numTeeth, 6);
		//calls constructor with 3 parameters
	}
	
	public Mouse(int weight, int numTeeth, int numWhiskers) {
		this.weight = weight;
		this.numTeeth = numTeeth;
		this.numWhiskers = numWhiskers;
	}

	@Override
	public String toString() {
		return "Mouse [numTeeth=" + numTeeth + ", numWhiskers=" + numWhiskers + ", weight=" + weight + "]";
	}
	
	
	
}
