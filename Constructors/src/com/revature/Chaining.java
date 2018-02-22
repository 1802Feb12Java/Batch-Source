package com.revature;

/**
 * Practice example on Constructor Chaining
 */
public class Chaining {

	public static void main(String[] args) {

		java.util.function.Consumer<Mouse> r1 = (Mouse m) -> System.out.println(m.toString());

		Mouse mouse = new Mouse(6000, 10, 4);
		Mouse mighty = new Mouse(5, 564);
		Mouse mickey = new Mouse(19);
		Mouse minnie = new Mouse();

		r1.accept(mouse);
		r1.accept(mighty);
		r1.accept(mickey);
		r1.accept(minnie);
	}

}
