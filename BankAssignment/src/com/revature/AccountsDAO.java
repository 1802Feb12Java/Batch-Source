package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public interface AccountsDAO {

	//view accounts
	void viewAccount(Connection c, User usr)throws SQLException;
	
	//delete if empty
	void deleteAccount(Connection c)throws SQLException;
	
	//deposit
	void deposit(Connection c, double amount, User usr)throws SQLException;
	
	//withdraw
	void withdraw(Connection c, double amount, User usr)throws SQLException;
	
	//transfer
	void newAcct(Connection c, User user)throws SQLException;
}
