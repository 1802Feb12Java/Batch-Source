package com.revature.beans;

public class BearWithAutowiringByType extends Bear {
	
private Cave cave;
	
	//this is ok because we're wiring by type, not by the setter argument name
	public void setSomethingDifferent(Cave somethingDifferent){
		this.cave = somethingDifferent;
	}

	@Override
	public void methodInBear() {
		System.out.println("method in BearWithAutowiringByType. this bear is: "+this.toString());
		System.out.println("cave: "+cave.toString());
		
	}

}
