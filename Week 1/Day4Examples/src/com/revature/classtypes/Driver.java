package com.revature.classtypes;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	
	public static void main(String[] args) {
		 String[] fishTypes = {"One","two","red","blue"};
		Guppie bob= new Guppie("Bob","CLown");
		ArrayList<Fishes> hotdog=new ArrayList<>();
		Clown jay= new Clown("Jay","Guppie");
		
		//Date d= new Date();
		bob.eat();
		bob.swim(9001);
		bob.typeOFish(fishTypes);
		System.out.println(bob.getName());
		fishToHotDog(bob,hotdog);
		fishToHotDog(jay,hotdog);
	}
	public static void fishToHotDog(Fishes g,ArrayList<Fishes> a) {
		a.add(g);
		System.out.println("Added guppie.");
	}
	
}
