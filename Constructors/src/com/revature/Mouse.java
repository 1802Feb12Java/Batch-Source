package com.revature;

public class Mouse {
<<<<<<< HEAD

=======
>>>>>>> origin/KnightenM
	private int numTeeth;
	private int numWhiskers;
	private int weight;
	
	public Mouse() {
		this(90);
	}
<<<<<<< HEAD
	
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

=======
	public Mouse (int weight, int numTeeth,int numWhiskers) {
		this.weight=weight;
		this.numTeeth=numTeeth;
		this.numWhiskers=numWhiskers;
	}
	
	
	public Mouse(int weight, int numTeeth) {
		this(weight, numTeeth,6); //calls constructor w/ 3 parameters
	}
	public Mouse (int weight) {
		this(weight, 15); //calls constructors w/ 2 parameters
	}
>>>>>>> origin/KnightenM
	@Override
	public String toString() {
		return "Mouse [numTeeth=" + numTeeth + ", numWhiskers=" + numWhiskers + ", weight=" + weight + "]";
	}
<<<<<<< HEAD
	
	
=======

	/*FUN!
	public String toString() {
		return super.toString();
	}*/
>>>>>>> origin/KnightenM
	
}
