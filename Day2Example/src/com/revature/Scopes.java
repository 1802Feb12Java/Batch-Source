package com.revature;

public class Scopes {

	// Static/Class Scope: lifetime = class
	/**
	 * Shadowing: In Java, if name of a derived class static function is same as
	 * base class static function then the derived class static function shadows (or
	 * conceals) the base class static function.
	 */
	static int numOfPuppies;

	// Instance Scope: lifetime = instance
	private int numOfDogs;

	// Identifiers: can start with letters, underscores, or currency characters.
	// Subsequent characters may be letters, digits, currency characters, or
	// underscore characters
	private int $$$$;
	private int _________;

	/**
	 * Reference types are objects - they're more than just a number they hold bits
	 * which refer to an object
	 * 
	 * Primitive types are just a value (int, float, double, long, byte, char,
	 * boolean, short)
	 */

	/**
	 * Numeric Type Conversions Narrowing - smaller --- double -> int ,,, reduicng
	 * bit-depth by going from a larger to smaller type
	 * 
	 * Widening - larger ----- increasing bit-depth by going from a smaller to
	 * larger type
	 */

	public void scopeTest(int a) {

		// a, x - Local/Method Scope: lifetime = method
		int x = 3;

		// Block Scope: lifetime = control statement/ loop
		while (true) {
			int y = 0;
			break;
		}
	}

	public Scopes(int numOfDogs) {
		super();
		/**
		 * Shadowing: what happens when variables in different scopes have the same
		 * identifier, also happens between subclasses and superclass variables.
		 * 
		 * One variable shadows another if they have the same name and are accessible in
		 * the same place.
		 */
		this.numOfDogs = numOfDogs;
	}

	public static void main(String[] args) {

	}
}
