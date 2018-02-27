package com.revature.BankingAppPt2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class CustomerDAO extends UserDAO {
	final static Logger logger = Logger.getLogger(Customer.class);
	
	public CustomerDAO(Connection connection) {
		super(connection);
	}
	
	public void deleteAccount(int accountId) {
		try {
			CallableStatement callDelete = connection.prepareCall("{call delete_account(?)}");
			callDelete.setInt(1, accountId);
			callDelete.execute();
			logger.info("Account with ID: " + accountId + " was deleted.");
		} catch (SQLException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
	}
}
