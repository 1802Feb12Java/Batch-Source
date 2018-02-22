package com.revature.classTypes;

import java.util.Arrays;

public class Guppy extends Fishes {

	private int fins;
	private String name;
	private String type;

	public Guppy(int fins, String name, String type) {
		super();
		this.fins = fins;
		this.name = name;
		this.type = type;
	}

	public Guppy() {
	}

	@Override
	public void typeOFish(String[] f) {
		print.accept(Arrays.toString(f));
	}

	@Override
	public void swim(int speed) {
		print.accept("swimming! at " + speed + " mph");
	}

	@Override
	public void eat() {
		print.accept("nom nom");
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
