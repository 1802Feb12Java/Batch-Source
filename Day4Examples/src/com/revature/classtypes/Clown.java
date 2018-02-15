package com.revature.classtypes;

public class Clown extends Fishes{
	private int fins;
	private String name;
	
	public Clown() {
		super();
	}
	public Clown(String name, String type) {
		super();
		this.fins = this.numOfFins;
		this.name = name;
		this.type = type;
	}
	private String type;

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

	public Clown(int fins, String name) {
		super();
		this.fins = fins;
		this.name = name;
	}

	@Override
	public void typeOFish(String[] a) {
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
