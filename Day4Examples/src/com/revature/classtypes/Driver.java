package com.revature.classtypes;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		String[] fishTypes = {"One", "Two", "Red", "Blue"};
		Guppie bob = new Guppie("Bob", "Guppie");
		Clown jay = new Clown("Jay", "Clown");
		ArrayList<Fishes> hotdog = new ArrayList<>();
		
		bob.eat();
		bob.swim(9001);
		bob.typeOFish(fishTypes);
		System.out.println(bob.getName());
		fishToHotDog(bob, hotdog);
		fishToHotDog(jay, hotdog);

	}
	public static void fishToHotDog(Fishes g, ArrayList<Fishes> a) {
		a.add(g);
		System.out.println("Added Fish");
	}
}
