package com.revature.classtypes;

import java.util.Arrays;

public class PondSmelt extends Fishes {

	private int fins = PondSmelt.numOfFins;
	private String name;
	private String type;
	public PondSmelt(String name, String type) {
		super();
		//this.fins = this.numOfFins;
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


	public void typeOfFish(String[] a) {
		System.out.println("Types of fish " + Arrays.toString(a));
	}
	public void swim(int speed) {
		System.out.println("I'm doing it ma!");		
	}
	public void eat() {
		// TODO Auto-generated method stub
		System.out.println("I'm the cookie monster, nom, nom, nom.");
	}
	
	
	
	
}
