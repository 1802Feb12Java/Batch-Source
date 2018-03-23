package com.revature.beans;

public class BearWithAutowiringByName extends Bear {
	
	private Cave cave;
	
	public void setCave(Cave cave){ //method identifier must match name of dependency
		this.cave=cave;
	}

	@Override
	public void methodInBear() {
		System.out.println("method in BearWithAutowiringByName. this bear is: "+this.toString());
		System.out.println("cave: "+cave.toString());

	}

}
