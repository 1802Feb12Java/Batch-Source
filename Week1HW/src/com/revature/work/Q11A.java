package com.revature.work;
import com.revature.workout.Q11B;

public class Q11A {
	
	void print() {
	// creating an object from a class in another package 
	Q11B Trial = new Q11B();
	// object comes with its variables
	float x = Trial.myTmp1;
	float y = Trial.myTmp2;
	System.out.println("From a whole different package came "+x+" and "+y);
	}
}
