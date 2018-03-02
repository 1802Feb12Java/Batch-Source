package com.revature;

public class CasingConcreteClass extends CasingAbstractClass{
	/*
	 * The purpose of this class is to demonstrate extending an abstract class
	 * and completing the abstract methods inherited from the superclass.
	 */
	private String s;
	
	public CasingConcreteClass(String str) {
		super();
		this.s = str;
	}

	@Override
	public Boolean checkingForUpperCase(){
		//will return true if there are any upper case letters
		//will return false if all letters are lower case
		Boolean b = false;
		for(int i = 0; i < this.s.length(); i++) {
			//iterates through characters in the string to check case
			if(Character.isUpperCase(this.s.charAt(i))) {
				b = true;
				return b;
			}
		}
		return b;
	}//end checkingForUpperCase method

	@Override
	public String makeUpperCase() {
		for(int i = 0; i < this.s.length(); i++) {
			//iterates through the characters in the string
			//making any lower case letters upper case
			if(Character.isLowerCase(this.s.charAt(i))) {
				this.s = this.s.replace(this.s.charAt(i), Character.toUpperCase(this.s.charAt(i)));
			}
		}
		return this.s;
		//returns the all upper case string
	}//end makeUpperCase method

	@Override
	public int stringToIntPlus() {
		Integer strAsInt = Integer.valueOf(this.s);
		return strAsInt+10;
	}//end stringToIntPlus method

	public String getS() {
		return s;
	}

	public void setS(String str) {
		this.s = str;
	}

	@Override
	public String toString() {
		return "CasingConcreteClass [s=" + s + "]";
	}
	
	
	
}//end class
