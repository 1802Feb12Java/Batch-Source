package com.revature.classtypes;

import java.util.Arrays;

public abstract class Fish implements InterfaceFun {

	protected int numOfFins = 2;
	protected String name;
	protected String type;
	public abstract void swim(int speed);
	public abstract void eat(String food);

	@Override
	public void typeFish(String[] a) {
		System.out.println(Arrays.toString(a));
	}

	public String toString() {
		return "Name:\t" + this.name + "\nFins:\t" + this.numOfFins + "\nType:\t" + this.type;
	}
}