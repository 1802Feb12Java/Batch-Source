package com.revature.bankingappPT2;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDAO {
	//TODO: all the connectivity stuff
	//everything throws SQLException
	
	public void updateAccountBalance(Double balance, Integer accountID) throws SQLException;
	public void addCustomerToTable(User newUser) throws SQLException;
}
