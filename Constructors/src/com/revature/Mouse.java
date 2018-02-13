package com.revature;

public class Mouse {
	private int numTeeth;
	private int weight;
	private int numWhiskers;
	
	public Mouse(int weight ,int numTeeth, int numWhiskers) {
		
		this.numTeeth = numTeeth;
		this.weight = weight;
		this.numWhiskers = numWhiskers;
	}
	
	public Mouse(int weight, int numTeeth) {
		this(weight,numTeeth, 6); //calls constructor w/ 3 parameters
	}

	public Mouse(int weight) {
		this(weight, 15); // calls constructor w/ 2 parameters
	}
	
	public Mouse() {
		this(90);
	}
	
	public int getNumTeeth() {
		return numTeeth;
	}

	public void setNumTeeth(int numTeeth) {
		this.numTeeth = numTeeth;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getNumWhiskers() {
		return numWhiskers;
	}

	public void setNumWhiskers(int numWhiskers) {
		this.numWhiskers = numWhiskers;
	}

	@Override
	public String toString() {
		
		return "Mouse [numTeeth=" + numTeeth + ", weight=" + weight + ", numWhiskers=" + numWhiskers + "]";
	}
	
	
	
}
