package com.revature;

public class Q18Concrete extends Q18Abstract{ //creates functionality for abstract methods

	@Override
	boolean checkForUppercase(String s) {
		for(int i = 0; i<s.length(); i++) {
			char ch = s.charAt(i);
			if(Character.isUpperCase(ch)) {//just 1 uppercase needed for true
				return true;
			} 
		}
		return false;
	}

	@Override
	void convertToUppercase(String s) {
		System.out.println(s.toUpperCase());
	}

	@Override
	void convertToIntThenAdd(String s) {
		int count = 0;
		String[] strArray = s.split("");
		for(String c : strArray) {
			count += 1;
		}
		System.out.println(count + 10);
	}

}
