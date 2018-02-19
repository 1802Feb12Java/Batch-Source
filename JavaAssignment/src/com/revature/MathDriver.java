/* Driver program for doing operations such as addition, subtraction, multiplication, and division */

package com.revature;

public class MathDriver {

	public static void main(String[] args) {
		Addition add = new Addition("A", "Addition");
		Subtraction sub = new Subtraction("S", "Subtraction");
		Multiplication mult = new Multiplication("M", "Multiplication");
		Division div = new Division("D", "Division");
		
		System.out.println(add.getName());
		System.out.println(sub.getName());
		System.out.println(mult.getName());
		System.out.println(div.getName());
		System.out.println();
		
		// Perform calculations
		add.calculate();
		sub.calculate();
		mult.calculate();
		div.calculate();
	}
}
