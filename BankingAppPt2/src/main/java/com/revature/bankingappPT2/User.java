package com.revature.bankingappPT2;

import java.util.ArrayList;

public interface User{

	String getUsername();

	String getPassword();
	
	Integer getUserAccess();

	ArrayList<Account> getListOfAccounts();

	void addAccount(Account newAccount);
	
	public void setCustomerID(Integer customerID);

	Integer getCustomerID();
	
}
