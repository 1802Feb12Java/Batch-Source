package com.revature.bankingapp.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public interface User extends Serializable{
	
	Object getUserName();

	public String getMainMenu();
	
	public void userDriverStart(Scanner sysScanner);
		
	public void enterAccount(Account account);
	
	public String getPassWord();
	
	public ArrayList<Account> getAccounts();
	//public void saveUserAccount();

	void addToAccounts(Account newAccount);
	
}
