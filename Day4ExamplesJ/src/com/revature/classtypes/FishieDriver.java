package com.revature.classtypes;

public class FishieDriver {

	public static void main(String[] args) {
		String[] fishTypes = {"One", "Two", "Red", "Blue"};
		
		Guppie bob = new Guppie("Bob", "Clown");
		bob.eat();
		bob.swim(500);
		bob.typeOfFish(fishTypes);

	}

}
