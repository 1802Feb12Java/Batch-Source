package com.revature.classTypes;

public class Clown extends Fishes {

	private int fins;
	private String name;
	private String type;

	public Clown() {
	}

	public Clown(int fins, String name, String type) {
		super();
		this.fins = fins;
		this.name = name;
		this.type = type;
	}

	@Override
	public void typeOFish(String[] f) {
		// TODO Auto-generated method stub

	}

	@Override
	public void swim(int speed) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eat() {
		// TODO Auto-generated method stub

	}

	public int getFins() {
		return fins;
	}

	public void setFins(int fins) {
		this.fins = fins;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
