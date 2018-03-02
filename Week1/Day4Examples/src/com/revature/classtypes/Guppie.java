package com.revature.classtypes;

public class Guppie extends Fish {

	public Guppie(String name) {
		this.numOfFins = super.numOfFins;
		this.type = "guppie";
		this.name = name;
	}

	@Override
	public void swim(int speed) {
		System.out.println(this.name + " swims like a guppie at " + speed + " mph!");
	}

	@Override
	public void eat(String food) {
		System.out.println(this.name + " eats " + food + " like a guppie.");		
	}

}