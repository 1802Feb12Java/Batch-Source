package com.revature.beans;

public class Manager extends Employee {

	//Just an employee with a different class name for type checks/generics in 
	//the functionality classes
	
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Manager(String username, String password, String email) {
		super(username, password, email);
		// TODO Auto-generated constructor stub
	}
	
	public Manager(String username, String password, String email, String firstname, String lastname) {
		super(username, password, email, firstname, lastname);
	}


	@Override
	public int getAccess() {
		// TODO Auto-generated method stub
		return 2;
	}
}
