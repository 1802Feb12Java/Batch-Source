package com.revature.classtypes;

import java.util.*;

public class Driver {
	public static void fishToFishList(Fish g, List<Fish> fishList) {
		fishList.add(g);
		System.out.println("\nAdded a " + g.type + " named "+ g.name + ".\n");
	}

	public static void main(String[] args) {
		String[] fishTypes = {"One", "Two", "Red", "Blue"};
		Guppie bob = new Guppie("Bob");
		ClownFish larry = new ClownFish("Larry", "O-");
		List<Fish> fishList = new ArrayList<>();

		System.out.println(bob.toString());
		bob.eat("fish food");
		bob.swim(9001);
		bob.typeFish(fishTypes);
		
		fishToFishList(bob, fishList);
		
		System.out.println(larry.toString());
		larry.eat("insects");
		larry.swim(2);
		fishToFishList(larry, fishList);
	}

}