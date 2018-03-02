package com.revature.banking;

public class Employee extends User {

	private static final long serialVersionUID = 1L;

	public Employee(String username, String password, String fName, String lName, int addressNumber,
			String addressStreet, int zipCode, int accountType) {
		super(username, password, fName, lName, addressNumber, addressStreet, zipCode, accountType);
	}
	
	public Employee() {
		super(null, null, null, null, 0, null, 0, 1);
	}

	public void viewUser(String user) {
		User viewedUser = new User(user);
		viewedUser = viewedUser.getUser1(user);
		
		System.out.println(viewedUser.toString());
	}

}