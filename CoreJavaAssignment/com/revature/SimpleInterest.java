package com.revature;

public class SimpleInterest {
	int interest;
	
	public SimpleInterest(int principle, int rate, int time)
	{
		interest = principle * rate * time;
	}
	
	public int getInterest() {
		return interest;
	}
	
	
}
