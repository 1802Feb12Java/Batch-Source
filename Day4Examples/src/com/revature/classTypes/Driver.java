package com.revature.classTypes;

public class Driver {

	public static void main(String[] args) {
		// sysout cntrl+enter
		String[] fishies = { "One", "2", "redFish", "blueFish" };
		Guppy gup = new Guppy();
		gup.eat();
		gup.typeOFish(fishies);
		gup.swim(10);

		boolean x = true, y = false;
		if (x = y)
			gup.print.accept("if");
		gup.print.accept(x);
		gup.print.accept(y);

		Clown jay = new Clown(3, "Jay", "guppy");
		gup.print.accept(jay);
	}

	public static <T> void stuff(T t) {

	}
}
