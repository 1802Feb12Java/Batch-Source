package com.revature.day2.constructorstuff;

public class Mouse {

	private int numTeeth;
	private int numWhiskers;
	private int weight;
	private String name;
	public Mouse() {
		this(0);
	}
	
	public Mouse(int weight) {
		this(weight, 0);
	}
	
	public Mouse(int weight, int numTeeth) {
		this(weight, numTeeth, 0); //calls constructor wth 3 params
	}
	
	public Mouse(int weight, int numTeeth, int numWhiskers) {
		this.numTeeth = numTeeth;
		this.numWhiskers = numWhiskers;
		this.weight = weight;		
		
	}

	@Override
	public String toString() {
		return "Mouse [numTeeth=" + numTeeth + ", numWhiskers=" + numWhiskers + ", weight=" + weight + "]";
	}

	
	
	
	
}
