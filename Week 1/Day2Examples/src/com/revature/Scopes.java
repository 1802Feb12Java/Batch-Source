package com.revature;

public class Scopes {
	static int numOfPuppies; // static variable, lifetime= class;
	
	//instance
	private int numOfLegs;
	 private boolean goodBoy;
	private String nameColor;
	//identifiers
	//begin w/ letters,underscores, or currency characters
	//after first, can include numbers also
	private int $isadumbvariablename;
	
	/*Reference types are objects. 
	 * They’re more than just a number;
	 *  they hold bits which refer to an object.
	Primitive types are just a value (e.g. int, double, byte, char).*/
	//Java is a language that passes by value.
	//It passes the values of references rather than the references themselves.
	/*bit depth
	Numeric type conversions
	Narrowing- smaller-Reducing bit-depth by going from a larger to smaller type

	Widening- larger-Increasing bit-depth by going from a smaller to larger type*/
	
	



	

	public Scopes(int numOfLegs, boolean goodBoy,
			String nameColor) {//the parameter “nameColor” is a method scope
		this.numOfLegs = numOfLegs; //this.[] is instance scope
		this.goodBoy = goodBoy;
		this.nameColor = nameColor;//Shadowing -What occurs when variables 
									//in different scopes have the same identifier.
									//Also between subclass and superclass variables.
	}
	
	
	public static void main(String[] args) {
		System.out.println(numOfPuppies);

	}
	public int methodTest(int a) {// a and test are method variables, lifetime= method
		 int test= 3+a;
		return test;
	}
	
	public void loopy() {
		for(int i=0;i<5;i++) {// i is a block scope variable; lifetime=loop
			System.out.println(i+ "is a number! Roll tide!");
		}
	}
	
	public void staticTest() {
		final int test= 3;
		System.out.println(test);
	}
}
