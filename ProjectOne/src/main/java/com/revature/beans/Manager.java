package com.revature.beans;

public class Manager extends Employee {

	//Just an employee with a different class name for type checks/generics in 
	//the functionality classes
	
	@Override
	public int getAccess() {
		// TODO Auto-generated method stub
		return 2;
	}
}
