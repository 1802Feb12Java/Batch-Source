package com.revature.classtypes;

import java.util.Arrays;

public class Guppie extends Fishes{
	private int fins;
	private String name;
	
	public Guppie() {
		super();
	}
	public Guppie(String name, String type) {
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public void typeOFish(String [] a) {
		System.out.println(Arrays.toString(a));		
	}
	@Override
	public void swim(int speed) {
		System.out.println("swim");
		
	}
	@Override
	public void eat() {
		System.out.println("eat");
		
	}
	
}
