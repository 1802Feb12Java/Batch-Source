package com.revature.bankingapp2.beans;

public class BankAdminBean extends UserBean{

	private static final long serialVersionUID = -6311879095063202278L;
	private static final String accountType = "Admin";
	
	public BankAdminBean(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
	}

	public static String getAccounttype() {
		return accountType;
	}

}//end class
