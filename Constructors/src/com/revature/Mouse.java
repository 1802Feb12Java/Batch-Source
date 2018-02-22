package com.revature;

public class Mouse {

	private int numberTeeth;
	private int numberWhiskers;
	private int weight;

	/**
	 * Constructor chaining 0
	 */
	public Mouse() {
		this(7);
	}

	/**
	 * Constructor chaining 1
	 * 
	 * @param weight
	 */
	public Mouse(int weight) {
		this(15, weight);
	}

	/**
	 * Constructor chaining 2
	 * 
	 * @param weight
	 * @param numberTeeth
	 */
	public Mouse(int weight, int numberTeeth) {
		this(numberTeeth, 6, weight);
	}

	/**
	 * Constructor chaining 3
	 * 
	 * @param numberTeeth
	 * @param numberWhiskers
	 * @param weight
	 */
	public Mouse(int numberTeeth, int numberWhiskers, int weight) {
		super();
		this.numberTeeth = numberTeeth;
		this.numberWhiskers = numberWhiskers;
		this.weight = weight;
	}

	public String toString() {
		// return super.toString();
		return "Mouse [numberTeeth=" + numberTeeth + ", numberWhiskers=" + numberWhiskers + ", weight=" + weight + "]";
	}

	public int getNumberTeeth() {
		return numberTeeth;
	}

	public void setNumberTeeth(int numberTeeth) {
		this.numberTeeth = numberTeeth;
	}

	public int getNumberWhiskers() {
		return numberWhiskers;
	}

	public void setNumberWhiskers(int numberWhiskers) {
		this.numberWhiskers = numberWhiskers;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
