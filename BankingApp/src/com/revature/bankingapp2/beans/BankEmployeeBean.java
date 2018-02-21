package com.revature.bankingapp2.beans;

public class BankEmployeeBean extends UserBean{
	
	private static final long serialVersionUID = -8450905890611165045L;
	private static final String accountType = "Employee";
	
	public BankEmployeeBean(String username, String password, String firstName, String lastName) {
		super(username, password, firstName, lastName);
	}

	public static String getAccounttype() {
		return accountType;
	}

}//end class
