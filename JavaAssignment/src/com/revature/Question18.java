/*  This is a class file for extending to an abstract class file
 * 
 *  @author Dominic Nguyen
 */

package com.revature;

public class Question18 extends Question18String{
	
	private String name;
	private String type;
	
	public Question18() {
		super();
	}

	public Question18(String name, String type) {
		super();
		
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean checkUpperCase(String str) {
		// TODO Auto-generated method stub

		char c;
		int count = 0;
		
		for(int i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			
			if(Character.isUpperCase(c)) {
				count++;
			}
		}
		
		if(count > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public String upperCase(String str) {
		// TODO Auto-generated method stub
		
		String str2 = str.toUpperCase();
		
		return str2;
	}

	@Override
	public int stringToInteger(String str) {
		// TODO Auto-generated method stub
		int i = 0;
		
		try {
			i = Integer.valueOf(str);
			i = i + 10;
			return i;
		}
		catch(NumberFormatException e) {
			return -1;
		}
	}
}
