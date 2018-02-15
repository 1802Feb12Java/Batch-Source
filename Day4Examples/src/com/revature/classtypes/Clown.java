package com.revature.classtypes;

public class Clown extends Fishes {


	private int fins = Clown.numOfFins;
	private String name;
	private String type;
	
	
	public Clown(String name, String type) {
		super();
		this.name = name;
		this.type = type;
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

	@Override
	public void typeOfFish(String[] a) {
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
	
}
