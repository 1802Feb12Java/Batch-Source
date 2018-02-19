package com.revature.answers;

public class Question18 extends Question18Super {

	public void run() {
		String s = "Howdy Boy";
		String j = "no sir";
		String num = "15";
		if(hasUpper(s)) {
			System.out.println(s + " has at least one upper case letter.");
		} else {
			System.out.println(s + " is all lower case.");
		}
		if(hasUpper(j)) {
			System.out.println(j + " has at least one upper case letter.");
		} else {
			System.out.println(j + " is all lower case.");
		}
		System.out.println(getBig(j));
		System.out.println(num);
		System.out.println("Value of " + num + " plus 10: " + whatsYoNumba(num));
	}

	public boolean hasUpper(String s) {
		if(s.compareTo(s.toLowerCase()) == 0) {		//this statement checks the input string to the lowercase of 
			return true;							//the input string. If it returns 0 then they are the same
		}else {										//meaning there are no uppercase letters.
			return false;
		}
	}

	public String getBig(String s) {				//this method converts the string into yelling format, aka
		s.toUpperCase();							//all uppercase letters.
		return s;
	}

	public int whatsYoNumba(String s) {				//this method converts the string to an Integer object
		int x;										//then we add 10 to it and return the value.
		Integer i = Integer.valueOf(s);
		x = i.intValue() + 10;
		return x;
	}

}
