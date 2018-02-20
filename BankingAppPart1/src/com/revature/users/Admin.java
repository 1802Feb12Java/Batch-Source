package com.revature.users;

public class Admin extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2305340192582828032L;
	public Admin() {
		super();
	}

	
	public Admin(String userName, String password, String firstName, String lastName) {
		super(userName, password, firstName, lastName);
		// TODO Auto-generated constructor stub
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
