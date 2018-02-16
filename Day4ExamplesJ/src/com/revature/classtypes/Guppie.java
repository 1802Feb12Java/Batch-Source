package com.revature.classtypes;

import java.util.Arrays;

public class Guppie extends Fishes {

	private int fins = this.numOfFins;
	private String name;
	private String type;
	
	public Guppie() {
		super();
	}
	
	public Guppie(String name, String type) {
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
		
		System.out.println(Arrays.toString(a));
		
	}
	
	@Override
	public void swim(int speed) {
		System.out.println("Swim!");
		
	}
	@Override
	public void eat() {
		System.out.println("Nom Nom Nom");
		
	}
	
	
	
}
