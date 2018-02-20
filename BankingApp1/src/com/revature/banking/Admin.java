package com.revature.banking;

import java.io.File;

public class Admin extends Employee {

	private static final long serialVersionUID = 1L;

	public Admin(String username, String password, String fName, String lName, int addressNumber, String addressStreet,
			int zipCode, int accountType) {
		super(username, password, fName, lName, addressNumber, addressStreet, zipCode, accountType);
	}

	public Admin() {
		super(null, null, null, null, 0, null, 0, 2);
	}

	public void cancelAccount(String username) {
		File file = new File("..\\BankingApp1\\ignorethis\\users\\" + username + ".txt");

		if (file.isFile()) {
			if (file.delete()) {
				System.out.println(username + " has been deleted");
			} else {
				System.out.println("Not Deleted");
			}
		} else {
			System.out.println("User (" + username + ") not found for deletion.");
		}

	}

}