package com.revature.classtypes;

import java.util.ArrayList;

public class Driver {

	public static void main(String[] args) {

		String[] fishTypes = {"One", "Two", "red", "blue"};
		
		PondSmelt frank = new PondSmelt("Frank the tank", "Pond Smelt");
		Clown bill = new Clown("Bill the pill", "Clown");
		ArrayList<Fishes> hotdog = new ArrayList<>();
		
		
		frank.eat();
		frank.swim(5);
		frank.typeOfFish(fishTypes);
		System.out.println(frank.getName());
		fishToHotdog(frank, hotdog);
		bill.eat();
		bill.swim(6);
		System.out.println(bill.getName());
		fishToHotdog(bill, hotdog);
		
	}
	
	public static void fishToHotdog(Fishes p, ArrayList<Fishes> a) {
		a.add(p);
		System.out.println(p.getName() + " became a hotdog. :(");
	}

}
