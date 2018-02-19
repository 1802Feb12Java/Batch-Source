package com.revature;

public class StringManipulation extends StringAbstractClass{

	public static void example() {
		// TODO Auto-generated method stub
		
		StringManipulation manip = new StringManipulation();
		
		System.out.println("Does 'hellO' constain a capital letter?: " + manip.checkUppercase("hellO"));
		System.out.println("Does 'hello' constain a capital letter?: " + manip.checkUppercase("sasdadd"));
		System.out.println("Convers HELLO to lowercase: "+ manip.toLowerCase("HELLO"));
		System.out.println("500 is a String, let's add 10 anyway: " + manip.toIntAddTen("500"));

		
	}

	@Override
	public boolean checkUppercase(String str) {
		// TODO Auto-generated method stub
		
		for(int i = 0; i < str.length(); i++) {
			
			//if an uppercase letter is found, return true
			if(Character.isUpperCase(str.charAt(i))) {
				return true;
			}
		}
		
		//no uppercase characters, return false
		return false;
	}

	@Override
	public String toLowerCase(String str) {
		// TODO Auto-generated method stub
		
		StringBuilder mySB = new StringBuilder();
		
		//create new string with lowercase variables
		for(int i = 0; i < str.length(); i++) {
			
			mySB.append(Character.toLowerCase(str.charAt(i))); 
			
		}
		
		return mySB.toString();
	}

	@Override
	public Integer toIntAddTen(String str) {
		// TODO Auto-generated method stub
		
		//checking a possible unchecked exception, so it may be handled
		try {
			return Integer.valueOf(str) + 10;
		}catch(NumberFormatException e){
			System.out.println("NumberFormatException, return -1");
		}
		
		return -1;
	}

}
