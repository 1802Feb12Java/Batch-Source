package com.revature;

import java.util.function.Consumer;

public class Wrapperss {

	// wrappers allow primitives to be treated as objects
	// utility methods

	public Integer i = new Integer(9);
	public Integer j = new Integer(10);

	private Consumer<Object> f = (Object i) -> System.out.println(i);

	public void funky() {
		if (i.equals(j))
			f.accept("Same");
		else
			f.accept("not the Same");
	}

	public static void main(String[] args) {
		Wrapperss x = new Wrapperss();
		x.funky();

	}
}
