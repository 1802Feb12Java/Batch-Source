package com.revature;

public class Mouse {
	private int numTeeth;
	private int numWhiskers;
	private int weight;
	
	public Mouse(int numTeeth, int numWhiskers, int weight) {
		this.numTeeth = numTeeth;
		this.numWhiskers = numWhiskers;
		this.weight = weight;
	}

	public Mouse() {
		this(7);
	}
	
	public Mouse(int numTeeth) {
		this(numTeeth,15);
	}
	
	public Mouse(int numTeeth, int numWhiskers) {
		this(numTeeth, numWhiskers, 23);
	}
	
	@Override
	public String toString() {
		return "Mouse [numTeeth=" + numTeeth + ", numWhiskers=" + numWhiskers + ", weight=" + weight + "]";
	}
	
	
}
