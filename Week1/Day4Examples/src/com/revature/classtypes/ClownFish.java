package com.revature.classtypes;

public class ClownFish extends Fish{

	public ClownFish(String name, String type) {
		super();
		this.numOfFins = super.numOfFins;
		this.type = "clownfish";
		this.name = name;
	}

	@Override
	public void swim(int speed) {
		System.out.println(this.name + " swims like a clownfish at " + speed + " mph!");
	}

	@Override
	public void eat(String food) {
		System.out.println(this.name + " eats " + food + " like a clownfish.");		
	}

}